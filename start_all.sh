#!/bin/bash
# FinalCombat 一键启动服务器
# 用法: sudo bash start_all.sh    (需要 root 启动系统服务，游戏服务自动用当前用户)
set -e
BASE="$(cd "$(dirname "$0")" && pwd)"
BIN="$BASE/game_server/bin"
LOG="$BASE/logs"
PIDS="$LOG/pids"
mkdir -p "$LOG" "$PIDS"

# ============ 服务器对外 IP/域名（客户端连频道的地址，必填） ============
SERVER_IP="192.168.31.5"     # 改成你服务器的实际 IP 或域名
AUTH_PORT="${AUTH_PORT:-18080}"      # 认证 HTTP 端口（登录 API）
INFO_PORT="${INFO_PORT:-8081}"       # Proxy 侧的内网信息端口
DUMMY_PORTS="${DUMMY_PORTS:-28085,28086,28222}"  # 避免与主机 808x 端口冲突
SERVER_PORT="${SERVER_PORT:-15000}" # 客户端连接 proxyserver 端口
# ======================================================================

run_in_bg() {
  local name="$1"
  local logfile="$2"
  local workdir="$3"
  shift 3

  (
    cd "$workdir"
    setsid "$@" > "$logfile" 2>&1 < /dev/null
  ) &
  local pid=$!
  echo "$pid" > "$PIDS/$name.pid"
  echo "   [start] $name => pid=$pid"
}

stop_old_by_pidfile() {
  shopt -s nullglob
  local files=("$PIDS"/*.pid)
  if [ "${#files[@]}" -eq 0 ]; then
    echo "   没有检测到上次启动的 PID 文件"
    for p in proxyserver channelserver matchingserver apexserver logserver login_server logserver_dummy; do
      pkill -f "$p" 2>/dev/null || true
    done
    shopt -u nullglob
    return
  fi

  for f in "${files[@]}"; do
    local name pid
    name="$(basename "$f" .pid)"
    pid="$(cat "$f")"
    if [ -z "$pid" ]; then
      rm -f "$f"
      continue
    fi
    if kill -0 "$pid" 2>/dev/null; then
      echo "   [stop-old] $name pid=$pid"
      kill -TERM "$pid" 2>/dev/null || true
      sleep 1
      if kill -0 "$pid" 2>/dev/null; then
        kill -KILL "$pid" 2>/dev/null || true
      fi
    fi
    rm -f "$f"
  done
  shopt -u nullglob
}

echo "========================================"
echo " FinalCombat 服务器一键启动"
echo "========================================"

# ---------- 0. 停止旧进程 ----------
echo "[0] 停止旧进程..."
stop_old_by_pidfile
sleep 1

# ---------- 1. 基础服务 ----------
echo "[1] 启动基础服务 (mysql/memcached/redis/jetty)..."
systemctl start mysql 2>/dev/null || service mysql start 2>/dev/null || true
systemctl start memcached 2>/dev/null || true
systemctl start redis-server 2>/dev/null || true
systemctl start jetty9 2>/dev/null || service jetty9 start 2>/dev/null || true
for i in $(seq 1 24); do
  if ss -tlnp 2>/dev/null | grep -q ":$INFO_PORT "; then
    echo "   info server $INFO_PORT 就绪"
    break
  fi
  sleep 5
done

# 清 memcached 缓存
python3 -c "
import socket
try:
    s=socket.create_connection(('127.0.0.1',11211),timeout=3)
    s.sendall(b'flush_all\\r\\n'); s.settimeout(2)
    try:
      s.recv(1024)
    except:
      pass
    s.close()
except:
  pass
" 2>/dev/null || true

# ---------- 2. 游戏服务器栈 ----------
echo "[2] 启动 logserver (11111)..."
run_in_bg "logserver" "$LOG/logserver.log" "$BIN" ./logserver --config log_server.ini
sleep 1

echo "[3] 启动 apexserver (9003)..."
run_in_bg "apexserver" "$LOG/apex.log" "$BIN" ./apexserver --log 127.0.0.1:11111 --config apex.cfg --debug 10 --address=127.0.0.1:9003

echo "[4] 启动 matchingserver (16000)..."
run_in_bg "matchingserver" "$LOG/match.log" "$BIN" ./matchingserver --log 127.0.0.1:11111 --config match.cfg --debug 10 --proxy_listen 0.0.0.0:16000

echo "[5] 启动 channelserver (9011/9012/9013)..."
run_in_bg "channelserver_9011" "$LOG/channel1.log" "$BIN" ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=1 --channel-id=1 --channel-addr=0.0.0.0:9011 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001
run_in_bg "channelserver_9012" "$LOG/channel2.log" "$BIN" ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=5 --channel-id=1 --channel-addr=0.0.0.0:9012 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001
run_in_bg "channelserver_9013" "$LOG/channel3.log" "$BIN" ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=5 --channel-id=2 --channel-addr=0.0.0.0:9013 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001
sleep 2

echo "[6] 启动 proxyserver (${SERVER_PORT})..."
run_in_bg "proxyserver" "$LOG/proxyserver.log" "$BIN" ./proxyserver --log 127.0.0.1:11111 --config proxy.cfg --debug 5 \
  --client-listen=0.0.0.0:$SERVER_PORT --channel-listen=127.0.0.1:9001 \
  --gm-listen=127.0.0.1:9002 --apex-server=127.0.0.1:9003 \
  --match-server=127.0.0.1:16000 -i 127.0.0.1:$INFO_PORT

# ---------- 3. 辅助服务 ----------
echo "[7] 启动认证服务器 ($AUTH_PORT)..."
run_in_bg "login_server" "$LOG/login_server.log" "$BASE" env \
  AUTH_LISTEN_PORT="$AUTH_PORT" \
  SERVER_IP="$SERVER_IP" \
  SERVER_PORT="$SERVER_PORT" \
  python3 login_server.py

echo "[8] 启动 dummy logserver ($DUMMY_PORTS)..."
run_in_bg "logserver_dummy" "$LOG/logserver_dummy.log" "$BASE" env \
  LOGSERVER_DUMMY_PORTS="$DUMMY_PORTS" \
  python3 logserver_dummy.py

# ---------- 4. 验证 ----------
sleep 4
echo "========================================"
echo " 启动验证:"
for port in "$AUTH_PORT" "$INFO_PORT" "$SERVER_PORT" 9011 9012 9013 9003 16000 11111; do
  if ss -tlnp 2>/dev/null | grep -q ":$port "; then
    echo "   OK  :$port"
  else
    echo "   FAIL:$port"
  fi
done
echo "========================================"
echo " 认证服务器: $SERVER_IP:$AUTH_PORT"
echo " 客户端直连:  $SERVER_IP:$SERVER_PORT"
echo " 测试账户:   test01 ~ test05 (密码 123456)"
echo "========================================"

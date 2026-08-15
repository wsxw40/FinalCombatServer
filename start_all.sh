#!/bin/bash
# FinalCombat 一键启动服务器
# 用法: sudo bash start_all.sh    (需要 root 启动系统服务，游戏服务自动用当前用户)
set -e
BASE="$(cd "$(dirname "$0")" && pwd)"
BIN="$BASE/game_server/bin"
LOG="$BASE/logs"
mkdir -p "$LOG"

# ============ 服务器对外 IP/域名（客户端连频道的地址，必填） ============
SERVER_IP="192.168.31.5"     # 改成你服务器的实际 IP 或域名
# ======================================================================

echo "========================================"
echo " FinalCombat 服务器一键启动"
echo "========================================"

# ---------- 0. 停止旧进程 ----------
echo "[0] 停止旧进程..."
for p in proxyserver channelserver matchingserver apexserver logserver "login_server.py" "logserver_dummy.py"; do
    pkill -9 -f "$p" 2>/dev/null || true
done
sleep 1

# ---------- 1. 基础服务 ----------
echo "[1] 启动基础服务 (mysql/memcached/redis/jetty)..."
systemctl start mysql 2>/dev/null || service mysql start 2>/dev/null || true
systemctl start memcached 2>/dev/null || true
systemctl start redis-server 2>/dev/null || true
systemctl start jetty9 2>/dev/null || service jetty9 start 2>/dev/null || true
for i in $(seq 1 24); do
    if ss -tlnp 2>/dev/null | grep -q 8081; then echo "   info server 8081 就绪"; break; fi
    sleep 5
done

# 清 memcached 缓存
python3 -c "
import socket
try:
    s=socket.create_connection(('127.0.0.1',11211),timeout=3)
    s.sendall(b'flush_all\r\n'); s.settimeout(2)
    try: s.recv(1024)
    except: pass
    s.close()
except: pass
" 2>/dev/null || true

# ---------- 2. 游戏服务器栈 ----------
echo "[2] 启动 logserver (11111)..."
(cd "$BIN" && setsid ./logserver --config log_server.ini > "$LOG/logserver.log" 2>&1 < /dev/null &)
sleep 1

echo "[3] 启动 apexserver (9003)..."
(cd "$BIN" && setsid ./apexserver --log 127.0.0.1:11111 --config apex.cfg --debug 10 --address=127.0.0.1:9003 > "$LOG/apex.log" 2>&1 < /dev/null &)

echo "[4] 启动 matchingserver (16000)..."
(cd "$BIN" && setsid ./matchingserver --log 127.0.0.1:11111 --config match.cfg --debug 10 --proxy_listen 0.0.0.0:16000 > "$LOG/match.log" 2>&1 < /dev/null &)

echo "[5] 启动 channelserver (9011/9012/9013)..."
(cd "$BIN" && setsid ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=1 --channel-id=1 --channel-addr=0.0.0.0:9011 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001 > "$LOG/channel1.log" 2>&1 < /dev/null &)
(cd "$BIN" && setsid ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=5 --channel-id=1 --channel-addr=0.0.0.0:9012 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001 > "$LOG/channel2.log" 2>&1 < /dev/null &)
(cd "$BIN" && setsid ./channelserver --log 127.0.0.1:11111 --config channel.cfg --debug 10 --server-id=5 --channel-id=2 --channel-addr=0.0.0.0:9013 --channel-domain-name="$SERVER_IP" --proxy-addr=127.0.0.1:9001 > "$LOG/channel3.log" 2>&1 < /dev/null &)
sleep 2

echo "[6] 启动 proxyserver (15000)..."
(cd "$BIN" && setsid ./proxyserver --log 127.0.0.1:11111 --config proxy.cfg --debug 5 \
  --client-listen=0.0.0.0:15000 --channel-listen=127.0.0.1:9001 \
  --gm-listen=127.0.0.1:9002 --apex-server=127.0.0.1:9003 \
  --match-server=127.0.0.1:16000 -i 127.0.0.1:8081 > "$LOG/proxyserver.log" 2>&1 < /dev/null &)

# ---------- 3. 辅助服务 ----------
echo "[7] 启动认证服务器 (8080)..."
(cd "$BASE" && setsid python3 login_server.py > "$LOG/login_server.log" 2>&1 < /dev/null &)

echo "[8] 启动 dummy logserver (8085/8086/2222)..."
(cd "$BASE" && setsid python3 logserver_dummy.py > "$LOG/logserver_dummy.log" 2>&1 < /dev/null &)

# ---------- 4. 验证 ----------
sleep 4
echo "========================================"
echo " 启动验证:"
for port in 8080 8081 15000 9011 9012 9013 9003 16000 11111; do
    if ss -tlnp 2>/dev/null | grep -q ":$port "; then
        echo "   OK  :$port"
    else
        echo "   FAIL:$port"
    fi
done
echo "========================================"
echo " 认证服务器: $SERVER_IP:8080"
echo " 客户端直连:  $SERVER_IP:15000"
echo " 测试账户:   test01 ~ test05 (密码 123456)"
echo "========================================"

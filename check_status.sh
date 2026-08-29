#!/bin/bash
# FinalCombat 状态检查
BASE="$(cd "$(dirname "$0")" && pwd)"
LOG="$BASE/logs"
PIDS="$LOG/pids"

SERVER_IP="192.168.31.5"
AUTH_PORT="${AUTH_PORT:-18080}"
INFO_PORT="${INFO_PORT:-8081}"
SERVER_PORT="${SERVER_PORT:-15000}"
DUMMY_PORTS="${DUMMY_PORTS:-28085,28086,28222}"

echo "========================================"
echo " FinalCombat 服务状态"
echo "========================================"

if [ -d "$PIDS" ]; then
  echo "[1] PID 文件:"
  for f in "$PIDS"/*.pid; do
    [ -e "$f" ] || continue
    name="$(basename "$f" .pid)"
    pid="$(cat "$f")"
    if kill -0 "$pid" 2>/dev/null; then
      echo "   $name -> pid=$pid (running)"
    else
      echo "   $name -> pid=$pid (stopped)"
    fi
  done
else
  echo "[1] PID 目录不存在"
fi

echo "[2] 端口检查:"
ports=("$AUTH_PORT" "$INFO_PORT" "$SERVER_PORT" 9011 9012 9013 9003 16000 11111)
IFS=',' read -r -a dummy_arr <<< "$DUMMY_PORTS"
for p in "${dummy_arr[@]}"; do
  ports+=("$p")
done
for port in "${ports[@]}"; do
  if ss -tlnp 2>/dev/null | grep -q ":$port "; then
    echo "   PORT $port: LISTEN"
  else
    echo "   PORT $port: -"
  fi
done

echo "[3] 登录接口:"
if command -v curl >/dev/null 2>&1; then
  set +e
  curl -s -X POST -H "Content-Type: application/json" -d '{}' "http://127.0.0.1:$AUTH_PORT/confirm" | sed -n '1,2p'
  set -e
else
  echo "   未检测到 curl"
fi

echo "========================================"

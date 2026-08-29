#!/bin/bash
# FinalCombat 一键停止脚本
set -e
BASE="$(cd "$(dirname "$0")" && pwd)"
LOG="$BASE/logs"
PIDS="$LOG/pids"

echo "========================================"
echo " FinalCombat 服务器停止"
echo "========================================"

echo "[1] 读取 PID 文件停止服务..."
shopt -s nullglob
files=("$PIDS"/*.pid)
if [ "${#files[@]}" -eq 0 ]; then
  echo "   未发现 PID 文件，尝试兜底按进程名停止..."
else
  for f in "${files[@]}"; do
    name="$(basename "$f" .pid)"
    pid="$(cat "$f")"
    if [ -z "$pid" ]; then
      rm -f "$f"
      continue
    fi
    if kill -0 "$pid" 2>/dev/null; then
      echo "   [stop] $name pid=$pid"
      kill -TERM "$pid" 2>/dev/null || true
      sleep 1
      if kill -0 "$pid" 2>/dev/null; then
        echo "   [force-kill] $name pid=$pid"
        kill -KILL "$pid" 2>/dev/null || true
      fi
    fi
    rm -f "$f"
  done
fi
shopt -u nullglob

for p in proxyserver channelserver matchingserver apexserver logserver login_server.py logserver_dummy.py; do
  pkill -f "$p" 2>/dev/null || true
done

echo "[2] 清理空端口占用（兜底）..."
for p in 8080 8081 8085 8086 2222 9003 9011 9012 9013 11111 15000 16000 18080 28085 28086 28222; do
  if ss -tlnp 2>/dev/null | grep -q ":$p "; then
    echo "   端口 $p 仍被占用，按需执行: sudo ss -ltnp | grep :$p"
  fi
done

echo "========================================"
echo " 停止完成"
echo "========================================"


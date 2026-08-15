#!/bin/bash
# FinalCombat 快速清理工具：清 memcached + 可选重启 jetty
# 用法: fc_clean.sh [jetty]   （带 jetty 参数则重启 info server）
set -e

# 1. 清 memcached（Python 连接明确关闭，不会卡住）
python3 - <<'EOF'
import socket
try:
    s = socket.create_connection(('127.0.0.1', 11211), timeout=3)
    s.sendall(b'flush_all\r\n')
    s.settimeout(3)
    try:
        data = s.recv(1024)
        print("memcached:", data.decode().strip())
    except socket.timeout:
        print("memcached: flushed (no response)")
    s.close()
except Exception as e:
    print("memcached flush error:", e)
EOF

# 2. 可选重启 jetty（带超时）
if [ "$1" = "jetty" ]; then
    echo "重启 jetty9..."
    timeout 60 sudo systemctl restart jetty9 || echo "jetty restart 超时，检查服务"
    # 等待 8081 就绪（最长 120 秒）
    for i in $(seq 1 24); do
        if ss -tlnp 2>/dev/null | grep -q 8081; then
            sleep 3
            echo "info server 8081 就绪"
            exit 0
        fi
        sleep 5
    done
    echo "8081 未就绪"
fi
echo "完成"

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""认证服务器：返回 token + 游戏地址，供客户端启动用。"""
import json
import time
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
import os

SERVER_IP = "192.168.31.5"
SERVER_PORT = 15000
LISTEN = ("0.0.0.0", int(os.environ.get("AUTH_LISTEN_PORT", "8080")))
SERVER_IP = os.environ.get("SERVER_IP", SERVER_IP)
SERVER_PORT = int(os.environ.get("SERVER_PORT", str(SERVER_PORT)))

class Handler(BaseHTTPRequestHandler):
    def log_message(self, *a):
        pass
    def _json(self, obj, code=200):
        body = json.dumps(obj).encode()
        self.send_response(code)
        self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)
    def do_POST(self):
        try:
            ln = int(self.headers.get("Content-Length", 0))
            self.rfile.read(ln)
        except Exception:
            pass
        if self.path.endswith("/prepare"):
            self._json({"ok": True, "login_ticket": "ticket-1", "ticket_expires_at": "2099-01-01T00:00:00.000Z",
                        "servers": [{"id": "s1", "server_id": "s1", "name": "Default",
                                     "game_ip": SERVER_IP, "game_port": SERVER_PORT}]})
        elif self.path.endswith("/confirm"):
            self._json({"ok": True, "token": "localtoken-" + str(int(time.time())),
                        "game_ip": SERVER_IP, "game_port": SERVER_PORT,
                        "server_id": "s1", "server_name": "local"})
        else:
            self._json({"ok": False, "message": "not found"}, 404)

if __name__ == "__main__":
    print("auth server on %s:%d -> %s:%d" % (LISTEN[0], LISTEN[1], SERVER_IP, SERVER_PORT))
    ThreadingHTTPServer(LISTEN, Handler).serve_forever()

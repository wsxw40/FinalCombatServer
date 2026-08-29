import socket, threading
import os

_ports = os.environ.get("LOGSERVER_DUMMY_PORTS", "28085,28086,28222")
def handle(c):
    try:
        while True:
            d = c.recv(65536)
            if not d: break
    except Exception: pass
    finally:
        c.close()
for port in map(int, _ports.split(",")):
    s = socket.socket(); s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind(('127.0.0.1', port)); s.listen(200)
    threading.Thread(target=lambda: [handle(c) for c in iter(lambda: None, None)], daemon=True).start()
    def accept_loop(sock=s):
        while True:
            try:
                c,_ = sock.accept(); threading.Thread(target=handle, args=(c,), daemon=True).start()
            except Exception: break
    threading.Thread(target=accept_loop, daemon=True).start()
    print('监听', port)
import time
while True: time.sleep(60)

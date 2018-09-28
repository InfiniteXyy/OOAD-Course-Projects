from http.server import HTTPServer, BaseHTTPRequestHandler
import json


class AntRequestHandler(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def do_GET(self):
        self._set_headers()
        self.wfile.write(b'{"a": 1}')


def main():
    httpd = HTTPServer(('', 8000), AntRequestHandler, HTTPServer)
    httpd.serve_forever()


if __name__ == '__main__':
    main()

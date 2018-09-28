from http.server import HTTPServer, SimpleHTTPRequestHandler


class AntRequestHandler(SimpleHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b'Hello World')

    def end_headers (self):
        self.send_header('Access-Control-Allow-Origin', '*')
        SimpleHTTPRequestHandler.end_headers(self)

httpd = HTTPServer(('localhost', 2616), AntRequestHandler)
httpd.serve_forever()
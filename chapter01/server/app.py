from http.server import HTTPServer, BaseHTTPRequestHandler
from pathlib import Path
import json
import os
from reader import *

parent_path = Path(os.getcwd()).parent


class AntRequestHandler(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def do_GET(self):
        self._set_headers()
        # os.system('../main/gradlew -p ../main run --args="demo" -q > tmp')
        gameList = readTempData()
        stages = []
        maxStep = 0
        minStep = 100000
        for i in gameList:
            singleGameData = {}
            singleGameData['directions'] = i.get_directions()
            step = i.get_steps()
            singleGameData['steps'] = step
            maxStep = max([maxStep, step])
            minStep = min([minStep, step])
            singleGameData['positions'] = i.get_positions()
            stages.append(singleGameData)
        data = {}
        data['antNumber'] = gameList[0].get_ant_numbers()
        data['startPosition'] = [30, 80, 110, 160, 250]
        data['stickLength'] = 300
        data['antSpeed'] = 5
        data['maxSteps'] = maxStep
        data['minSteps'] = minStep
        data['stages'] = stages

        self.wfile.write(bytes(json.dumps(data), encoding="utf8"))


def main():
    httpd = HTTPServer(('', 8000), AntRequestHandler, HTTPServer)
    httpd.serve_forever()


if __name__ == '__main__':
    main()

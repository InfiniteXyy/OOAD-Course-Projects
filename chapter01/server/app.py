from http.server import HTTPServer, BaseHTTPRequestHandler
from pathlib import Path
import json
import os
from reader import *
from urllib.parse import urlparse

parent_path = Path(os.getcwd()).parent


class AntRequestHandler(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def do_GET(self):
        self._set_headers()
        o = urlparse(self.path)
        gameConfig = dict(qc.split("=") for qc in o.query.split("&"))
        print(gameConfig)
        # gradle = '../main/gradlew -p ../main run --args="game {} {} {}" -q > tmp'.format(
        #     gameConfig['length'], gameConfig['velocity'], gameConfig['positions'].replace(
        #         ",", " ")
        # )
        command = 'java -jar ./OOAD-0.1.jar game {} {} {} > tmp'.format(
            gameConfig['length'], gameConfig['velocity'], gameConfig['positions'].replace(
                ",", " ")
        )
        print(command)
        os.system(command)
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
        data['startPosition'] = gameConfig['positions'].split(",")
        data['stickLength'] = gameConfig['length']
        data['antSpeed'] = gameConfig['velocity']
        data['maxSteps'] = maxStep
        data['minSteps'] = minStep
        data['stages'] = stages

        self.wfile.write(bytes(json.dumps(data), encoding="utf8"))


def main():
    httpd = HTTPServer(('', 8000), AntRequestHandler, HTTPServer)
    httpd.serve_forever()


if __name__ == '__main__':
    main()

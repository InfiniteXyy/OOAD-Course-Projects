class GameRecord:
    def __init__(self, directions):
        self.directions = directions
        self.stages = []
        self.steps = 0

    def add_stage(self, stage):
        self.stages.append(stage)

    def get_ant_numbers(self):
        return len(self.directions)

    def get_directions(self):
        return self.directions

    def get_steps(self):
        return len(self.stages)

    def get_positions(self):
        return self.stages

    def __str__(self):
        return "<Record: positions {}>".format(self.directions)


def parseList(data):
    return [int(x) for x in data.strip(r'[]').split(",")]


def readTempData():
    gameList = []
    with open('tmp', 'r') as f:
        for i in f.readlines():
            i = i.strip('\n')
            if i.startswith('directions: '):
                directionsList = parseList(i.split('directions: ')[1])
                record = GameRecord(directionsList)
                gameList.append(record)
            else:
                if i.startswith('['):
                    gameList[-1].add_stage(parseList(i))
    return gameList

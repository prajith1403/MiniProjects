import random

class CoinTossSimulator:
    
    __HEADS = 0
    __TAILS = 1

    def __init__(self):
        self.__trialCount = 0
        self.__twoHeads = 0
        self.__twoTails = 0
        self.__headsTails = 0

    def run(self, numTrials: int):
        for i in range(numTrials):
            self.__firstCoinToss = random.randint(0, 1)
            self.__secondCoinToss = random.randint(0, 1)
            if self.__firstCoinToss == self.__HEADS and self.__secondCoinToss == self.__HEADS:
                self.__twoHeads += 1
            elif self.__firstCoinToss == self.__TAILS and self.__secondCoinToss == self.__TAILS:
                self.__twoTails += 1
            else:
                self.__headsTails += 1
            self.__trialCount += 1
    
    def getNumTrials(self) -> int:
        return self.__trialCount

    def getTwoHeads(self) -> int:
        return self.__twoHeads

    def getTwoTails(self) -> int:
        return self.__twoTails

    def getHeadsTails(self) -> int:
        return self.__headsTails

    def reset(self):
        self.__trialCount = 0
        self.__twoHeads = 0
        self.__twoTails = 0
        self.__headsTails = 0
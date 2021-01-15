import random
import numpy as np

class SolitaireBoard:
    NUM_FINAL_PILES = 9
    CARD_TOTAL = int(NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2)
    __FINAL_PILE = [i for i in range(1, NUM_FINAL_PILES + 1)]

    __cardStack = np.empty(CARD_TOTAL + 1, dtype = object).tolist()
    __stackCapacity = 0

    def __init__(self, piles = None):
        if piles:
            for i in range(len(piles)):
                self.__cardStack[i] = piles[i]
                self.__stackCapacity += 1
            assert self.__isValidSolitaireBoard()
        else:
            cardTotalFlag = True
            sumOfPiles = 0
            while cardTotalFlag:
                if sumOfPiles < self.CARD_TOTAL:
                    self.__addCards(random.randint(0, self.CARD_TOTAL - sumOfPiles))
                else:
                    cardTotalFlag = False
                sumOfPiles = 0
                for i in range(self.__stackCapacity):
                    sumOfPiles += self.__cardStack[i]
            assert self.__isValidSolitaireBoard()
    
    def playRound(self):
        for i in range(self.__stackCapacity):
            self.__cardStack[i] -= 1
        self.__cardStack[self.__stackCapacity] = self.__stackCapacity
        self.__stackCapacity += 1
        assert self.__isValidSolitaireBoard()
        self.__zeroRemoval()

    def isDone(self) -> bool:
        assert self.__isValidSolitaireBoard()
        cardStackCopy = self.__cardStack[:self.__stackCapacity]
        cardStackCopy.sort()
        if cardStackCopy == self.__FINAL_PILE and self.__stackCapacity == self.NUM_FINAL_PILES:
            return True
        return False
    
    def configString(self) -> str:
        cardStackString = str(self.__cardStack[0])
        for i in range(1, self.__stackCapacity):
            cardStackString += " " + str(self.__cardStack[i])
        return cardStackString

    def __isValidSolitaireBoard(self) -> bool:
        sumOfPiles = 0
        for i in range(self.__stackCapacity):
            sumOfPiles += self.__cardStack[i]
        if sumOfPiles == self.CARD_TOTAL:
            return True
        return False
    
    def __addCards(self, cardNumber):
        if cardNumber > 0:
            self.__cardStack[self.__stackCapacity] = cardNumber
            self.__stackCapacity += 1
    
    def __zeroRemoval(self):
        nonZeroCount = 0
        for i in range(self.__stackCapacity):
            if self.__cardStack[i] != 0:
                self.__cardStack[nonZeroCount] = self.__cardStack[i]
                nonZeroCount += 1
        self.__stackCapacity = nonZeroCount
        assert self.__isValidSolitaireBoard()

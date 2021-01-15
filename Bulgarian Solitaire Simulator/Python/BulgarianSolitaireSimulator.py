import sys
import SolitaireBoard as SB

def userInput() -> list:
    isValidInput = False
    boardInputReader = []
    
    print("Number of total cards is", SB.SolitaireBoard.CARD_TOTAL)
    print("You will be entering the initial configuration of the cards (i.e., how many in each pile).")
    
    while(not isValidInput):
        isValidInt = True
        boardInputReader.clear()
        boardConfig = input("Please enter a space-separated list of positive integers followed by newline:")
        boardConfigArray = boardConfig.split()
        for i in range(len(boardConfigArray)):
            if boardConfigArray[i].isnumeric() and int(boardConfigArray[i]) != 0:
                boardInputReader.append(int(boardConfigArray[i]))
            else:
                isValidInt = False
        if not isValidInt:
            isValidInput = False
            print("ERROR: Each pile must have at least one card and the total number of cards must be",SB.SolitaireBoard.CARD_TOTAL)
        else:
            if sum(boardInputReader) == SB.SolitaireBoard.CARD_TOTAL:
                isValidInput = True
            else:
                isValidInput = False
                print("ERROR: Each pile must have at least one card and the total number of cards must be",SB.SolitaireBoard.CARD_TOTAL)

    return boardInputReader

if __name__ == '__main__':
    singleStep = False
    userConfig = False

    solitaireSteps = 0

    BulgarianSolitaireBoard = None
    
    for i in range(1, len(sys.argv[1:])+1):
        if sys.argv[i] == "-u":
            userConfig = True
        if sys.argv[i] == "-s":
            singleStep = True
    
    if userConfig:
        BulgarianSolitaireBoard = SB.SolitaireBoard(userInput())
        print("Initial configuration:", BulgarianSolitaireBoard.configString())
    else:
        BulgarianSolitaireBoard = SB.SolitaireBoard()
        print("Initial configuration:", BulgarianSolitaireBoard.configString())
    
    while not BulgarianSolitaireBoard.isDone():
        BulgarianSolitaireBoard.playRound()
        solitaireSteps += 1
        print("["+str(solitaireSteps)+"] Current configuration:", BulgarianSolitaireBoard.configString())
        if singleStep:
            input()

    print("Done!")
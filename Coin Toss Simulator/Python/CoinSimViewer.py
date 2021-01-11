import matplotlib.pyplot as plt
import pyinputplus as pyip
import CoinTossSimulator as CTS

def CoinSimHelper(numberOfTrials: int):
    coinToss = CTS.CoinTossSimulator()
    coinToss.run(numberOfTrials)

    headHeadTosses = coinToss.getTwoHeads()
    headTailTosses = coinToss.getHeadsTails()
    tailTailTosses = coinToss.getTwoTails()

    hhPercentage = '{0:.2%}'.format(float(headHeadTosses)/float(numberOfTrials))
    htPercentage = '{0:.2%}'.format(float(headTailTosses)/float(numberOfTrials))
    ttPercentage = '{0:.2%}'.format(float(tailTailTosses)/float(numberOfTrials))

    labelHH = "Two Heads: " + str(headHeadTosses) + "\n(" + str(hhPercentage) + ")"
    labelHT = "A Head and a Tail: " + str(headTailTosses) + "\n(" + str(htPercentage) + ")"
    labelTT = "Two Tails: " + str(tailTailTosses) + "\n(" + str(ttPercentage) + ")"

    xPoints = [labelHH, labelHT, labelTT]
    yPoints = [headHeadTosses, headTailTosses, tailTailTosses]
    plt.bar(xPoints, yPoints)
    plt.gcf().subplots_adjust(bottom=0.15)
    plt.show()
    

if __name__ == '__main__':
    numberOfTrials = pyip.inputNum('Enter number of trials: ', greaterThan = 0)
    CoinSimHelper(numberOfTrials)
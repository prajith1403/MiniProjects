# Coin Toss Simulator
## Introduction
A graphics-based program to simulate someone tossing a pair of coins some number of times, and display the results. For example, the user may request 10 trials. For each trial two coins are tossed. The program reports in bar graph form how many times the result was two heads, how many times it was two tails, and how many times it was one of each.

Initially the program will prompt for the number of trials to simulate (a trial is two coin tosses) on the console (i.e., terminal window), error checking that a positive value is entered.

Then it will run the simulation and display a 500x800 (HxW) pixel window with the results of that simulation. The results will consist of three labeled bars, each a different color, to show how many trials had the specified outcome. The label will show what the outcome was (e.g., Two Heads), the number of trials that had that result, and the percentage of trials that had that result (rounded to the nearest one percent). Because the simulation uses random coin tosses (simulated using a random-number generator) subsequent runs with the same input will produce different results.

## How to compile and run multi-file Java programs on the command line
Often you can just list the file that contains main in the compile command and javac figures out what other classes are used in that program and compiles those as well. However, sometimes the Java compiler gets confused when you only have modified some of the source files since the original compile. For running a program that uses multiple class files, the only class name you give as the argument to the java virtual machine is the one containing main.

When you are compiling and running your test program you should be able to do it as follows:

*javac CoinTossSimulator\*.java*
*java CoinTossSimulatorTester*

The wild-card in the compile command will match the two files *CoinTossSimulatorTester.java* and *CoinTossSimulator.java*.

For the larger program (for that one main that is in *CoinSimViewer.java*), you can either list all of the files it uses on the command line; but the following is a convenient shorthand:

*javac @CoinSimViewer.list*
*java CoinSimViewer*

The *CoinSimViewer.list* file just consists of the list of files to compile for the program. The @ on the command line tells java to look in the file that follows it find out what java files to compile. An alternate is to use \*.java instead in the compile command, although that one would also attempt to compile *CoinTossSimulatorTester.java*.

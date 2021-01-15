# CSCI 455x Programming Assignment 2
## Introduction
This is a program to model the game Bulgarian Solitaire. This program will run in the console window and will not have a GUI.
The game starts with 45 cards. (They need not be playing cards. Unmarked index cards work just as well.) Randomly divide them into some number of piles of random size. For example, you might start with piles of size 20, 5, 1, 9, and 10. In each round you take one card from each pile, forming a new pile with these cards. For example, the starting configuration would be transformed into piles of size 19, 4, 8, 9, and 5. The solitaire is over when the piles have size 1, 2, 3, 4, 5, 6, 7, 8, and 9, in some order.
In the normal mode of operation the program will produce a random starting configuration and print it. It then keeps applying the solitaire step and printing the results, and stops when the final configuration is reached.

To make it easier to test the code, the program will be able to be run in a few different modes, each of these controlled by a command-line argument. The user may supply one or both of the arguments, or neither.

**-u**

Prompts for the initial configuration from the user, instead of generating a random configuration.

**-s**

Stops between every round of the game. The game only continues when the user hits enter (a.k.a., return).

## How to run the program
Compile the program by executing:
*javac BulgarianSolitaireSimulator.java*

Here are a few examples of ways to run the program in the Linux shell:

*java -ea BulgarianSolitaireSimulator -u*

*java -ea BulgarianSolitaireSimulator -u -s*

*java -ea BulgarianSolitaireSimulator*

[Note: you are using the -ea argument for assertion-checking. The arguments after the program name are the ones that get sent to your program.]

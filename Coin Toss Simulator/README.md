# Coin Toss Simulator
## Introduction
A graphics-based program to simulate someone tossing a pair of coins some number of times, and display the results. For example, the user may request 10 trials. For each trial two coins are tossed. The program reports in bar graph form how many times the result was two heads, how many times it was two tails, and how many times it was one of each.

Initially the program will prompt for the number of trials to simulate (a trial is two coin tosses) on the console (i.e., terminal window), error checking that a positive value is entered.

Then it will run the simulation and display a 500x800 (HxW) pixel window with the results of that simulation. The results will consist of three labeled bars, each a different color, to show how many trials had the specified outcome. The label will show what the outcome was (e.g., Two Heads), the number of trials that had that result, and the percentage of trials that had that result (rounded to the nearest one percent). Because the simulation uses random coin tosses (simulated using a random-number generator) subsequent runs with the same input will produce different results.

The project was originally done in Java, but has since been converted to Python (same functionality)

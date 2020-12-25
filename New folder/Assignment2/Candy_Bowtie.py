#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
'''
Created on Sun Nov 3 2019
 
@author: Michelle M Khalife
'''


def PointDown(width):     # draw pointing down
    depth = width
    for i in range (depth):         # Iteration on depth and horizontal spaces
        print(i*' ', end="")
        for j in range (width):   # Iteration on width
            print('*' + ' ', end="")
        width = width -1        # Decrease no of stars by 1
        print()

def PointUp(width):     # draw pointing down
    depth = width
    for i in range (depth):     # Iteration on depth and horizontal spaces
        print((depth-i-1)*' ', end="")
        for j in range (i+1): # Iteration on width
            print('*' + ' ', end="")
        print()


# DRIVER CODE 

validInput = False
while (validInput == False):
    shape = str(input("Would you like to draw a candy or a bowtie? (c for candy, b for bowtie)\n"))
    validInput = shape.lower()== 'c' or shape.lower()== 'b'

validInput = False
while (validInput == False):
    width = int(input("How wide would you like your " + shape + "? (Min is 2) \n"))
    validInput = type(width) is int and width > 2

if (shape.lower() == 'c'):
    PointDown(width) 
    PointUp(width)
    PointDown(width) 
    PointUp(width)
elif (shape.lower() == 'b'):
    PointDown(width) 
    PointUp(width)
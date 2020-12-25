#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  15 12:50 2019
 
@author: Michelle M Khalife
"""

# A function to check whether or not a year is a leap year
# A leap year is divisible by 4, but not by 100 
# Otherwise, a leap year is divisible by 400
def isLeap(year):
    return (x%4==0 and x%100!=0) or (x%400==0)
    

# Driver Code
validInput = False
while (validInput == False):
    arg = str(input("Enter a year to check if or not leap: "))
    validInput = type(arg) is int 
    

if (isLeap(arg) == True):
    print(arg, "is a leap year\n")
else:
    print(arg, "is not a leap year\n")
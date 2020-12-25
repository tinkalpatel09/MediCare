#! usr/bin/env/python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  15 12:50 2019
 
@author: Michelle M Khalife
"""

def isVowel(c):  
    return c.lower() in ('aeiouy')      # Change c into lower case and check if in set of vowels
    

# Driver Code
validInput = False 
while (validInput == False):
    
    arg = str(input("Enter a letter to check its type: "))
    validInput = len(arg)==1 and (65<=ord(arg)<=90 or 97<=ord(arg)<=122)

if (isVowel(arg) == True):
    print(arg + " is a vowel\n")
else:
    print(arg + " is a consonant\n")


    # There are other valid ways to check whether or not the character is a vowel, some better than others
    #   isVowel_Basic = (c=="a" or c=="e" or c=="i" or c=="o" or c=="u" or c=='y'\      # Rudimentary 
    #                c=="A" or c=="E" or c=="I" or c=="O" or c=="U" or c=='Y')          # Call lower() to avoid redundant check
    #   isVowel_WithList = c.lower() in ['a', 'e', 'i', 'o', 'u', 'y']                  # List is better
    #   isVowel_WithSet = c.lower() in (['a', 'e', 'i', 'o', 'u', 'y'])                 # Set created from list 


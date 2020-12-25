#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Sun Nov 3
 
@author: Michelle M Khalife
"""

#Implement Fisher-Yates shuffle algorithm
#https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
# -- To shuffle an array a of n elements (indices 0..n-1):
# for i from n−1 downto 1 do
#     j ← random integer such that 0 ≤ j ≤ i
#     exchange a[j] and a[i]


import random

sentence = ([i for i in input("Enter a sentence to shuffle the order of the words\n").split(" ")])
print(sentence)

for i in range(len(sentence)-1, 0, -1): 
      
    j = random.randint(0, i + 1)  # call random on range (0, current range)
    sentence[i], sentence[j] = sentence[j], sentence[i]  # swap word at current index with word at random index
      
print(sentence)
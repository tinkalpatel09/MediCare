#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  15 12:50 2019
 
@author: Michelle M Khalife
"""

import random

# Loop 5 times / from i = 0 to 4
# lst = [x for x in range(1, 1000) if (x%5==0 and x%7==0)]
# i.e., for x in range 1 to 1001 (to capture 1000) add x to lst if x%5==0 && x%7==0
for i in range(5):  print(random.choice([x for x in range(1, 1001) if (x%5==0 and x%7==0)]) 

# Read a list of integers separated by a space from the terminal 
# Filter out the even integeres and print the rest
print([i for i in input().split(" ") if(int(i)%2!=0)])
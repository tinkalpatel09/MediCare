#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Nov 3 2019
 
@author: Michelle M Khalife
"""

# Code from https://www.sanfoundry.com/python-program-print-pascal-triangle/

n=int(input("Enter number of rows: "))
a=[]

for i in range(n+1):        # n+1 to include row n
    a.append([])            # append an empty list to a
    a[i].append(1)          # always start with the first elem in a row ie. a[i][0] = 1
    for j in range(1,i):    # now start at j =1 
        a[i].append(a[i-1][j-1]+a[i-1][j])  # sum of two elems in previous row (i-1) at positions j-1 and j
    if(n!=0):                               # check that we are past row 0
        a[i].append(1)                      # and terminate every row with 1

# Now print 
for i in range(n):
    for j in range(0,i+1):
        print(a[i][j],end=" ")
    print()

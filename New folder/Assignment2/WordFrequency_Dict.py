#! usr/bin/env/ python3
# -*- coding: utf-8 -*-
"""
Created on Sun Nov 3
 
@author: Michelle M Khalife
"""
'''

                            Online Python Compiler.
                Code, Compile, Run and Debug python program online.
Write your code in this editor and press "Run" button to execute it.

'''
def wordFrequency(myList):
    
    myDict = {}                                         # create the dictionary
    
    for i in range(len(myList)):
    
        if (myList[i] in myDict):                       # if key exists
            myDict[myList[i]] = myDict[myList[i]] +1    # retrieve the value and add 1
        else: 
            myDict[myList[i]] = 1                       # else insert the key with value =1
    
    return myDict        

def InvertDictPairs(myDict):

    dictCopy = myDict.copy()                # copy dict items in a temp dict / you cannot iterate and modift the dict at once
    
    for x,y in dictCopy.items():            # iterate over items in dict copy 
        myDict[y] = myDict.get(y,[])        
        myDict[y].append(x)                 # for each key in dictCopy, insert its value as key in myDict
    
    for x in dictCopy.keys():
        myDict.pop(x)                       # remove items with keys as words from original dict
    del dictCopy
    
    sortedDict = sorted(myDict.items())     # sorting returns a list ... dictionaries are unordered collections
    print(sortedDict)                       # sorting on items will sort by keys first then by values
    
    for key in sorted(myDict.keys()) :          # you can sort on keys and iterate over the sorted collection 
        print(key, " :: " , myDict[key])        # this will give you a sorted printout of the dict items
    
    maxFreqTuple = max(myDict.keys()), myDict[max(myDict.keys())]
    return (maxFreqTuple)                   # this will return the max frequency and a list of values if there are more than one.
    
    
    
# DRIVER CODE

someText    = ([i for i in input("Enter some text to count word frequency\n").split(" ")])
newDict     = wordFrequency(someText)
invertedDict = InvertDictPairs(newDict)

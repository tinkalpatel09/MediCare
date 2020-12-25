########################## Q-1
##Function for reverse traingle
def opptrian(num):
    row=num
    while row>0: 
        space = num-row
        while space>0:
            print(end=" ")
            space = space-1
        star = num-row-1
        while star<num-1:
            print("*",end=" ")
            star = star+1
        row = row-1
        print()

##Function for traingle
def trian(num):
    row = 0
    while row<num:
        space = num-row-1
        while space>0:
            print(end=" ")
            space = space-1
        star = row+1
        while star>0:
            print("*", end=" ")
            star= star-1
        row=row+1
        print() 

a=0
num= int(input("Enter the length of width: "))
turn= int(input("Enter the number of turns(1 for bowtie, 2 for candy,): "))


if turn == 1:
    opptrian(num)
    trian(num)

if turn == 2:
    opptrian(num)
    trian(num)
    opptrian(num)
    trian(num)






######################### Q-2  #A


def is_leapyear(year):

    temp=(year % 4 ==0) and (year % 100 !=0) or (year % 400 ==0)
   
    print(temp) 
    
year = int(input("Enter year: "))    

is_leapyear(year)


# b. Write a function is_vowel() that returns True or False depending on whether its arguments is a vowel or
# consonant



def is_vowel(vorc):
    vorc = vorc.upper()
    temp = vorc == "A" or vorc == "E" or vorc == "I" or vorc == "O" or vorc == "U"
    print(temp)
    
vorc = input("enter a char: ")    
is_vowel(vorc)



#########################  A  Q-3
import random
print (random.sample([i for i in range(1,1001) if i%5==0 and i%7==0], 5)) #print numbers between 1 to 1000 is divide by 5 and 7 


#########################  B
def removeEven(arr):
   return [ i for i  in arr if i % 2 != 0]  ##remove even number from mylist

mylist = [22,44,33,55,66,77]
print(removeEven(mylist))


#######################   Q-4
#import random
str = ["the", "string", "should", "be" ,"in" ,"a", "proper", "manner"]
listStr = list(str)
random.shuffle(listStr)
sentence = listStr
print("original string",str)
print("after suffled",sentence)

###################     Q-5

i=int(input("Enter number of rows: "))
a=[]
for n in range(i):
    a.append([])
    a[n].append(1)
    for k in range(1,n):
        a[n].append(a[n-1][k-1]+a[n-1][k])
    if(i!=0):
        a[n].append(1)
for n in range(i):
    print(end=" ",sep=" ")
    for k in range(0,n+1):
        print('{0:6}'.format(a[n][k]),end=" ",sep=" ")
    print()


###################     Q-6

data = [('sima','patel','may',1990),('janvi','patel','december',1995),('rima','patel','april',1991),('mira','rins','feburary',1999),('rims','yinsho','october',1994)]
print(sorted(data))



#################       Q-7
#####################   A.
def wordFrequencies(myList):
    myDictionary = {}
    for x in myList:
        myDictionary[x] = myList.count(x)  
    return (myDictionary)    


myList = ["Hi","This","is","a","paragraph","Hi","This","is","python"]
print("Original list: ",myList)
myDict = wordFrequencies(myList)
print("Dictionary with word frequencies : ",myDict)

#####################  B.


def invertDict(myDict):
    newDict = {}
    for key,value in myDict.items():
        newDict[value] = key
    return newDict

def sortDict(newDict):
    return[(i,newDict[i]) for i in sorted(newDict.keys())]

invertedDict = invertDict(myDict)
sortedDict = sortDict(invertedDict)

print("Sorted Dictionary: ",sortedDict)




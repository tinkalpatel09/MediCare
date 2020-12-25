# 1. Candy or Bowtie
# Write a program that draws a candy or a bowtie, given the following user specifications:
# - candy (c) or bowtie (b)
# - width (even or odd integer)
# - tight or relaxed pattern (default is tight, see first two diagrams)

##Function for reverse traingle
def bowtie(num):
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
def candy(num):
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
    bowtie(num)
    candy(num)

if turn == 2:
    bowtie(num)
    candy(num)
    bowtie(num)
    candy(num)



# 2. Boolean expressions. Do not use conditionals.
# a. Write a function is_leapyear() that returns True if a year is a leap year, and False otherwise. A leap year is
# divisible by 400 or by 4 but not by 100.

year = int(input("Enter year"))

def is_leapyear(year):

    temp=(year % 4 ==0) and (year % 100 !=0) or (year % 400 ==0)
   
    print(temp) 

is_leapyear(year)


# b. Write a function is_vowel() that returns True or False depending on whether its arguments is a vowel or
# consonant

words = input("Some words: ")

def is_vowel(words):
    words = words.upper()
    temp = words == "A" or words == "E" or words == "I" or words == "O" or words == "U"
    print(temp)
is_vowel(words)

# 3. List comprehension
# a. Use list comprehension to output 5 random numbers between 1 and 1000, divisible by 5 and 7.
import random
print (random.sample([i for i in range(1,1001) if i%5==0 and i%7==0],5))

# b. Use list comprehension to print a list after deleting even numbers.

mylist = [2,3,4,5,6,7,8,10,9]

def removeEven(arr):
   return [ i for i  in arr if i % 2 != 0]

print(removeEven(mylist))


# 4. Shuffle
    # Write a program that reads a sentence from the command line as a list of strings and returns the sentence in a shuffled
    # order. The strings in the list need to change positions in place: do not create a duplicate list
import random
sentence = "The strings in the list need to change positions in place: do not create a duplicate list"
listSentence = list(sentence)
random.shuffle(listSentence)
sentence = listSentence
print(sentence)

# 5. Pascal candy
# Write a program that prompts the user for an integer and builds and prints the equivalent Pascal candy. In a Pascal
# candy, every row may be computed from the previous row by adding adjacent pairs of values together. Using a two
# dimensional list (list of lists), Pascal’s rule stipulates that, for any non-negative integer n and any integer k, between 0
# and n, inclusive: a[n][k] = a[n-1][k-1] + a[n-1][k]. 

def pascalsTriangle(n):
    res = []
    for _ in range(n): 
        row = [1] 
        if res: 
            lastRow = res[-1] 
            row.extend([sum(pair) for pair in zip(lastRow, lastRow[1:])])
            row.append(1)
        res.append(row)
    return res

for i in pascalsTriangle(8):
     print(i)

# 6. Student Records
# Students have been registered in the Computer Science program on a first come, first served basis. But now that the
# dust has settled, the administration wants to sort the student records in alphabetical order. Each student record is a
# tuple consisting of the following information: (last name, first name, Month-of-birth, Year-of-birth). Write a function
# that return a sorted list of tuples on last name, first name, year, then month. 

t = [("last name","Tauriac"),("first name","Rose"),("Month of birth",1),("year of birth",1910)]
temp = t[2]
temp1 =t[3]
t[2]=temp1
t[3]=temp 
print(t)

# 7. Word Frequency
# a. Write a function called word_frequencies(myList) that accepts a list of strings called myList and returns a
# dictionary where the keys are the words from myList and the values are the number of times that word appears
# in myList.

mylist = ["apple","organe","banana","apple","papple","apple","banana"]
def frequencies(mylist):
    myDictonray = {}
    for x in mylist:
        myDictonray[x] = mylist.count(x)
    return myDictonray

myD = {};
myD = frequencies(mylist)
print(myD)


# b. Write a function to invert key and value pairs in the dictionary you constructed. Sort the dictionary according 
# to the new keys. The function should accept a dictionary as parameter and return a tuple with the most frequent word 
# and its frequency.

invert = {}
for k,v in myD.items():
    invert[v] = k
print(invert)

def sortedDict(arr):
    return [(k,arr[k]) for k in sorted(arr.keys())]

invertKey =()
invertKey = sortedDict(invert)
print(invertKey)


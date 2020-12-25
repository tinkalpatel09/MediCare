# list with EVEN and ODD number
#list = [11, 22, 33, 44, 55]

# print original list
#print("Original list:")
#print(list)

# loop to traverse each element in the list
# and, remove elements
# which are EVEN (divisible by 2)
def listfnc(ListInput):
    for i  in range(len(ListInput)-1):
	    if(i %2) == 0:
	     ListInput.pop(i)
    print(ListInput)   

def AddingMulItems():
    myList=[]
    

    for i in range(0,5):
        num = int(input("enter list: "))
        myList.append(num)
    return myList   
        
        
###################################################
List1=[]
List1=AddingMulItems()

print(List1)

result = [List1.pop(i)   for i in range(len(List1)-1)  if(i %2) == 0  ] 
print(result)


#listfnc(list)
# print list after removing EVEN elements
#print("list after removing EVEN numbers:")
#print(list)




##########################################
mylist = ["apple","organe","banana","apple","pinapple","apple","banana"]
def frequencies(mylist):
    myDictonray = {}
    for x in mylist:
        myDictonray[x] = mylist.count(x)
    return myDictonray

myD = {};
myD = frequencies(mylist)
print(myD)
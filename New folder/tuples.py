#mytuples = ()

#mytuples=(1,7,"blue")+(3,"while")
#print(len(mytuples))
mytuples = (1,7,"blue",4,"tinkal","rin")
print(mytuples[2:5])
print(mytuples[:2])
print(mytuples[2:])
print(mytuples[5])
mytuples = mytuples + ("azure",3,6)
print(mytuples)

for t in mytuples:
    mytuples.index(t)
    
for var in mytuples:
    print(var)
    
 ######factorial
def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)   
print(factorial(3))

#######return tuples from function
def favclr_and_number(who):
    if(who == "tinkal"):
        colour = "white"
        number = "7"
        return(colour,number)
    else:
        colour= "blue"
        number = 1
        return(colour,number)
favclr_and_number("riya") 
print(favclr_and_number("riya"))   

##################### write a program to sort tuples in a list by alphabetical order or numerical order , hint ag. [('items1',5),('items2','trees'),('items3',24.5)]
         
         
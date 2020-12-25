
# Python3 code to iterate over a list 
list=[1,2,3,4,5,6,7]
length=len(list)
counter=0
counter1=0
while(counter<length):
    counter1=0
    while(counter1<length):
        if(list[counter]==list[counter1]):
            ##print("same number")
         
        else:
            if(list[counter1]%list[counter]==0):
                print(str(list[counter1]) + "and" + str(list[counter]) +" are divisible")
                
        counter1 += 1
    counter += 1
    
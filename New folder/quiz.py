
a=int(input("Write the 1st number : "))
b=int(input("Write the 2nd number : "))
c=int(input("Write the 3rd number : "))

x=int(min(a,b,c))
y=int(max(a,b,c))
z=int((a+b+c)-x-y)


if(a==x and c==y):
    print("True, They are in ascending order")
elif(a==y and c==x):
    print("True,  They are in descending order")
else:
    print("False, they are not in ascending or descending order")







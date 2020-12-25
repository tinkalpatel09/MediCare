def myFun(x): 
   x[0] = 20
  
# Driver Code (Note that lst is modified 
# after function call. 
lst = [10, 11, 12, 13, 14, 15]  
myFun(lst); 
print(lst)  

def myFun(x): 
  
   # After below line link of x with previous 
   # object gets broken. A new object is assigned 
   # to x. 
   x = [20, 30, 40] 
  
# Driver Code (Note that lst is not modified 
# after function call. 
lst = [10, 11, 12, 13, 14, 15]  
myFun(lst); 
print(lst)  

def func1(list):
    print(list)
    list=[47,11]
    print(list)
    
fib=[1,2,3,4,5]
func1(fib)    
print(fib)


def func2(list):
    print(list)
    list +=[47,11]
    print(list)
    
fib=[1,2,3,4,5]
func2(fib)    
print(fib)

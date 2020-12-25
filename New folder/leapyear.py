def leap(i):
    if (i%4) == 0 and (i%400) == 0 :
        print("leap = True")
    else:
        print("leap = False")
        
        
    
year = int(input("enter a year: "))    
leap(year)
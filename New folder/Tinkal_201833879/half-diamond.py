####################### Q-1 ##########    
def diamond(number):
    r = 0                             #when row is equal to 0
    while r<number:
        space = number-r-1
        while space>0:                  #iteration on spaces
            print(end=" ")
            space = space-1
        star = r+1                    #iteration on stars
        while star>0:
            print("*", end="")
            star= star-1               #decrese number of stars by 1
        r=r+1
        print()
    r=number-1                              
    while r>0:                        #when row is more than 0
        space = number-r
        while space>0:               #iteration on spaces
            print(end=" ")
            space = space-1
        star = number-r-1                  #iteration on stars
        while star<number-1:
            print("*",end="")
            star = star+1
        r = r-1
        print()
diamond(5)


    
    
    
#############Q-4 ##########################
import random
def Randomwalk(xas,yas):          # function for randomwalk
  
    res=[]
    for i in range(xas):                  #iteration for fix the range
        res.append([])                          
    for i in res:
        for j in range(yas):
            i.append(0)
    pos=(xas//2,yas//2)              # pos getting the intersection point
    z=pos[0]
    v=pos[1]

    while z!=-1 and z!=xas and v!=-1 and v!=yas:
        res[z][v] += 1
        direction=random.randrange(1,5)  #going through the random direction using random method
        if direction==1:          
            v+=1
        elif direction==2:
            z+=1
        elif direction==3:
            v-=1
        else:
            z-=1
    for i in res:        
        print(i)


T= int(input("Enter the number of M: "))
S= int(input("Enter the number of N: "))
Randomwalk(T,S)

    
    
    
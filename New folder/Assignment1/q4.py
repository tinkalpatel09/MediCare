grid = []
#loop for each loop 
for row in range(5):
    #for each row,create a list that will 
    #represent an entire row
    (grid.append([]))
    
    #loop for each column
    for column in range(6):
     grid[row].append(row+column*5)
for x in range(5):
    print(grid[x])
        
    
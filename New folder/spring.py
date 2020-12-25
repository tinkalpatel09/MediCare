m = int(input("enter a month: "))
d = int(input("enter a day: "))
if(m in range (3,7)):
    if (m == 3):
        if (d in range (21,30)):
            print("Both conditions are True")
        else:
            print("false")
    if (m == 4):       
        if (d in range (1,30)):
                print("Both conditions are True")
        else:
            print("false")
    if (m == 5):       
        if (d in range (1,31)):
                print("Both conditions are True")
        else:
            print("false")
    if (m == 6):       
        if (d in range (1,20)):
                print("Both conditions are True")
        else:
            print("false")
else:
    print("Wrong month entered")

      
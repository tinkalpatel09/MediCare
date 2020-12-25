
length=int(input("Enter the total track(in Km): "))
ask=int(input("Have you just started the race ? \n press 1 for yes, press 0 for no : "))
laps=int((305/length)+1)
if (ask == 1):
    print(" you have to cover a distance of 305km for which you have to complete ",laps," laps")
else:
    done=int(input("Please enter the number of laps you have already completed : "))
    print("Hey racer, you have already covered ",done," laps and you just need to cover ",int(laps-done)," more tracks")
    

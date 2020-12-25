def wordfrequencies(wordlist):
    mydix = {}
    for x in wordlist:
       mydix = [wordlist.count(w) for w in wordlist]
     
    print("String\n" + wordstring +"\n")
    print("List\n" + str(wordlist) + "\n") 
    print(mydix) 
    
wordstring = 'it was the best of times it was the  summer '
wordstring += 'it was the age of wisdom it was the age of goodness'
wordlist = wordstring.split()

wordfrequencies(wordlist)



def long_substring(a):
    pc = ""                        # define previous string
    alphaorder = ""                # define  current string
    newString = ""                 # define new string      

    for char in a:              # start iteration for a string
        if pc <= char:
            alphaorder += char
            if len(alphaorder) > len(newString):  #compare current and new string
                newString = alphaorder
        else:
            alphaorder = char
        pc = char
    return '\nLongest substring in alphabetical order is: ' + newString

a = input("\nEnter your String: ")
var = long_substring(a)
print(var)


file = open("output.txt","a")
file.write(var)
file.close()
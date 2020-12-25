import sys
ls=(sys.argv[1:])
def string2floats(ls):
    lf=[]  
    for element in ls:
        lf.append(float(element))
    return lf
def positive_min(lf):
    minimum = float("inf")
    for i in range(0,len(lf)):
        if (minimum>lf[i] and lf[i]>0):
            minimum=lf[i]
    return minimum

positive_min(string2floats(ls))
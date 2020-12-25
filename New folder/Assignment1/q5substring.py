def substring(s1,s2,k):
    if(s1 in s2[k:]):
        return True
    else:
        return False
print(substring("rin","summer is gone",2))    
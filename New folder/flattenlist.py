def flattenArray(inputArray):      # O(N)
    flatList = []
    for item in inputArray:        # Loop over every element(array or not) in the array
        # If the element is nested array, join it with output flat array
        # else just add the element to the flat array
        flatList += item if type(item) is list else [item]
    return flatList

print(flattenArray([2,9,[2,1,13,2],8,[2,6]]) )
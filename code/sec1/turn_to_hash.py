arr1 = ['a','b','c','d']
arr2 = ['x','z','c']

hash1 = {element: True for element in arr1}
for i in range(len(arr2)):
    if hash1.get(arr2[i]):
        print(True)
print(hash1)

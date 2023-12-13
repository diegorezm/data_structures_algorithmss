from typing import List

array = [2,5,1,2,3,5,1,2,4]
# array = [2,1,1,2,3,5,1,2,4]

def get_first_repeated(arr: List[int]) -> int:
    seen = {}
    for i in range(len(arr)):
        if array[i] in seen:
            return array[i]
        seen[array[i]] = True
    return -1

def get_most_repeated(arr: List[int]) -> int:
    seen = {}
    counter = 0
    number = 0
    for i in range(len(arr)):
        if arr[i] in seen:
            seen[arr[i]] += 1
        else:
            seen[arr[i]] = 1
        if seen[arr[i]] > counter:
            counter = seen[arr[i]]
            number = arr[i]
    return number

print(get_first_repeated(array))
print(get_most_repeated(array))

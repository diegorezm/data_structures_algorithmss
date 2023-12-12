from typing import List

arr1 = [0,3,4,31]
arr2 = [4,6,30]

# O(n)
# MY SOLUTION
def merge(arr1: List[int], arr2: List[int]) -> List[int]:
    res = arr1 + arr2
    for i in range(len(res) - 1):
        if res[i] > res[i + 1]:
            aux = res[i]
            res[i] = res[i + 1]
            res[i + 1] = aux
    return res

# print(merge(arr1,arr2))


# TRYING HIS SOLUTION

def mergeArray(arr1: List[int], arr2: List[int]) -> List[int]:
    if len(arr1) <= 0:
        return arr2
    if len(arr2) <= 0:
        return arr1
    res = []
    i, j = 1, 1
    item1, item2 = arr1[0], arr2[0]
    while item1 != None or item2 != None:
        if item1 < item2:
            res.append(item1)
            item1 = arr1[i]
            i += 1
        else:
            res.append(item2)
            item2 = arr1[j]
            j += 1
    return res

print(mergeArray(arr1, arr2))

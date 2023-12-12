arr1 = [1,2,3,9]
arr2 = [1,2,4,4]
arr3 = [2,3,5, 6]
nums = [3,2,4]

def hasPairWithSum(arr: list[int], sum: int) -> bool: 
    seen = set()
    for i in range(len(arr)):
        if(arr[i] in seen):
            return True
        seen.add(sum - arr[i])
    return False

print(hasPairWithSum(nums,9))

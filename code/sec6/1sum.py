from typing import List

nums = [3,4,2]
target = 6
# 6 - 3 = 3

# 6 - 2 = 4
# 6 - 4 = 2

def sum(nums: List[int], target: int) -> List[int]: 
    seen = {}
    for i in range(len(nums)):
        if nums[i] in seen:
            return [seen[nums[i]], i]
        complement = target - nums[i]
        seen[complement] = i
        print(complement)
        print(seen)
    return []
print(sum(nums, target))

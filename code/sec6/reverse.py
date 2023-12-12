arr = [1,2,2,3,4,5,6,7,8,9,10,10,10,11,12,12,12,12]
m = {}

offset = len(arr) // 4
res = 0
for i in range(len(arr) - offset):
    if arr[i] == arr[i + offset]:
        res = arr[i]
print(res)


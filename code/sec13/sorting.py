def bubble_sort(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            if arr[i] > arr[j]:
                aux = arr[j]
                arr[j] = arr[i]
                arr[i] = aux
    return arr

def selection_sort(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        min_index = i
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index] ,arr[i]
    return arr

def insertion_sort(arr: list[int]) -> list[int]:
    for i in range(1, len(arr) - 1):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j+1] = arr[j]
            j = j - 1
        arr[j+1] = key
    return arr

def merge_sort(arr: list[int]):
    if len(arr) == 1:
        return arr
    m = len(arr) // 2
    left = arr[:m]
    right = arr[m:]
    left = merge_sort(left)
    right = merge_sort(right)
    return merge(left, right)

def merge(left: list[int], right:list[int]):
    sorted_arr = []
    i ,j = 0, 0
    while i < len(left) and j < len(right):
        if left[i] > right[j]:
            sorted_arr.append(right[j])
            j += 1
        else:
            sorted_arr.append(left[i])
            i += 1
    while i < len(left):
        sorted_arr.append(left[i])
        i += 1
    while j < len(right):
        sorted_arr.append(right[j])
        j += 1
    return sorted_arr

# print(bubble_sort([2,1,5,4,6,8,7,10,9,3]))
# print(selection_sort([2,1,5,4,6,8,7,10,9,3]))
# print(insertion_sort([99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0]))
print(merge_sort([2,1,5,4,6,8,7,10,9,3]))

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

# print(bubble_sort([2,1,5,4,6,8,7,10,9,3]))
# print(selection_sort([2,1,5,4,6,8,7,10,9,3]))
print(insertion_sort([99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0]))


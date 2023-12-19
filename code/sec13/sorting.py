def bubble_sort(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            if arr[i] > arr[j]:
                aux = arr[j]
                arr[j] = arr[i]
                arr[i] = aux
    return arr

def selection_sort(arr: list[int]) -> list[int]:
    for i in range(len(arr) -1 ):
        min_index = i
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index] ,arr[i]
    return arr

# print(bubble_sort([2,1,5,4,6,8,7,10,9,3]))
print(selection_sort([2,1,5,4,6,8,7,10,9,3]))


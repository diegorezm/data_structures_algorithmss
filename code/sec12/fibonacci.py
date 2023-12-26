from time import time
def fibonacciIter(n: int) -> int:
    prev, curr = 0, 1
    while n > 0:
        sum  = prev + curr
        prev = curr
        curr = sum
        n -= 1
    return prev

def fibonacci(n: int, cache: dict):
    if n in cache:
        return cache[n]
    if n < 2:
        return n
    else:
        cache[n] = fibonacci(n - 1, cache) + fibonacci(n - 2, cache)
        return cache[n]

def noCacheRecursive(n: int):
    if n < 2:
        return n
    return noCacheRecursive(n - 1) + noCacheRecursive(n - 2)

test_cases = 600

start = time()
fibonacciIter(test_cases)
end = time()
print(f"iter: {end - start}")

start = time()
fibonacci(test_cases , {})
end = time()
print(f"recursive with cache: {end - start}")

# start = time()
# noCacheRecursive(30)
# end = time()
# print(f"nocache: {end - start}")

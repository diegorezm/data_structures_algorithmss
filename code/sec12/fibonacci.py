def fibonacciIter(n: int) -> int:
    prev, curr = 0, 1
    while n > 0:
        sum  = prev + curr
        prev = curr
        curr = sum
        n -= 1
    return prev

def fibonacci(n: int):
    if n < 2:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)

print(fibonacciIter(6))
print(fibonacci(6))



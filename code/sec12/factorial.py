

def findFactorial(n: int) -> int:
    if n <= 1:
        return 1
    return n * findFactorial(n - 1)

def findFactorialNoRecursion(n: int) -> int:
    factorial = 1
    while 1 < n:
        factorial *= n
        n -= 1
    return factorial

print(findFactorial(5))
print(findFactorialNoRecursion(5))

    


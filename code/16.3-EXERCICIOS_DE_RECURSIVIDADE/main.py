def misterio(a: int, b: int):
    if b == 0:
        return a
    return a + misterio(a, b - 1)
def potencia(n: int, e: int):
    if e == 0:
        return 1
    return n * potencia(n, e - 1)

def soma_rec(a: int, b: int):
    if b == 0:
        return a
    return soma_rec(a + 1, b - 1)

def soma_iter(a: int, b: int):
    while b > 0 :
        a += 1
        b -= 1
    return a

def ex7(m: int , n: int):
    if n == 1:
        return m + 1
    elif m == 1:
        return n + 1
    return ex7(m, n - 1) + ex7(m - 1,n)

def mdc(x: int, y: int):
    if x == y:
        return x
    if x > y:
        return mdc(x - y, y)
    elif y > x:
        return mdc(y - x,x)

def soma_impares(n: int):
    if n == 0:
        return n
    if n % 2 > 0:
        return n + soma_impares(n - 1)
    return n - 1

def mod(n: int,m: int):
    if m > n:
        return n
    return mod(n - m, m)

def dec2hex(n: int, final_str = ""):
    conversion_table = {0: '0', 1: '1', 2: '2', 3: '3', 4: '4', 
                    5: '5', 6: '6', 7: '7', 
                    8: '8', 9: '9', 10: 'A', 11: 'B', 12: 'C', 
                    13: 'D', 14: 'E', 15: 'F'} 
    if n == 0:
        return final_str
    else:
        remainder = n % 16
        final_str = conversion_table[remainder] + final_str
        return dec2hex(n // 16 , final_str)

def fatorial(n: int):
    if n == 0:
        return 1
    return n * fatorial(n - 1)

def ex13(x: int):
    if x % 3 == 0:
        return x * x
    if x % 3 == 1:
        return x + 3
    if x % 3 == 2:
        return fatorial(x)
def puzzle(base: int, limit: int):
    if base > limit:
        return -1
    if base == limit:
        return 1
    return base * puzzle(base + 1, limit)

def ex15(n: int):
    if n == 1:
        return 1
    return n + ex15(n - 1)

def ex16(n: int):
    if n == 1:
        return 1
    if n % 2 == 0 :
        return -1/n + ex16(n - 1)
    else:
        return 1/n + ex16(n - 1)

def insertion_sort(arr: list[int]):
    return __insertion_sort(arr, len(arr))

def __insertion_sort(arr: list[int], n: int):
    if n < 1:
        return
    __insertion_sort(arr, n - 1)
    last_el = arr[n - 1]
    # tudo depois do ultimo elemento
    i = n - 2
    # Move todos os elemtos que são maiores que last_el para frente
    while i > 0 and arr[i] > last_el:
        arr[i + 1] = arr[i]
        i -= 1
    # Insere last_el na posição correta
    arr[i + 1] = last_el


def max_value(arr: list[int]):
    if len(arr) == 1:
        return arr[0]
    first_el = arr[0]
    if first_el > max_value(arr[1:]):
        return first_el
    else:
        return max_value(arr[1:])
print(misterio(2,1))

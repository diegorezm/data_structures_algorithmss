# Ex1
- Soma o valor de "a" com o valor de "b" até que "b" se torne 1, então retorna o valor final de "a"

# Ex2
- Este código gera um loop infinito pois a variavel "n" não muda quando passada como parametro. Para concertar:
```python
    else return n + soma(n-1);
```

# Ex3
```python
def potencia(n: int, e: int):
    if e == 0:
        return 1
    return n * potencia(n, e - 1)
```

# Ex4
- repetição:
```python
    funcao soma(int a, int b):
        enquanto b for maior que 0:
            a = a + 1
            b = b - 1
        return a
```
- recursiva:
 ```python
    funcao soma(int a, int b):
        se b for 0:
            return a
        senão:
            return soma(a + 1, b - 1)
```

# Ex5
- Retorna 3

# Ex6 
- Retorna 3

# Ex7
```python
def ex7(m: int , n: int):
    if n == 1:
        return m + 1
    elif m == 1:
        return n + 1
    return ex7(m, n - 1) + ex7(m - 1,n)
```

# Ex8
```python
def mdc(x: int, y: int):
    if x == y:
        return x
    if x > y:
        return mdc(x - y, y)
    elif y > x:
        return mdc(y - x,x)
```
## Ex9
```python
def potencia(n: int, x: int);
    if x == 0:
        return 1
    return n * potencia(n, x - 1)

```
## Ex10
```python
def soma_impares(n: int):
    if n == 0:
        return n
    if n % 2 != 0:
        return n + soma_impares(n - 1)
    return n - 1
```
# Ex11 
```python
def mod(n: int,m: int):
    if m > n:
        return n
    return mod(n - m, m)
```
# Ex12
- python:
    ```python
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
    ```
- java:
    ```java
    public class Dec2Hex {

      private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

      public static String dec2hex(int n, String hexString) {
        if (n == 0) {
          return hexString;
        }

        int remainder = n % 16;
        hexString = HEX_ARRAY[remainder] + hexString;
        return dec2hex(n / 16, hexString);
      }

      public static void main(String[] args) {
        int decimal = 255;
        String hexString = dec2hex(decimal, "");  // Start with empty string
        System.out.println("Decimal: " + decimal + ", Hexadecimal: " + hexString);
      }
    }
    ```

## Ex13
```python
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
```
# Ex14
```java
public int puzzle(int base, int limit) 
{ 
    if ( base > limit ) return -1; 
    else if ( base == limit ) return 1; 
    else return base * puzzle(base + 1, limit); 
} 
```
a: quando a base for maior que limite e quando a base for igual ao limite.
b: a recursividade acontece quando as duas condições para solução trivial não são atendidas (caindo no else)
c:
    - a: -1
    - b: 120
    - c: 1
# Ex15
```python
def ex15(n: int):
    if n == 1:
        return 1
    return n + ex15(n - 1)

```
# Ex16
```python
def ex16(n: int):
    if n == 1:
        return 1
    return n - ex16(n - 1)
```
# Ex17
- F(3) = 9
- F(7) = 23

# Ex18
```python
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
```

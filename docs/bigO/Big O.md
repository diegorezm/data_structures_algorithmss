# Big O?

**BIG O:** as  the number of elements increases, how many more operations do i have to do?
# Time complexity

**O(n)** or **linear time**: This means that as the input size (n) increases, the running time will increase proportionally.

**O(1)** or **constant time**: It will consistently perform a fixed number of operations no matter the size of the array.

**O(n^2)**: Nested loops, means an algorithm's time grows quadratically with the input size.

# Rules

1. **Worst-case scenario**: Always assume that the algorithm WILL take the maximum possible amount of time or resources.
    
2. **Remove constants**: Always ignore fixed numbers or coefficients when describing a algorithm's performance.
    
3. **Different terms for input**: Lets say you have 2 arrays that you have to loop through inside of the same function, the O notation will go from O(n) to O(a + b) to account for the size of the 2 arrays.
    
4. **Drop non dominants**: Always keep the dominant term, the term that will consume the most amount of time/resources. Lets say a function has 2 loops, one of them is O(n) and the other one O(n^2), you could say this function has a O(n + n^2) but because of this law you only consider the O(n^2).

# Space complexity 

## Space complexity?
The space complexity of an algorithm is simply the amount of memory required to run it.

What causes it:
 - Variables
 - Data structures 
 - Function calls
 - Allocations

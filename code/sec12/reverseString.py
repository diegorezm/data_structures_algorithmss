def reverseString(word: str):
    if len(word) < 2:
        return word
    char = word[-1]
    newWord = word[:len(word) - 1]
    return char + reverseString(newWord)

print(reverseString("hello world"))

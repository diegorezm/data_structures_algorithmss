from typing import Optional, Union

class Node():
    def __init__(self, value: Union[int, str]):
        self.value = value
        self.next: Optional[Node] = None

class Stack():
    def __init__(self) -> None:
        self.top: Optional[Node] = None
        self.bottom: Optional[Node] = None
        self.size = 0

    def push(self, value: Union[int, str]):
        node = Node(value)
        if self.top is None:
            self.top = node
            self.bottom = node
        else:
            node.next = self.top
            self.top = node
        self.size += 1

    def peek(self):
        if self.top is not None:
            return self.top.value
        return None

    def pop(self):
        if self.top is not None:
            num = self.top.value
            prev = self.top.next
            self.top = prev
            self.size -= 1
            return num
        return None

    def __repr__(self):
        if self.top is None:
            return f"size: {self.size}"
        nodes = []
        current = self.top
        while current:
            nodes.append(str(current.value))
            current = current.next
        return ' -> '.join(nodes) + " -> None \nsize: " + f"{self.size}"

class StackArray():
    def __init__(self):
        self.data = []
        self.length = 0
    def push(self, val):
        self.length += 1
        return self.data.insert(0,val)
    def peek(self):
        if self.length < 1:
            return None
        return self.data[self.length - 1]
    def pop(self):
        if self.length < 1:
            return None
        self.length -= 1
        return self.data.pop()

    def __repr__(self) -> str:
        if self.length == 0:
            return 'None \nsize: 0'
        return ' -> '.join(map(str, self.data)) + " -> None \nsize: " + str(self.length)

stack = Stack()
stack.push("google")
stack.push("youtube")
stack.push("chatgpt")
stack.pop()
stack.pop()
print(stack)

stackArray = StackArray()
stackArray.push("google")
stackArray.push("chatgpt")
stackArray.push("youtube")
stackArray.push("twitter")
print(stackArray)



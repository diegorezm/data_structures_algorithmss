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
            return num
        return None

    def __repr__(self):
        nodes = []
        current = self.top
        while current:
            nodes.append(str(current.value))
            current = current.next
        return ' -> '.join(nodes) + " -> None"

stack = Stack()
stack.push("google")
stack.push("youtube")
stack.push("chatgpt")
print(stack)

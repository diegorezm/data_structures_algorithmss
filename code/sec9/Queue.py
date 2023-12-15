from typing import Optional, Union

class Node():
    def __init__(self, value: Union[int, str]):
        self.value = value
        self.next: Optional[Node] = None

class Queue():
    def __init__(self) -> None:
        self.first: Optional[Node] = None
        self.last: Optional[Node] = None
        self.size = 0

    def peek(self):
        if self.first is not None:
            return self.first.value
        return None

    def enqueue(self, value: Union[int, str]):
        node = Node(value)
        if self.last is None:
            self.first = node
            self.last = node 
        else:
            self.last.next = node
            self.last = self.last.next
        self.size += 1

    def dequeue(self):
        if self.first is not None:
            value = self.first.value
            if self.size == 1:
                self.last = None
                self.first = None
            else:
                self.first = self.first.next
            self.size -= 1
            return value
        return None


    def __repr__(self):
        if self.first is None:
            return f"size: {self.size}"
        nodes = []
        current = self.first
        while current:
            nodes.append(str(current.value))
            current = current.next
        return ' -> '.join(nodes) + " -> None \nsize: " + f"{self.size}"

class ArrayQueue():
    def __init__(self):
        self.queue = []
    def peek(self):
        return self.queue[0]
    def enqueue(self,value: Union[int, str]):
        return self.queue.append(value)
    def dequeue(self):
        return self.queue.pop(0)
    def __repr__(self) -> str:
        return ' -> '.join(map(str, self.queue)) + " -> None \nsize: " + f"{len(self.queue)}"

queue = Queue()
queue.enqueue(6)
queue.enqueue(5)
queue.enqueue(2)
queue.enqueue(3)
queue.enqueue(1)
queue.dequeue()
queue.dequeue()
queue.dequeue()
queue.dequeue()
queue.dequeue()
print(queue)

from typing import List, Optional, Union

class Node:
    def __init__(self, value):
        self.value = value
        self.next: Optional[Node] = None

    def __repr__(self):
        return str(self.value)

class LinkedList:
    def __init__(self):
        self.head: Optional[Node] = None
        self.tail: Optional[Node] = None
        self.size = 0

    def insert(self, idx: int, value: int):
        if idx >= self.size:
            self.append(value)
            return

        node = Node(value)
        leader = self.to_index(idx - 1)

        if leader is not None:
            holder = leader.next
            leader.next = node
            node.next = holder
            self.size += 1

    def remove(self, idx: int):
        if self.head is not None:
            if idx == 0:
                self.head = self.head.next
                return 
            leader = self.to_index(idx - 1)
            if leader is None or leader.next is None:
                return
            el = leader.next
            leader.next = el.next
            if el == self.tail:
                self.tail = leader
            self.size -= 1
        else:
            raise TypeError("ERROR: head of linked list is of type None!")
            
    def append(self, value):
        node = Node(value)
        if not self.head:
            self.head = node
            self.tail = node
        else:
            if self.tail:
                self.tail.next = node
                self.tail = node
            else:
                raise ValueError("Unexpected condition: self.tail is None")
        self.size += 1

    def prepend(self, value):
        node = Node(value)
        if not self.head:
            self.head = node
            self.tail = node
        else:
            node.next = self.head
            self.head = node
        self.size += 1

    def to_index(self,idx: int) -> Optional[Node]:
        if idx < 0:
            raise ValueError(f"ERROR: index out of bound!")
        if idx >= self.size:
            return self.tail
        i = 0
        current = self.head

        while current is not None:
            if i == idx:
                return current
            current = current.next
            i += 1
        return None
    # 1 -> 2 -> 3
    def reverse(self) -> List[int]:
        current = self.head
        reversed_list = []
        while current:
            reversed_list.append(current.value)
            current = current.next
        return reversed_list[::-1]

    def __repr__(self, values: Union[List[int], None] = None):
        nodes = []
        if values is not None:
            nodes.extend(str(val) for val in values)
        else:
            current = self.head
            while current:
                nodes.append(str(current.value))
                current = current.next
        return ' -> '.join(nodes) + " -> None"


list = LinkedList()
list.append(10)
list.insert(1,20)
list.insert(2,18)
reversed = list.reverse()
print(list)

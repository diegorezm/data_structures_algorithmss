from typing import Optional


class Node:
    def __init__(self, value):
        self.value = value
        self.next: Optional[Node] = None
        self.prev_node: Optional[Node] = None

    def __repr__(self):
        return str(self.value)

class DoublyList:
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

        if leader is not None and leader.next is not None:
            follower = leader.next
            leader.next = node
            node.prev_node = leader
            node.next = follower
            follower.prev_node = node
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
            if el.next is not None:
                follower = el.next
                leader.next = follower
                follower.prev_node = leader
            else:
                leader.next = None
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
                node.prev_node = self.tail
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
            self.head.prev_node = node
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
    def reverse(self):
        if self.tail is not None:
            reverse = []
            current = self.tail
            while current:
                reverse.append(current)
                current = current.prev_node
            return reverse
        else:
            raise TypeError("ERROR: Tail is none.")


    def __repr__(self):
        nodes = []
        current = self.head
        while current:
            nodes.append(str(current.value))
            current = current.next
        return ' -> '.join(nodes) + " -> None"


list = DoublyList()
list.append(10)
list.append(20)
list.prepend(18)
list.insert(3,90)
list.remove(3)
print(list.reverse())
print(list)


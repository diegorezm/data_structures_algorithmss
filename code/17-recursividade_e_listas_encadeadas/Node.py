from typing import Optional

class Node:
    def __init__(self, val: int) -> None:
        self.val = val
        self.next: Optional[Node] = None

    def push(self, val: int):
        if self.next is None:
            self.next = Node(val)
        else:
            self.next.push(val)

    def show_all(self):
        print(f"{self.val} -> ", end= "")
        if self.next:
            self.next.show_all()
        else:
            return

    def sum(self) -> int:
        if self.next is None:
            return self.val
        return self.val + self.next.sum()

    def max(self):
        if self.next == None:
            return self.val
        if self.val > self.next.max():
            return self.val
        return self.next.max()

    def find(self, val: int):
        if val == self.val:
            return True
        if self.next is not None:
            return self.next.find(val)
        return False

    def print_even(self):
        aux = self.val if self.val % 2 == 0  else None
        if aux:
            print(aux)
        if self.next is not None:
            self.next.print_even()

    def last_value(self):
        if self.next is None:
            return self.val
        else:
            return self.next.last_value()

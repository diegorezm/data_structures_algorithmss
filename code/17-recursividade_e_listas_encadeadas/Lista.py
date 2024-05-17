from typing import Optional
from Node import Node

class Lista:
    def __init__(self) -> None:
        self.top: Optional[Node] = None

    def push(self,val: int):
        if self.top is None:
            self.top = Node(val)
        else:
            self.top.push(val)

    def pop(self):
        if self.top:
            aux = self.top.val
            if self.top.next:
                self.top = self.top.next
            else:
                self.top = None
            return aux
        return None

    def show(self):
        if self.top:
            self.top.show_all()

    ## This code does not work on python for some reason,
    ## but it works on java so its fine.
    def sum(self):
        if self.top:
            return self.top.sum
        return None

    def find(self, val: int):
        if self.top:
            return self.top.find(val)
        return None
    
    def max(self):
        if self.top:
            return self.top.max()
        return None

    def print_even(self):
        if self.top:
            self.top.print_even()

    def last_value(self):
        if self.top:
            return self.top.last_value()

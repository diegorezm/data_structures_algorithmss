from typing import Optional

class Node():
    def __init__(self, value: int) -> None:
        self.left: Optional[Node] = None
        self.right: Optional[Node] = None
        self.value = value

class BinaryTree():
    def __init__(self) -> None:
        self.root: Optional[Node] = None

    def insert(self,value: int):
        if self.root is None:
            self.root = Node(value)
        else:
            curr = self.root
            while True:
                if value < curr.value:
                    if curr.left is None:
                        curr.left = Node(value)
                        break
                    curr = curr.left
                else:
                    if curr.right is None:
                        curr.right = Node(value)
                        break
                    curr = curr.right
        return self

    def lookup(self,value: int):
        if self.root is not None:
            curr = self.root
            while True:
                if curr is not None:
                    if curr.value == value:
                        return curr
                    if curr.value > value:
                        curr = curr.left
                    else:
                        curr = curr.right
                else:
                    return None
        else:
            return None

    def remove(self, value):
        parent = None
        current = self.root

        while current is not None:
            if value == current.value:
                if current.left is None and current.right is None:  # Node to remove has no children
                    if parent is None:
                        self.root = None
                    elif parent.left == current:
                        parent.left = None
                    else:
                        parent.right = None
                elif current.left is None:  # Node to remove has only right child
                    if parent is None:
                        self.root = current.right
                    elif parent.left == current:
                        parent.left = current.right
                    else:
                        parent.right = current.right
                elif current.right is None:  # Node to remove has only left child
                    if parent is None:
                        self.root = current.left
                    elif parent.left == current:
                        parent.left = current.left
                    else:
                        parent.right = current.left
                else:  # Node to remove has both left and right children
                    successor_parent = current
                    successor = current.right
                    while successor.left is not None:
                        successor_parent = successor
                        successor = successor.left

                    current.value = successor.value
                    if successor_parent.left == successor:
                        successor_parent.left = successor.right
                    else:
                        successor_parent.right = successor.right
                return  # Node removed, exit loop

            elif value < current.value:
                parent = current
                current = current.left
            else:
                parent = current
                current = current.right
        # Value not found
        return

    def tree_to_dict(self, node):
        if node is None:
            return None
        tree_dict = {
            "value": node.value,
            "left": self.tree_to_dict(node.left),
            "right": self.tree_to_dict(node.right)
        }
        return tree_dict

    def __repr__(self):
        if self.root is None:
            return "Empty BinaryTree"

        tree_dict = self.tree_to_dict(self.root)
        return f"BinaryTree: {tree_dict}"



tree = BinaryTree()
tree.insert(20)
tree.insert(10)
tree.insert(20)
tree.insert(30)
print(tree)

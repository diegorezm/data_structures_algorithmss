package avl

import "fmt"

type avlTree struct {
	Root *avlNode
}

func NewAvlTree() *avlTree {
	return &avlTree{Root: nil}
}

func (t *avlTree) Search(key int32) bool {
	return t.search(t.Root, key)
}

func (t *avlTree) search(node *avlNode, key int32) bool {
	if node == nil {
		return false
	}
	if key < node.key {
		return t.search(node.left, key)
	} else if key > node.key {
		return t.search(node.right, key)
	} else {
		return true
	}
}

func (t *avlTree) Insert(key int32) {
	t.Root = t.insert(t.Root, key)
}

func (t *avlTree) insert(node *avlNode, key int32) *avlNode {
	if node == nil {
		return newAvlNode(key)
	}
	if key < node.key {
		node.left = t.insert(node.left, key)
	} else if key > node.key {
		node.right = t.insert(node.right, key)
	} else {
		fmt.Println("key already exists")
		return node
	}
	node = t.balance(node)
	return node
}

func (t *avlTree) Delete(key int32) {
	t.Root = t.delete(t.Root, key)
}

func (t *avlTree) delete(node *avlNode, key int32) *avlNode {
	if node == nil {
		return nil
	}

	if key < node.key {
		node.left = t.delete(node.left, key)
	} else if key > node.key {
		node.right = t.delete(node.right, key)
	} else {
		// Node found
		if node.left == nil && node.right == nil { // No children
			node = nil
		} else if node.left == nil { // One child (right)
			node = node.right
		} else if node.right == nil { // One child (left)
			node = node.left
		} else { // Two children
			temp := t.getMinimum(node.right)            // Find in-order successor
			node.key = temp.key                         // Replace with successor's key
			node.right = t.delete(node.right, temp.key) // Delete successor
		}
	}

	if node == nil {
		return nil
	}

	// Update height and balance the tree
	t.updateHeight(node)
	node = t.balance(node)

	return node
}

func (t *avlTree) PreOrder() {
	t.preOrder(t.Root)
}

func (t *avlTree) preOrder(node *avlNode) {
	if node != nil {
		fmt.Println(node.key)
		t.preOrder(node.left)
		t.preOrder(node.right)
	}
}

func (t *avlTree) InOrder() {
	t.inOrder(t.Root)
}

func (t *avlTree) inOrder(node *avlNode) {
	if node != nil {
		t.inOrder(node.left)
		fmt.Println(node.key)
		t.inOrder(node.right)
	}
}

func (t *avlTree) balance(node *avlNode) *avlNode {
	t.updateHeight(node)
	balance := t.calculateBalance(node)

	if balance > 1 {
		if t.calculateBalance(node.right) < 0 {
			node.right = t.rotateRight(node.right)
		}
		return t.rotateLeft(node)
	} else if balance < -1 {
		if t.calculateBalance(node.left) > 0 {
			node.left = t.rotateLeft(node.left)
		}
		return t.rotateRight(node)
	}
	return node
}

func (t *avlTree) calculateBalance(node *avlNode) int32 {
	if node == nil {
		return 0
	}
	return t.getHeight(node.right) - t.getHeight(node.left)
}

func (t *avlTree) rotateLeft(node *avlNode) *avlNode {
	newRoot := node.right
	node.right = newRoot.left
	newRoot.left = node
	t.updateHeight(node)
	t.updateHeight(newRoot)
	return newRoot
}

func (t *avlTree) getMinimum(node *avlNode) *avlNode {
	current := node
	for current.left != nil {
		current = current.left
	}
	return current
}

func (t *avlTree) rotateRight(node *avlNode) *avlNode {
	newRoot := node.left
	node.left = newRoot.right
	newRoot.right = node
	t.updateHeight(node)
	t.updateHeight(newRoot)
	return newRoot
}

func (t *avlTree) updateHeight(node *avlNode) {
	node.height = t.getHeight(node)
}

func (t *avlTree) getHeight(node *avlNode) int32 {
	if node == nil {
		return 0
	}
	return 1 + max(t.getHeight(node.left), t.getHeight(node.right))
}

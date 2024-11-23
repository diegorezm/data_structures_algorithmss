package avl

type avlNode struct {
	key    int32
	height int32
	left   *avlNode
	right  *avlNode
}

func newAvlNode(key int32) *avlNode {
	return &avlNode{key: key, height: 0, left: nil, right: nil}
}

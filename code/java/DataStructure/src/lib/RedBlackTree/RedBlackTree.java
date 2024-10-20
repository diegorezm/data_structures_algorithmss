package lib.RedBlackTree;

public class RedBlackTree {
  private final boolean RED = true;
  private final boolean BLACK = false;
  private RedBlackNode root;

  public void insert(int val) {
    RedBlackNode root = this.root;
    RedBlackNode parent = null;

    // Traverse the tree to the left or right depending on the key
    while (root != null) {
      parent = root;
      if (val < root.getValue()) {
        root = root.getLeft();
      } else if (val > root.getValue()) {
        root = root.getRight();
      } else {
        throw new IllegalArgumentException("BST already contains a node with key " + val);
      }
    }
    var newNode = new RedBlackNode(val);
    newNode.setColor(RED);
    if (parent == null) {
      this.root = newNode;
    } else if (val < parent.getValue()) {
      parent.setLeft(newNode);
    } else {
      parent.setRight(newNode);
    }
    newNode.setParent(parent);
    this.fixRedBlackPropertiesAfterInsert(newNode);
  }

  public RedBlackNode searchNode(int key) {
    RedBlackNode node = root;
    while (node != null) {
      if (key == node.getValue()) {
        return node;
      } else if (key < node.getValue()) {
        node = node.getLeft();
      } else {
        node = node.getRight();
      }
    }
    return null;
  }

  public void transversePostOrder() {
    postOrder(root);
  }

  public void transverseInOrder() {
    inOrder(root);
  }

  public void transversePreOrder() {
    preOrder(root);
  }

  private void postOrder(RedBlackNode node) {
    if (node == null) {
      return;
    }
    postOrder(node.getLeft());
    postOrder(node.getRight());
    System.out.println(node.getValue());
  }

  private void inOrder(RedBlackNode node) {
    if (node == null) {
      return;
    }
    inOrder(node.getLeft());
    System.out.println(node.getValue());
    inOrder(node.getRight());
  }

  private void preOrder(RedBlackNode node) {
    if (node == null) {
      return;
    }
    System.out.println(node.getValue());
    preOrder(node.getLeft());
    preOrder(node.getRight());
  }

  // During the repair, we have to deal with five different cases:
  //
  // Case 1: New node is the root
  // Case 2: Parent node is red and the root
  // Case 3: Parent and uncle nodes are red
  // Case 4: Parent node is red, uncle node is black, inserted node is "inner
  // grandchild"
  // Case 5: Parent node is red, uncle node is black, inserted node is "outer
  // grandchild"
  private void fixRedBlackPropertiesAfterInsert(RedBlackNode node) {
    var parent = node.getParent();
    // Case 1
    if (parent == null) {
      node.setColor(BLACK);
      return;
    }
    if (parent.getColor() == BLACK) {
      return;
    }
    // From here on, parent is red
    var grandparent = parent.getParent();

    // Case 2:
    // Not having a grandparent means that parent is the root. If we enforce black
    // roots
    // (rule 2), grandparent will never be null, and the following if-then block can
    // be
    // removed.
    if (grandparent == null) {
      // As this method is only called on red nodes (either on newly inserted ones -
      // or -
      // recursively on red grandparents), all we have to do is to recolor the root
      // black.
      parent.setColor(BLACK);
      return;
    }

    // Get the uncle (may be null/nil, in which case its color is BLACK)
    var uncle = getUncle(grandparent);

    // Case 3: Uncle is red -> recolor parent, grandparent and uncle
    if (uncle != null && uncle.getColor() == RED) {
      grandparent.setColor(RED);
      parent.setColor(BLACK);
      uncle.setColor(BLACK);

      // Call recursively for grandparent, which is now red.
      // It might be root or have a red parent, in which case we need to fix more...
      fixRedBlackPropertiesAfterInsert(grandparent);
    }
    // Parent is left child of grandparent

    else if (parent == grandparent.getLeft()) {
      // Case 4a: Uncle is black and node is left->right "inner child" of its
      // grandparent
      if (node == parent.getRight()) {
        rotateLeft(parent);

        // Let "parent" point to the new root node of the rotated sub-tree.
        // It will be recolored in the next step, which we're going to fall-through to.
        parent = node;
      }

      // Case 5a: Uncle is black and node is left->left "outer child" of its
      // grandparent
      rotateRight(grandparent);

      // Recolor original parent and grandparent
      parent.setColor(BLACK);
      grandparent.setColor(RED);
    } else {
      // Case 4b: Uncle is black and node is right->left "inner child" of its
      // grandparent
      if (node == parent.getLeft()) {
        rotateRight(parent);

        // Let "parent" point to the new root node of the rotated sub-tree.
        // It will be recolored in the next step, which we're going to fall-through to.
        parent = node;
      }

      // Case 5b: Uncle is black and node is right->right "outer child" of its
      // grandparent
      rotateLeft(grandparent);

      // Recolor original parent and grandparent
      parent.setColor(BLACK);
      grandparent.setColor(RED);
    }
  }

  // Red-Black Tree Deletion
  // Here is a summary:
  // If the node has no children, remove it.
  //
  // If the node has one child, remove the node and let its
  // single child move up to its position.
  //
  // If the node has two children, we copy the content (not the
  // color!) of the in-order successor of the right child into the node to be
  // deleted and then delete the in-order successor according to rule 1 or 2 (the
  // in-order successor has at most one child by definition).
  // After that, we need to check the rules of the tree and repair it if
  // necessary. To do this, we need to remember the deleted node's color and which
  // node we have moved up.

  // If the deleted node is red, we cannot have violated any rule: Neither can it
  // result in two consecutive red nodes (rule 4), nor does it change the number
  // of black nodes on any path (rule 5).
  // However, if the deleted node is black, we are guaranteed to have violated
  // rule 5 (unless the tree contained nothing but a black root), and rule 4 may
  // also have been violated – namely if both parent nodes and the moved-up child
  // of the deleted node were red.
  //
  public void delete(int key) {
    RedBlackNode node = root;

    // Find the node to be deleted
    while (node != null && node.getValue() != key) {
      // Traverse the tree to the left or right depending on the key
      if (key < node.getValue()) {
        node = node.getLeft();
      } else {
        node = node.getRight();
      }
    }

    // Node not found?
    if (node == null) {
      return;
    }

    // At this point, "node" is the node to be deleted

    // In this variable, we'll store the node at which we're going to start to fix
    // the R-B
    // properties after deleting a node.
    RedBlackNode movedUpNode;
    boolean deletedNodeColor;

    // Node has zero or one child
    if (node.getLeft() == null || node.getRight() == null) {
      movedUpNode = this.deleteNodeWithZeroOrOneChild(node);
      deletedNodeColor = node.getColor();
    }

    // Node has two children
    else {
      // Find minimum node of right subtree ("inorder successor" of current node)
      RedBlackNode inOrderSuccessor = this.findMinimum(node.getRight());

      // Copy inorder successor's data to current node (keep its color!)
      node.setValue(inOrderSuccessor.getValue());

      // Delete inorder successor just as we would delete a node with 0 or 1 child
      movedUpNode = this.deleteNodeWithZeroOrOneChild(inOrderSuccessor);
      deletedNodeColor = inOrderSuccessor.getColor();
    }
    // Deleting a red node does not vaiolate any rule
    // This checks if the deleted node is black. in this case
    // we have to consider:
    // Case 1: Deleted node is the root
    // Case 2: Sibling is red
    // Case 3: Sibling is black and has two black children, parent is red
    // Case 4: Sibling is black and has two black children, parent is black
    // Case 5: Sibling is black and has at least one red child, "outer nephew" is
    // black
    // Case 6: Sibling is black and has at least one red child, "outer nephew" is
    // red
    if (deletedNodeColor == BLACK) {
      this.fixRedBlackPropertiesAfterDelete(movedUpNode);

      // Remove the temporary NIL node
      if (movedUpNode.getClass() == NilNode.class) {
        replaceParentsChild(movedUpNode.getParent(), movedUpNode, null);
      }
    }
  }

  private RedBlackNode getUncle(RedBlackNode parent) {
    var grandparent = parent.getParent();
    if (grandparent == null) {
      // No grandparent, thus no uncle
      return null;
    }
    if (grandparent.getLeft() == parent) {
      return grandparent.getRight();
    } else if (grandparent.getRight() == parent) {
      return grandparent.getLeft();
    } else {
      throw new IllegalStateException("Parent is not a child of its grandparent");
    }
  }

  private void rotateLeft(RedBlackNode node) {
    RedBlackNode parent = node.getParent();
    RedBlackNode rightChild = node.getRight();
    node.setRight(rightChild.getLeft());
    if (rightChild.getLeft() != null) {
      rightChild.getLeft().setParent(node);
    }
    rightChild.setLeft(node);
    node.setParent(rightChild);
    this.replaceParentsChild(parent, node, rightChild);
  }

  private void rotateRight(RedBlackNode node) {
    RedBlackNode parent = node.getParent();
    RedBlackNode leftChild = node.getLeft();
    node.setLeft(leftChild.getRight());
    if (leftChild.getRight() != null) {
      leftChild.getRight().setParent(node);
    }

    leftChild.setRight(node);
    node.setParent(leftChild);
    this.replaceParentsChild(parent, node, leftChild);
  }

  private void replaceParentsChild(RedBlackNode parent, RedBlackNode oldChild, RedBlackNode newChild) {
    if (parent == null) {
      this.root = newChild;
    } else if (parent.getLeft() == oldChild) {
      parent.setLeft(newChild);
    } else if (parent.getRight() == oldChild) {
      parent.setRight(newChild);
    } else {
      throw new IllegalStateException("Node is not a child of its parent");
    }
    if (newChild != null) {
      newChild.setParent(parent);
    }
  }

  private RedBlackNode deleteNodeWithZeroOrOneChild(RedBlackNode node) {
    // Node has ONLY a left child --> replace by its left child
    if (node.getLeft() != null) {
      replaceParentsChild(node.getParent(), node, node.getParent());
      return node.getLeft(); // moved-up node
    }

    // Node has ONLY a right child --> replace by its right child
    else if (node.getRight() != null) {
      replaceParentsChild(node.getParent(), node, node.getRight());
      return node.getRight(); // moved-up node
    }

    // Node has no children -->
    // * node is red --> just remove it
    // * node is black --> replace it by a temporary NIL node (needed to fix the R-B
    // rules)
    else {
      RedBlackNode newChild = node.getColor() == BLACK ? new NilNode() : null;
      replaceParentsChild(node.getParent(), node, newChild);
      return newChild;
    }
  }

  private void fixRedBlackPropertiesAfterDelete(RedBlackNode node) {
    // Case 1: Examined node is root, end of recursion
    if (node == root) {
      // Uncomment the following line if you want to enforce black roots (rule 2):
      // node.color = BLACK;
      return;
    }

    RedBlackNode sibling = getSibling(node);

    // Case 2: Red sibling
    if (sibling.getColor() == RED) {
      handleRedSibling(node, sibling);
      sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
    }

    // Cases 3+4: Black sibling with two black children
    if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
      sibling.setColor(RED);

      // Case 3: Black sibling with two black children + red parent
      if (node.getParent().getColor() == RED) {
        node.getParent().setColor(BLACK);
      }

      // Case 4: Black sibling with two black children + black parent
      else {
        fixRedBlackPropertiesAfterDelete(node.getParent());
      }
    }

    // Case 5+6: Black sibling with at least one red child
    else {
      handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
    }
  }

  private void handleBlackSiblingWithAtLeastOneRedChild(RedBlackNode node, RedBlackNode sibling) {
    boolean nodeIsLeftChild = node == node.getParent().getLeft();

    // Case 5: Black sibling with at least one red child + "outer nephew" is black
    // --> Recolor sibling and its child, and rotate around sibling
    if (nodeIsLeftChild && isBlack(sibling.getRight())) {
      sibling.getLeft().setColor(BLACK);
      sibling.setColor(RED);
      rotateRight(sibling);
      sibling = node.getParent().getRight();
    } else if (!nodeIsLeftChild && isBlack(sibling.getLeft())) {
      sibling.getRight().setColor(BLACK);
      sibling.setColor(RED);
      rotateLeft(sibling);
      sibling = node.getParent().getLeft();
    }

    // Fall-through to case 6...

    // Case 6: Black sibling with at least one red child + "outer nephew" is red
    // --> Recolor sibling + parent + sibling's child, and rotate around parent
    sibling.setColor(node.getParent().getColor());
    node.getParent().setColor(BLACK);
    if (nodeIsLeftChild) {
      sibling.getRight().setColor(BLACK);
      rotateLeft(node.getParent());
    } else {
      sibling.getLeft().setColor(BLACK);
      rotateRight(node.getParent());
    }
  }

  private RedBlackNode findMinimum(RedBlackNode node) {
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    return node;
  }

  private RedBlackNode getSibling(RedBlackNode node) {
    RedBlackNode parent = node.getParent();
    if (node == parent.getLeft()) {
      return parent.getRight();
    } else if (node == parent.getRight()) {
      return parent.getLeft();
    } else {
      throw new IllegalStateException("Parent is not a child of its grandparent");
    }
  }

  private void handleRedSibling(RedBlackNode node, RedBlackNode sibling) {
    // Recolor...
    sibling.setColor(BLACK);
    node.getParent().setColor(RED);

    // ... and rotate
    if (node == node.getParent()) {
      rotateLeft(node.getParent());
    } else {
      rotateRight(node.getParent());
    }
  }

  private boolean isBlack(RedBlackNode node) {
    return node == null || node.getColor() == BLACK;
  }

  public RedBlackNode getRoot() {
    return root;
  }

  public void setRoot(RedBlackNode root) {
    this.root = root;
  }

}

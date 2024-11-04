package RedBlackTree;

import BinaryTree.BinaryNode;
import org.w3c.dom.Node;

public class RedBlackTree {
    private final boolean RED = true;
    private final boolean BLACK = false;
    private RedBlackNode root;
    private RedBlackNode criticalNode;
    private String rotationType;

    public void insert(int val) {
        RedBlackNode node = this.root;
        RedBlackNode parent = null;

        // Traverse the tree to the left or right depending on the key
        while (node != null) {
            parent = node;
            if (val < node.getValue()) {
                node = node.getLeft();
            } else if (val > node.getValue()) {
                node = node.getRight();
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
        System.out.println("-------------------------------------------------");
        System.out.println("INSERINDO O NÓ: " + newNode.getValue());
        this.fixRedBlackPropertiesAfterInsert(newNode);
        System.out.println("ARVORE: (PRÉ ORDEM)");
        System.out.println("--------------------");
        this.transversePreOrder();
        System.out.println("--------------------");
        System.out.println("ALTURA NEGRA: " + this.getBlackHeight());
        System.out.println("-------------------------------------------------");
        System.out.println();
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

    // Transverse methods
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
            this.printNode();
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        this.printNode(node);
    }

    private void inOrder(RedBlackNode node) {
        if (node == null) {
            this.printNode();
            return;
        }
        inOrder(node.getLeft());
        this.printNode(node);
        inOrder(node.getRight());
    }

    private void preOrder(RedBlackNode node) {
        if (node == null) {
            this.printNode();
            return;
        }
        this.printNode(node);
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void printNode(RedBlackNode node) {
        System.out.println("VALOR: " + node.getValue());
        String color = isBlack(node) ? "PRETO" : "VERMELHO";
        System.out.println("COR: " + color);
        System.out.println("ALTURA NEGRA DO NÓ: " + this.getBlackDepth(node));
        System.out.println();
    }

    public void printNode() {
        System.out.println("VALOR: NULO");
        System.out.println("COR: PRETO");
        System.out.println("ALTURA NEGRA DO NÓ: " + this.getBlackHeight());
        System.out.println();
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
        RedBlackNode parent = node.getParent();

        // Case 1: Root node
        if (parent == null) {
            node.setColor(BLACK);
            System.out.println("ALTERAÇÃO: A raiz " + node.getValue() + " foi pintada de preto.");
            return;
        }

        // Case 2: Parent is black --> nothing to do
        if (parent.getColor() == BLACK) {
            return;
        }

        // From here on, parent is red
        RedBlackNode grandparent = parent.getParent();

        // Get the uncle (based on parent, not grandparent)
        RedBlackNode uncle = this.getUncle(parent);

        // Case 3: Uncle is red -> recolor parent, grandparent and uncle
        if (uncle != null && uncle.getColor() == RED) {
            parent.setColor(BLACK);
            grandparent.setColor(RED);
            uncle.setColor(BLACK);

            System.out.println("ALTERAÇÃO: O nó pai " + parent.getValue() + " e o tio " + uncle.getValue() + " foram pintados de preto. Avô " + grandparent.getValue() + " foi pintado de vermelho.");

            // Call recursively for grandparent, which is now red
            fixRedBlackPropertiesAfterInsert(grandparent);
        }
        // Parent is left child of grandparent
        else if (parent == grandparent.getLeft()) {
            // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
            if (node == parent.getRight()) {
                // Rotate left on parent
                rotateLeft(parent);
                System.out.println("ALTERAÇÃO: Rotacionou à esquerda no nó pai " + parent.getValue() + ".");

                // Update parent pointer to new subtree root after rotation
                parent = node;
            }

            // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
            // Rotate right on grandparent
            rotateRight(grandparent);
            System.out.println("ALTERAÇÃO: Rotacionou à direita no nó avô " + grandparent.getValue() + ".");

            // Recolor parent and grandparent after rotation
            parent.setColor(BLACK);
            grandparent.setColor(RED);
            System.out.println("ALTERAÇÃO: Nó pai " + parent.getValue() + " pintado de preto e nó avô " + grandparent.getValue() + " pintado de vermelho.");
        } else {
            // Parent is right child of grandparent
            // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
            if (node == parent.getLeft()) {
                // Rotate right on parent
                rotateRight(parent);
                System.out.println("ALTERAÇÃO: Rotacionou à direita no nó pai " + parent.getValue() + ".");

                // Update parent pointer to new subtree root after rotation
                parent = node;
            }

            // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
            // Rotate left on grandparent
            rotateLeft(grandparent);
            System.out.println("ALTERAÇÃO: Rotacionou à esquerda no nó avô " + grandparent.getValue() + ".");

            // Recolor parent and grandparent after rotation
            parent.setColor(BLACK);
            grandparent.setColor(RED);
            System.out.println("ALTERAÇÃO: Nó pai " + parent.getValue() + " pintado de preto e nó avô " + grandparent.getValue() + " pintado de vermelho.");
        }
        if (this.rotationType != null) {
            System.out.println("ROTACÃO: " + this.rotationType + " no nó crítico " + criticalNode.getValue());
            this.rotationType = null;
            this.criticalNode = null;
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
        this.rotationType = "Esquerda";
        this.criticalNode = rightChild;
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
        this.rotationType = "Direita";
        this.criticalNode = leftChild;
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

    public int getBlackHeight() {
        return this.getBlackHeight(this.root);
    }

    private int getBlackHeight(RedBlackNode node) {
        if (node == null) return 1;

        int leftH = this.getBlackHeight(node.getLeft());
        if (leftH == 0) return leftH;
        int rightH = this.getBlackHeight(node.getRight());
        if (rightH == 0) return rightH;
        if (leftH != rightH) {
            return 0;
        }
        return leftH + (isBlack(node) ? 1 : 0);
    }

    public int getBlackDepth(RedBlackNode node) {
        RedBlackNode current = node;
        int count = 0;
        while (current != null) {
            if (isBlack(current)) count++;
            current = current.getParent();
        }
        return count;
    }

    private boolean isBlack(RedBlackNode node) {
        return node == null || node.getColor() == BLACK;
    }

    public void printNodesWithLevels() {
        this.printNodesWithLevels(this.root, 1);
    }

    private void printNodesWithLevels(RedBlackNode node, int level) {
        if (node == null) {
            System.out.println("NODE: NULO\nNIVEL: " + level);
            System.out.println();
            return;
        }
        System.out.println("NODE: " + node.getValue() + "\nNIVEL: " + level);
        System.out.println();
        printNodesWithLevels(node.getRight(), level + 1);
        printNodesWithLevels(node.getLeft(), level + 1);
    }

    public int getHeight() {
        return getHeight(this.root);
    }

    private int getHeight(RedBlackNode node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }


    public void avaregeValuesByLevel() {
        var nodes = this.getAllNodes();
        int size = this.getSize();
        int[] sum = new int[size];
        int[] count = new int[size];
        for (RedBlackNode node : nodes) {
            int level = getLevel(node);
            sum[level] += node.getValue();
            count[level]++;
        }

        // Calculate and print averages
        for (int i = 0; i < sum.length; i++) {
            if (count[i] > 0) {
                System.out.println("Media dos valores no nível: " + i + ": " + (sum[i] * 1.0 / (double) count[i]));
                System.out.println();
            }
        }
    }

    public RedBlackNode[] getAllNodes() {
        int size = this.getSize();
        RedBlackNode[] nodes = new RedBlackNode[size];
        getAllNodes(this.root, nodes, 0);
        return nodes;
    }

    private int getAllNodes(RedBlackNode node, RedBlackNode[] nodes, int index) {
        if (node == null) return index;
        index = getAllNodes(node.getLeft(), nodes, index);
        nodes[index++] = node;
        index = getAllNodes(node.getRight(), nodes, index);
        return index;
    }

    public int getSize() {
        return this.getSize(this.root);
    }

    private int getSize(RedBlackNode node) {
        if (node == null) return 0;
        int l = this.getSize(node.getLeft());
        int r = this.getSize(node.getRight());
        return l + r + 1;
    }

    public int getLevel(RedBlackNode node) {
        return getLevel(node, this.root, 1);
    }

    private int getLevel(RedBlackNode node, RedBlackNode current, int level) {
        if (current == null) return -1; // Não encontrado
        if (current == node) return level;
        int leftLevel = getLevel(node, current.getLeft(), level + 1);
        if (leftLevel != -1) return leftLevel;
        return getLevel(node, current.getRight(), level + 1);
    }

    public RedBlackNode getRoot() {
        return root;
    }

    public void setRoot(RedBlackNode root) {
        this.root = root;
    }
}

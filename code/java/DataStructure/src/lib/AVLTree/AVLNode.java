package AVLTree;

public class AVLNode {
    private int val;
    private AVLNode right;
    private AVLNode left;
    private int height;

    public AVLNode(int val) {
        this.val = val;
        this.right = null;
        this.left = null;
        this.height = 1;
    }


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

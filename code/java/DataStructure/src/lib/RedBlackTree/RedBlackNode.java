package lib.RedBlackTree;

public class RedBlackNode {
  private int value;

  private RedBlackNode left;
  private RedBlackNode right;
  private RedBlackNode parent;
  // false -> black; true -> red;
  private boolean color;

  public RedBlackNode(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.parent = null;
    this.color = false;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public RedBlackNode getLeft() {
    return left;
  }

  public void setLeft(RedBlackNode left) {
    this.left = left;
  }

  public RedBlackNode getRight() {
    return right;
  }

  public void setRight(RedBlackNode right) {
    this.right = right;
  }

  public RedBlackNode getParent() {
    return parent;
  }

  public void setParent(RedBlackNode parent) {
    this.parent = parent;
  }

  public boolean getColor() {
    return color;
  }

  public void setColor(boolean color) {
    this.color = color;
  }
}

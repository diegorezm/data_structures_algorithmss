package encode.lib;

public class Node implements Comparable<Node> {
    private Node left;
    private Node right;
    private int frequency;

    public Node() {
        this.frequency = 0;
        this.left = null;
        this.right = null;
    }

    public Node(int frequency) {
        this.frequency = frequency;
    }

    public Node(Node left, Node right) {
        this.frequency = left.getFrequency() + right.getFrequency();
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Compare nodes based on frequency
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }

}

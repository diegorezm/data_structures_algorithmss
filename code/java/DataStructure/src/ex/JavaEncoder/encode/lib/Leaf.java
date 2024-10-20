package encode.lib;

public class Leaf extends Node {
    private char c;

    public Leaf() {
        super();
        this.c = '0';
    }

    public Leaf(char c, int frequency) {
        super(frequency);
        this.c = c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Bnode {
    private int value;
    private Bnode l, r;

    public Bnode(int value) {
        this.value = value;
        this.l = null;
        this.r = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Bnode getL() {
        return l;
    }

    public void setL(Bnode l) {
        this.l = l;
    }

    public Bnode getR() {
        return r;
    }

    public void setR(Bnode r) {
        this.r = r;
    }

    public void display() {
        if (this.l != null) {
            this.l.display();
        }
        System.out.println(this.value);
        if (this.r != null) {
            this.r.display();
        }
    }

    public void displayF() {
        if (this.l == null && this.r == null) {
            System.out.println(this.value);
        } else {
            if (this.l != null) {
                this.l.displayF();
            }
            if (this.r != null) {
                this.r.displayF();
            }
        }
    }

    public void add(int value) {
        if (value <= this.value) {
            if (this.l == null) {
                this.l = new Bnode(value);
            } else {
                this.l.add(value);
            }
        } else {
            if (this.r == null) {
                this.r = new Bnode(value);
            } else {
                this.r.add(value);
            }
        }
    }

    public int sum() {
        int rSum = (this.r != null) ? this.r.sum() : 0;
        int lSum = (this.l != null) ? this.l.sum() : 0;
        return this.value + rSum + lSum;
    }

    public int size() {
        int sizeR = (this.r != null) ? this.r.size() : 0;
        int sizeL = (this.l != null) ? this.l.size() : 0;
        return 1 + sizeR + sizeL;
    }

    public int biggest() {
        int biggestR = -9999;
        int biggestL = -9999;
        if (this.r != null) {
            biggestR = this.r.biggest();
        }
        if (this.l != null) {
            biggestL = this.l.biggest();
        }
        int max = Math.max(biggestR, biggestL);
        return max > this.value ? max : this.value;
    }

    public int smallest() {
        int biggestR = 10000;
        int biggestL = 10000;
        if (this.r != null) {
            biggestR = this.r.smallest();
        }
        if (this.l != null) {
            biggestL = this.l.smallest();
        }
        int max = Math.min(biggestR, biggestL);
        return max < this.value ? max : this.value;
    }

    public int height() {
        return this.heightHelper(this);
    }

    public int heightHelper(Bnode node) {
        if (node == null) {
            return 0;
        } else {
            int lH = this.heightHelper(node.l);
            int rH = this.heightHelper(node.r);
            return Math.max(lH, rH) + 1;
        }
    }

    public List<Integer> getLevel(int N) {
        ArrayList<Integer> response = new ArrayList<>();
        this.getLevelHelper(this, response, N);
        return response;
    }

    public void getLevelHelper(Bnode node, List<Integer> list, int level) {
        if (node == null) {
            return;
        }
        if (level == 0) {
            list.add(node.value);
        } else {
            this.getLevelHelper(node.l, list, level - 1);
            this.getLevelHelper(node.r, list, level - 1);
        }
    }

    public void getN(int n) {
        this.getNHelper(this, 0, n);
    }

    public void getNHelper(Bnode node, int level, int n) {
        if (level == this.height() || node == null) {
            return;
        }

        if (node.value == n) {
            System.out.println("O valor " + n + " esta no nivel " + level);
            return;
        } else {
            this.getNHelper(node.l, level + 1, n);
            this.getNHelper(node.r, level + 1, n);
        }

    }

    // ----------------------------------------------
    // folha
    // ----------------------------------------------

    public int sumF() {
        return this.sumFHelper();
    }

    public int sizeF() {
        return this.sizeFHelper();
    }

    private int sumFHelper() {
        if (this.l == null && this.r == null) {
            return this.value;
        }
        int rSum = (this.r != null) ? this.r.sumFHelper() : 0;
        int lSum = (this.l != null) ? this.l.sumFHelper() : 0;
        return rSum + lSum;
    }

    private int sizeFHelper() {
        if (this.l == null && this.r == null) {
            return 1;
        }
        int rSum = (this.r != null) ? this.r.sizeFHelper() : 0;
        int lSum = (this.l != null) ? this.l.sizeFHelper() : 0;
        return rSum + lSum;
    }

    // ----------------------------------------------
    // interno
    // ----------------------------------------------

    public int sizeI() {
        return this.sizeIHelper();
    }

    public int sumI() {
        return this.sumIHelper();
    }

    private int sumIHelper() {
        if (this.r == null && this.l == null) {
            return 0;
        }
        int rSum = (this.r != null) ? this.r.sumIHelper() : 0;
        int lSum = (this.l != null) ? this.l.sumIHelper() : 0;
        return this.value + rSum + lSum;
    }

    private int sizeIHelper() {
        if (this.r == null && this.l == null) {
            return 0;
        }
        int rSum = (this.r != null) ? this.r.sizeIHelper() : 0;
        int lSum = (this.l != null) ? this.l.sizeIHelper() : 0;
        return 1 + rSum + lSum;
    }
}

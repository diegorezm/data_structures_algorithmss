import java.util.ArrayList;
import java.util.List;

public class Btree {
    private Bnode root;

    public Bnode getRoot() {
        return root;
    }

    public void add(int value) {
        if (this.root == null) {
            this.root = new Bnode(value);
        } else {
            this.root.add(value);
        }
    }

    public void display() {
        if (root != null) {
            root.display();
        } else {
            System.out.println("Não há elementos na lista.");
        }
    }

    public int sum() {
        if (root != null) {
            return this.root.sum();
        }
        return -9999;
    }

    public int size() {
        if (root != null) {
            return this.root.size();
        }
        return -1;
    }

    public int biggest() {
        if (root != null) {
            return this.root.biggest();
        }
        return -9999;
    }

    public int smallest() {
        if (root != null) {
            return this.root.smallest();
        }
        return -9999;
    }

    public int height() {
        if (root != null) {
            return this.root.height();
        }
        return -9999;
    }

    public List<Integer> getLevel(int N) {
        if (N > this.height()) {
            System.out.println("Esta árvore não possui o nível " + N);
            return new ArrayList<>();
        }
        if (root != null) {
            return this.root.getLevel(N);
        }

        return new ArrayList<>();
    }

    public void getN(int n) {
        if (root != null) {
            this.root.getN(n);
        } else {
            System.out.println("Arvore esta vazia.");
        }
    }

    // folha
    public void displayF() {
        if (root != null) {
            root.displayF();
        } else {
            System.out.println("Não há elementos na lista.");
        }
    }

    public int sizeF() {
        if (root != null) {
            return root.sizeF();
        }
        return -9999;
    }

    public int sumF() {
        if (root != null) {
            return root.sumF();
        }
        return -9999;
    }

    // interno
    public int sizeI() {
        if (root != null) {
            return root.sizeI();
        }
        return -9999;
    }

    public int sumI() {
        if (root != null) {
            return root.sumI();
        }
        return -9999;
    }

}

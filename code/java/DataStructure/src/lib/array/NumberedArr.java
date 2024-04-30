package lib.array;

public class NumberedArr<N extends Number> extends Arr<N> {
    public N sum() {
        double sum = 0;
        for (N t : this.arr) {
            sum += t.doubleValue();
        }
        return (N) (Number) sum;
    }

    public N largest() {
        var largest = this.arr[0].doubleValue();
        for (N n : this.arr) {
            if (largest < n.doubleValue()) {
                largest = n.doubleValue();
            }
        }
        return (N) (Number) largest;
    }

    public N smallest() {
        var smallest = this.arr[0].doubleValue();
        for (N n : this.arr) {
            if (smallest > n.doubleValue()) {
                smallest = n.doubleValue();
            }
        }
        return (N) (Number) smallest;
    }
}

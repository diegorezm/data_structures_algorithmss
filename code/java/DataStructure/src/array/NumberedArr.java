package array;

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
        for (int i = 0; i < this.arr.length; i++) {
            if (largest < this.arr[i].doubleValue()) {
                largest = this.arr[i].doubleValue();
            }
        }
        return (N) (Number) largest;
    }

    public N smallest() {
        var smallest = this.arr[0].doubleValue();
        for (int i = 0; i < this.arr.length; i++) {
            if (smallest > this.arr[i].doubleValue()) {
                smallest = this.arr[i].doubleValue();
            }
        }
        return (N) (Number) smallest;
    }
}

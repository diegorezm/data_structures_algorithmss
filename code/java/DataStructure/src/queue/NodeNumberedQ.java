package queue;

public class NodeNumberedQ<T extends Number> extends NodeQueue<T> {
    public T sum() {
        if (this.first != null) {
            double total = this.first.getValue().doubleValue();
            var curr = this.first.getNext();
            while (curr != null) {
                total += curr.getValue().doubleValue();
                curr = curr.getNext();
            }
            return (T) (Number) total;
        }
        return null;
    }

    public T largest() {
        if (this.first != null) {
            double largest = this.first.getValue().doubleValue();
            var curr = this.first.getNext();
            while (curr != null) {
                if (largest < curr.getValue().doubleValue()) {
                    largest = curr.getValue().doubleValue();
                }
                curr = curr.getNext();
            }
            return (T) (Number) largest;
        }
        return null;
    }

    public T smallest() {
        if (this.first != null) {
            double smallest = this.first.getValue().doubleValue();
            var curr = this.first.getNext();
            while (curr != null) {
                if (smallest > curr.getValue().doubleValue()) {
                    smallest = curr.getValue().doubleValue();
                }
                curr = curr.getNext();
            }
            return (T) (Number) smallest;
        }
        return null;
    }
}

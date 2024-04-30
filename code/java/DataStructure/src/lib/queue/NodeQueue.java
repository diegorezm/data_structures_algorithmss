package lib.queue;

import lib.base.Node;
import lib.interfaces.CustomNodeList;

public class NodeQueue<T> implements CustomNodeList<T> {
    protected Node<T> first, last;
    protected int size;

    public NodeQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void push(T value) {
        if (this.first == null) {
            this.first = new Node<>(value);
            this.last = first;
        } else {
            this.last.setNext(new Node<>(value));
            this.last = this.last.getNext();
        }
        this.size++;
    }

    @Override
    public int indexOf(T value) {
        if (this.first == null) return -1;
        var curr = this.first;
        int idx = 0;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                return idx;
            }
            curr = curr.getNext();
            idx++;
        }
        return -1;
    }

    @Override
    public Node<T> getNode(Integer index) {
        if (index > this.size || index < 0) throw new IndexOutOfBoundsException();
        var curr = this.first;
        int idx = 0;
        while (curr.getNext() != null) {
            if (index.equals(idx)) {
                return curr;
            }
            curr = curr.getNext();
            idx++;
        }
        return null;
    }

    @Override
    public T get(Integer index) {
        if (index > this.size || index < 0) throw new IndexOutOfBoundsException();
        var curr = this.first;
        int idx = 0;
        while (curr.getNext() != null) {
            if (index.equals(idx)) {
                return curr.getValue();
            }
            curr = curr.getNext();
            idx++;
        }
        return null;
    }

    @Override
    public T pop() {
        if (this.isEmpty() || this.first == null) {
            return null;
        }
        var aux = this.first;
        this.first = this.first.getNext();
        this.size--;
        return aux.getValue();
    }


    @Override
    public T peak() {
        return this.first.getValue();
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void show() {
        if (this.first == null) return;
        var curr = this.first;
        while (curr != null) {
            System.out.print(curr.getValue() + " -> ");
            curr = curr.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        var curr = this.first;
        while (curr != null) {
            stringBuilder.append(curr.getValue());
            curr = curr.getNext();
        }
        return stringBuilder.toString();
    }
}

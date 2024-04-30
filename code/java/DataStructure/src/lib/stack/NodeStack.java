package lib.stack;

import lib.base.Node;
import lib.interfaces.CustomNodeList;

public class NodeStack<T> implements CustomNodeList<T> {
    private int size;
    private Node<T> top;

    public NodeStack() {
        this.size = 0;
        this.top = null;
    }

    @Override
    public void push(T value) {
        if (this.top == null) {
            this.top = new Node<>(value);
        } else {
            this.top = new Node<>(value, this.top);
        }
        this.size++;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        var curr = this.top;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public Node<T> getNode(Integer index) {
        if (index > this.size || index < 0) throw new IndexOutOfBoundsException();
        int idx = 0;
        var curr = this.top;
        while (curr != null) {
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
        int idx = 0;
        var curr = this.top;
        while (curr != null) {
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
        if (this.isEmpty() || this.top == null) {
            return null;
        }
        var aux = this.top;
        this.top = this.top.getNext();
        this.size--;
        return aux.getValue();
    }

    @Override
    public T peak() {
        return this.top.getValue();
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void show() {
        if (this.top == null) return;
        var curr = this.top;
        while (curr != null) {
            System.out.print(curr.getValue() + " -> ");
            curr = curr.getNext();
        }
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        var curr = this.top;
        while (curr != null) {
            stringBuilder.append(curr.getValue());
            curr = curr.getNext();
        }
        return stringBuilder.toString();
    }
}

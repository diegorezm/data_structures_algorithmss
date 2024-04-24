package stack;

import base.Node;
import interfaces.CustomList;

public class NodeStack<T> implements CustomList<T> {
    private int size;
    private Node<T> bottom;
    private Node<T> top;

    public NodeStack() {
        this.size = 0;
        this.top = null;
        this.bottom = null;
    }

    @Override
    public void append(T value) {
        if (this.bottom == null) {
            this.bottom = new Node<>(value);
            this.top = bottom;
        } else {
            this.top.setNext(new Node<>(value));
            this.top = this.top.getNext();
        }
        this.size++;
    }

    @Override
    public void prepend(T value) {
        if (this.bottom == null) {
            this.bottom = new Node<>(value);
            this.top = bottom;
        } else {
            var aux = new Node<>(value);
            aux.setNext(this.bottom);
            this.bottom = aux;
        }
        this.size++;
    }

    @Override
    public void insert(int index, T value) {
        if (index == this.size) {
            this.append(value);
            return;
        }
        if (index == 0) {
            this.prepend(value);
            return;
        }
        if (this.bottom == null) {
            this.bottom = new Node<>(value);
            this.top = bottom;
        } else {
            var parent = this.getNode(index - 1);
            var child = parent.getNext();
            var node = new Node<>(value, child);
            parent.setNext(node);
            this.size++;
        }
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        var curr = this.bottom;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    private Node<T> getNode(Integer index) {
        if (index > this.size || index < 0) throw new IndexOutOfBoundsException();
        int idx = 0;
        var curr = this.bottom;
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
        var curr = this.bottom;
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
        if (this.size <= 0) {
            return null;
        }
        if (this.size == 1) {
            var aux = this.bottom.getValue();
            this.bottom = null;
            this.top = null;
            this.size--;
            return aux;
        }
        if (this.bottom != null) {
            var parent = this.getNode(this.size - 2);
            var aux = this.top.getValue();
            parent.setNext(null);
            this.top = parent;
            this.size--;
            return aux;
        }
        return null;
    }

    @Override
    public T peek() {
        return this.top.getValue();
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void show() {
        if (this.bottom == null) return;
        var curr = this.bottom;
        while (curr != null) {
            System.out.print(curr.getValue() + " -> ");
            curr = curr.getNext();
        }
    }
}

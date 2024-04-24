package queue;

import interfaces.CustomList;
import base.Node;

public class NodeQueue<T> implements CustomList<T> {
    protected Node<T> first, last;
    protected int size;

    public NodeQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void append(T value) {
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
    public void prepend(T value) {
        if (this.first == null) {
            this.first = new Node<>(value);
            this.last = first;
        } else {
            Node<T> aux = new Node<>(value);
            aux.setNext(this.first);
            this.first = aux;
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
        if (this.first == null) {
            this.first = new Node<>(value);
            this.last = first;
        } else {
            var node = new Node<>(value);
            var parent = this.getNode(index - 1);
            var child = parent.getNext();
            node.setNext(child);
            parent.setNext(node);
            this.size++;
        }
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

    private Node<T> getNode(Integer index) {
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
        if (this.size <= 0) {
            return null;
        }
        if (this.size == 1) {
            var aux = this.first.getValue();
            this.first = null;
            this.last = null;
            return aux;
        }
        if (this.first != null) {
            var aux = this.first.getValue();
            this.first = this.first.getNext();
            this.size--;
            return aux;
        }
        return null;
    }

    @Override
    public T peek() {
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
}

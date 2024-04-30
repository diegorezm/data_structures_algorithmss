package lib.array;

import lib.interfaces.CustomList;

public class Arr<T> implements CustomList<T> {
    private final int size = 256;
    protected T[] arr = (T[]) new Object[this.size];
    protected int curr = 0;

    private void shift() {
        for (int i = this.curr - 1; i >= 0; i--) {
            this.arr[i + 1] = this.arr[i];
        }
    }

    private void shift(int index) {
        if (index > this.curr) return;
        for (int i = this.curr - 1; i >= index; i--) {
            this.arr[i + 1] = this.arr[i];
        }
    }

    @Override
    public void push(T value) {
        if (curr == size) {
            return;
        }
        this.arr[curr] = value;
        curr++;
    }

    public void prepend(T value) {
        this.shift();
        this.arr[0] = value;
        this.curr++;
    }

    public void insert(int index, T value) {
        this.shift(index);
        this.arr[index] = value;
        this.curr++;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < this.curr; i++) {
            if (this.arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(Integer index) {
        if (index > this.curr) return null;
        return this.arr[index];
    }

    @Override
    public T pop() {
        var aux = this.arr[curr];
        this.arr[curr] = null;
        this.curr--;
        return aux;
    }

    @Override
    public T peak() {
        return this.arr[curr];
    }

    @Override
    public int length() {
        return this.curr;
    }

    @Override
    public void show() {
        for (T t : this.arr) {
            if (t == null) return;
            System.out.print(t + " -> ");
        }
        this.shift();
    }

    @Override
    public boolean isEmpty() {
        return this.curr == 0;
    }
}

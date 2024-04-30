package exercicios.ex2;

import lib.stack.NodeStack;

public class QueueWithStack {
    private NodeStack<Integer> numbers;
    private NodeStack<Integer> order;
    private int size;

    public QueueWithStack() {
        this.numbers = new NodeStack<>();
        this.order = new NodeStack<>();
        this.size = 0;
    }

    private void prepush() {
        while (!this.numbers.isEmpty()) {
            this.order.push(this.numbers.pop());
        }
    }

    private void pospush() {
        while (!this.order.isEmpty()) {
            this.numbers.push(this.order.pop());
        }
    }

    public void push(int value) {
        this.prepush();
        this.numbers.push(value);
        this.size++;
        this.pospush();
    }

    public int pop() {
        if(this.isEmpty()) return -99999;
        this.size--;
        return this.numbers.pop();
    }

    public int peak() {
        return this.numbers.peak();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    public void show(){
        this.numbers.show();
    }
}

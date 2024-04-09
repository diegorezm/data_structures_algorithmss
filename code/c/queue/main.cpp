#include <iostream>
class Queue {
public:
  Queue(int size) {
    this->size = size;
    this->queue = new int[size];
    this->first = 0;
    this->last = 0;
  }

  ~Queue() { delete[] this->queue; }

  int push(int n) {
    int temp = -1;
    if (this->last == this->size) {
      this->last = 0;
      temp = this->queue[0];
    }
    this->queue[this->last] = n;
    this->last++;
    return temp;
  }

  int pop() {
    if (this->isEmpty()) {
      return -1;
    }
    int temp = this->queue[this->first];
    this->queue[this->first] = 0;
    if (this->first > size) {
      this->first = 0;
    } else {
      this->first++;
    }
    return temp;
  }

  int peek() {
    return this->queue[this->first];
  }

  bool isEmpty() { return this->last == this->first; }

  bool isFull() { return this->last == this->size; }

  void display() {
    for (int i = 0; i < this->size; i++) {
      std::cout << this->queue[i];
      if (i != this->size - 1) {
        std::cout << "->";
      }
    }
    std::cout << std::endl;
  }

private:
  int size;
  int *queue;
  int first;
  int last;
};

int main(int argc, char *argv[]) {
  Queue q(5);
  q.push(10);
  q.push(20);
  q.push(30);
  q.push(40);
  q.push(50);
  q.display();
  int p = q.pop();
  q.push(10);
  q.display();
  std::cout << "\n" << q.peek();
  return 0;
}

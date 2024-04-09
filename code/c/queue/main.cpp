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
  void push(int n) {
    if (this->last == this->size) {
      this->last = 0;
    }
    this->queue[this->last] = n;
    this->last++;
  }
  int pop() {
    if (isEmpty()) {
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

  bool isEmpty() { return this->last == this->first; }

  bool isFull() { return this->last == this->size; }

  void display() {
    for (int i = 0; i < this->size; i++) {
      std::cout << this->queue[i] << " ";
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
  return 0;
}

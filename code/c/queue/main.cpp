#include <cstddef>
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
    if (this->isEmpty()) {
      return -1;
    }
    return this->queue[this->first];
  }

  int index(int n) {
    int idx = -1;
    for (int i = 0; i < this->size; i++) {
      if (this->queue[i] == n) {
        idx = i;
        break;
      }
    }
    return idx;
  }

  bool isEmpty() { return this->last == 0 && this->first == 0; }

  bool isFull() { return this->last == this->size; }

  void display() {
    std::cout << "First: " << this->first << std::endl;
    std::cout << "Last: " << this->last << std::endl;
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
  size_t n = 2;
  int n_people = 13;
  Queue q(n_people - 1);
  for (int i = 0; i < n_people; i += n) {
    if (i > 0) {
      q.push(i);
    }
  }
  for (int i = 0; i < n_people; i++) {
    if (i != 0 && q.index(i) < 0) {
      q.push(i);
    }
  }
  q.display();
  return 0;
}

#include <iostream>
template <typename T> class Queue {
private:
  int size;
  T *queue;
  int first;
  int last;

public:
  Queue(int size) {
    this->size = size;
    this->queue = new T[size];
    this->first = this->last = -1;
  }

  ~Queue() { delete[] this->queue; }

  void push(T n) {
    if (this->first == 0 && this->last == this->size - 1) {
      return;
    } else if (this->first == -1) {
      this->first = this->last = 0;
      this->queue[this->last] = n;
    } else if (this->first != 0 && this->last == this->size - 1) {
      this->last = 0;
      this->queue[this->last] = n;
    } else {
      this->last++;
      this->queue[this->last] = n;
    }
  }

  T pop() {
    if (this->isEmpty()) {
      return -1;
    }
    T temp = this->queue[this->first];
    this->queue[this->first] = 0;
    if (this->first == this->last) {
      this->first = this->last = -1;
    } else if (this->first == this->size - 1) {
      this->first = 0;
    } else {
      this->first++;
    }
    return temp;
  }

  T peek() {
    if (this->isEmpty()) {
      return -1;
    }
    return this->queue[this->first];
  }

  int index(T n) {
    int idx = -1;
    for (int i = 0; i < this->size; i++) {
      if (this->queue[i] == n) {
        idx = i;
        break;
      }
    }
    return idx;
  }

  bool isEmpty() { return this->first < 0; }

  bool isFull() { return this->last == this->size; }

  int getFirstIndex() { return this->first; }
  int getLastIndex() { return this->last; }

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
};

void josephus(int n, int n_people) {
  Queue<int> q(n_people - 1);
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
}

int main(int argc, char *argv[]) {

  Queue<int> q(3);
  q.push(1);
  q.push(2);
  q.pop();
  q.push(3);
  q.display();
  return 0;
}

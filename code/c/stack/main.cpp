#include <cstddef>
#include <iostream>
#include <string>

template <typename T>

class Stack {
private:
  int size;
  T *stack;
  int top;

public:
  Stack(int size) {
    this->size = size;
    this->stack = new T[size];
    this->top = -1;
  };

  T push(T n) {
    if (this->isFull()) {
      return -1;
    }
    this->top++;
    this->stack[this->top] = n;
    return 0;
  }

  T pop() {
    if (this->isEmpty()) {
      return -1;
    }
    int temp = this->stack[this->top];
    this->stack[this->top] = 0;
    this->top--;
    return temp;
  }

  T peek() {
    if (this->isEmpty()) {
      return -1;
    }
    return this->stack[this->top];
  }

  bool isEmpty() { return this->top < 0; }

  bool isFull() { return this->top == this->size - 1; }

  void display() {
    std::cout << "Top: " << this->top << std::endl;
    for (int i = 0; i < this->size; i++) {
      std::cout << this->stack[i];
      if (i != this->size - 1) {
        std::cout << "->";
      }
    }
    std::cout << std::endl;
  }

  ~Stack() { delete[] this->stack; };
};

int main(int argc, char *argv[]) {
  std::string str = "((4 + 5) - 20";
  size_t str_len = str.length();
  const char *c_char = str.c_str();
  Stack<char> stack(str_len);
  for (int i = 0; i < str_len; i++) {
    if (c_char[i] == '(') {
      stack.push('(');
    } else if (c_char[i] == ')') {
      stack.pop();
    }
  }
  std::cout << (stack.isEmpty() ? "Valid" : "Invalid");
  return 0;
}

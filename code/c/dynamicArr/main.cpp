#include <iostream>

class Array {
public:
  Array() {
    this->array = new int[1];
    this->size = 0;
  };
  ~Array() { delete[] this->array; };
  void push(int n) {
    this->size++;
    int *new_arr = new int[this->size];
    for (int i = 0; i < this->size - 1; i++) {
      new_arr[i] = this->array[i];
    }
    new_arr[this->size - 1] = n;
    delete[] this->array;
    this->array = new_arr;
  }
  void display() {
    std::cout << "[";
    for (int i = 0; i < this->size; i++) {
      std::cout << this->array[i];
      if(i != this->size - 1){
        std::cout << ",";
      }
    }
    std::cout << "]" << std::endl;
  }

private:
  int *array;
  int size;
};
int main(int argc, char *argv[]) {
  Array arr;
  arr.push(10);
  arr.push(20);
  arr.push(30);
  arr.push(40);
  arr.push(50);
  arr.display();
  return 0;
}

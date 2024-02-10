#include <iostream>
#include <ostream>

struct node {
  int value;
  node *next;
};

using namespace std;

class LinkedList {
public:
  LinkedList() {
    this->root = nullptr;
    this->tail = nullptr;
    this->size = 0;
  };

  void append(int value) {
    node *newNode = new node();
    newNode->value = value;
    newNode->next = nullptr;
    if (!this->root) {
      this->root = newNode;
      this->tail = newNode;
    } else {
      this->tail->next = newNode;
      this->tail = newNode;
    }
    this->size += 1;
  };

  void prepend(int value) {
    if (!this->root) {
      append(value);
    } else {
      node *newNode = new node();
      newNode->value = value;
      newNode->next = this->root;
      this->root = newNode;
      this->size += 1;
    }
  }

  void insert(int idx, int value){
    if(idx == 0){
      this->prepend(value);
      return;
    }
    if(idx >= this->size){
      this->append(value);
      return;
    }
    node *parent = this->to_index(idx - 1);
    node *temp = parent->next;
    node *newNode = new node();
    newNode->value = value;
    newNode->next = temp;
    parent->next = newNode;
    this->size += 1;
  }

  void remove(int idx) {
    if (idx < 0 || idx >= this->size) {
      return;
    }
    if (idx == 0) {
      if (!this->root) {
        return;
      }
      node *temp = this->root;
      this->root = this->root->next;
      delete temp;
      if (this->root == nullptr) {
        this->tail = nullptr;
      }
    } else {
      node *parent = this->to_index(idx - 1);
      if (parent == nullptr || parent->next == nullptr) {
        cout << "ERROR: parent is null";
        return;
      }
      node *el = parent->next;
      parent->next = el->next;
      if (el == this->tail) {
        this->tail = parent;
      }
      delete el;
    }
    this->size -= 1;
  }

  node *to_index(int idx) {
    if(idx >= this->size){
      return this->tail;
    }
    else if(idx < 0){
      return nullptr;
    }
    node *current = this->root;
    node *target = nullptr;
    int i = 0;
    while (current){
      if(i == idx){
        target = current;
      }
      current = current->next;
      i +=1;
    }
    delete current;
    return target;
  }

  void display() {
    node *current = this->root;
    while (current) {
      cout << current->value << " ->  ";
      current = current->next;
    }
    cout << "null";
    delete current;

  };

  int getSize() { return this->size; }

private:
  node *root;
  node *tail;
  int size;
};

int main(int argc, char *argv[]) {
  LinkedList linkedList = LinkedList();
  ::LinkedList();
  linkedList.append(10);
  linkedList.append(14);
  linkedList.append(12);
  linkedList.append(20);
  linkedList.append(90);
  linkedList.prepend(1);
  linkedList.insert(5, 200);
  linkedList.display();
  cout << "\n" << linkedList.getSize();
  return 0;
}

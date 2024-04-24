package interfaces;

import base.Node;

public interface CustomList<T> {
    void append(T value);

    void prepend(T value);

    void insert(int index, T value);

    int indexOf(T value);

    T get(Integer index);

    T pop();

    T peek();

    int length();

    void show();
}

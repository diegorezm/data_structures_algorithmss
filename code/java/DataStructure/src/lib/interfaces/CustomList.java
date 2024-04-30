package lib.interfaces;

public interface CustomList<T> {
    void push(T value);

    int indexOf(T value);

    T get(Integer index);

    T pop();

    T peak();

    int length();

    void show();

    boolean isEmpty();
}

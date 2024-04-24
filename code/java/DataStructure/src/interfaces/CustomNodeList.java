package interfaces;

import base.Node;

public interface CustomNodeList<T> extends CustomList<T> {
    Node<T> getNode(Integer index);
}

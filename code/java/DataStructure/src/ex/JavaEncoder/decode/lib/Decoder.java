package decode.lib;

import encode.lib.Leaf;
import encode.lib.Node;

public class Decoder {
    private final String token;
    private final Node root;

    public Decoder(String token, Node root) {
        this.token = token;
        this.root = root;
    }

    public String getDecodedString() {
        StringBuilder content = new StringBuilder();
        Node currentNode = this.root;
        for (char bit : this.token.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.getLeft();
            } else if (bit == '1') {
                currentNode = currentNode.getRight();
            }
            if (currentNode instanceof Leaf leaf) {
                content.append(leaf.getC());
                currentNode = this.root;
            }
        }
        return content.toString();
    }

}
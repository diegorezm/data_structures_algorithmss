package encode.lib;

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public String[] getHuffmanCodes(Leaf[] leaves) {
        String[] huffmanCodes = new String[leaves.length];
        generateCodes(root, "", huffmanCodes, leaves);
        return huffmanCodes;
    }

    private void generateCodes(Node node, String code, String[] huffmanCodes, Leaf[] leaves) {
        if (node == null)
            return;
        // Se o nó atual for uma folha, atribua o código gerado a essa folha
        if (node instanceof Leaf leaf) {
            // Percorre o array de folhas para encontrar o índice correspondente à folha
            // atual
            for (int i = 0; i < leaves.length; i++) {
                if (leaves[i].getC() == leaf.getC()) {
                    huffmanCodes[i] = code;
                    break;
                }
            }
        }
        // Para a esquerda + 0
        generateCodes(node.getLeft(), code + '0', huffmanCodes, leaves);
        // Para a direita + 1
        generateCodes(node.getRight(), code + '1', huffmanCodes, leaves);
    }

    public Node getRoot() {
        return this.root;
    }
}

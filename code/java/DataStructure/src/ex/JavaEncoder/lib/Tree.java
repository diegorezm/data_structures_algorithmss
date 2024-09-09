package lib;

import java.util.Arrays;

public class Tree {
    private Node root;

    public Tree(Leaf[] leaves) {
        // Copiando o array de folhas para uma fila
        Node[] queue = Arrays.copyOf(leaves, leaves.length, Node[].class);

        int size = queue.length;

        // Enquanto o tamanho da fila for maior que 1, continue o processo de criação da
        // árvore
        while (size > 1) {
            // Ordenando a fila (do menor para o maior) com base na frequência dos nós
            Arrays.sort(queue, 0, size);

            // Como a fila está ordenada, os dois primeiros nós têm as menores frequências
            Node left = queue[0];
            Node right = queue[1];

            // Criando um novo nó pai (parent) a partir desses dois nós
            // O novo nó pai terá como frequência a soma das frequências dos nós filhos
            Node parent = new Node(left, right);

            // Removendo os dois nós utilizados da fila e inserindo o novo nó pai
            for (int i = 1; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }

            // Substituindo o primeiro elemento da fila pelo novo nó pai
            queue[0] = parent;
            size--;
        }
        // Após o loop, o último nó restante na fila se torna a raiz da árvore
        this.root = queue[0];
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
        if (node instanceof Leaf) {
            Leaf leaf = (Leaf) node;
            // Percorre o array de folhas para encontrar o índice correspondente à folha
            // atual
            for (int i = 0; i < leaves.length; i++) {
                if (leaves[i].getC() == leaf.getC()) {
                    huffmanCodes[i] = code; // Armazena o código gerado na posição correta do array
                    break;
                }
            }
        }
        // Para a esquerda + 0
        generateCodes(node.getLeft(), code + '0', huffmanCodes, leaves);
        // Para a direita + 1
        generateCodes(node.getRight(), code + '1', huffmanCodes, leaves);
    }
}

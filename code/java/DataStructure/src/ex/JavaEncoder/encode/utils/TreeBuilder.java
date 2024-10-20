package encode.utils;

import java.util.Arrays;

import encode.lib.Leaf;
import encode.lib.Node;

public class TreeBuilder {
    private static final int ASCII_SIZE = 256;
    private final int[] frequency = new int[ASCII_SIZE];
    private char[] letters;

    public TreeBuilder(char[] letters) {
        setup(letters);
    }

    public int getCharacterFrequency(char c) {
        if (c >= ASCII_SIZE) {
            return 0;
        }
        return this.frequency[c];
    }

    public Leaf[] getSortedNodesByFrequency() {
        Leaf[] nodes = new Leaf[letters.length];
        for (int i = 0; i < letters.length; i++) {
            Leaf node = new Leaf();
            node.setC(letters[i]);
            node.setFrequency(frequency[letters[i]]);
            nodes[i] = node;
        }
        // Comparator já esta implementado na classe Node
        Arrays.sort(nodes);
        return nodes;
    }

    public Node getRootNode() {
        Leaf[] leafs = this.getSortedNodesByFrequency();
        Node[] queue = Arrays.copyOf(leafs, leafs.length, Node[].class);
        int size = queue.length;
        while (size > 1) {
            Arrays.sort(queue, 0, size);

            var child1 = queue[0];
            var child2 = queue[1];

            var parent = new Node(child1, child2);

            for (int i = 0; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
            queue[0] = parent;
            size--;
        }
        Node root = queue[0];
        return root;
    }

    private void setup(char[] words) {
        // seen[char] = true/false (visto/não visto)
        boolean[] seen = new boolean[ASCII_SIZE];
        int uniqueCount = 0;

        for (char c : words) {
            if (c >= ASCII_SIZE) {
                System.out.println("INVALID CHARACTER: " + c);
                continue;
            }
            if (!seen[c]) {
                seen[c] = true;
                uniqueCount++;
            } else {
                this.frequency[c]++;
            }
        }
        char[] result = new char[uniqueCount];
        int index = 0;
        for (char c : words) {
            if (c >= ASCII_SIZE) {
                continue;
            }
            if (seen[c]) {
                result[index++] = c;
                seen[c] = false;
            }
        }
        this.letters = result;
    }

    public int[] getFrequency() {
        return frequency;
    }

    public char[] getLetters() {
        return letters;
    }
}

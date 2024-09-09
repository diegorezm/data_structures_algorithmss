package utils;

import java.util.Arrays;
import java.util.Comparator;

import lib.Leaf;
import lib.Node;

public class CharFrequency {
    private static final int ASCII_SIZE = 128;
    private int[] frequency = new int[ASCII_SIZE];
    private char[] letters;

    public CharFrequency(char[] letters) {
        setUniqueChars(letters);
        setFrequency(letters);
    }

    public int getCharacterFrequency(char c) {
        var frequency = this.frequency[c];
        return frequency;
    }

    public Leaf[] getSortedNodesByFrequency() {
        Leaf[] nodes = new Leaf[letters.length];
        for (int i = 0; i < letters.length; i++) {
            Leaf node = new Leaf();
            node.setC(letters[i]);
            node.setFrequency(frequency[letters[i]]);
            nodes[i] = node;
        }
        Arrays.sort(nodes, Comparator.comparingInt(Node::getFrequency));
        return nodes;
    }

    private void setUniqueChars(char[] words) {
        boolean[] seen = new boolean[ASCII_SIZE];
        int uniqueCount = 0;

        for (char c : words) {
            if (c >= 0 && c < ASCII_SIZE && !seen[c]) {
                seen[c] = true;
                uniqueCount++;
            }
        }
        char[] result = new char[uniqueCount];
        int index = 0;
        for (char c : words) {
            if (c >= 0 && c < ASCII_SIZE && seen[c]) {
                result[index++] = c;
                seen[c] = false;
            }
        }
        this.letters = result;
    }

    private void setFrequency(char[] message) {
        for (char c : message) {
            frequency[c]++;
        }
    }

    public static int getAsciiSize() {
        return ASCII_SIZE;
    }

    public int[] getFrequency() {
        return frequency;
    }

    public char[] getLetters() {
        return letters;
    }
}

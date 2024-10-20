package encode;

import encode.lib.Leaf;
import encode.lib.Tree;
import encode.utils.EncodeReader;
import encode.utils.EncodeWriter;
import encode.utils.TreeBuilder;
import encode.utils.TreeParser;

public class JavaEncoder {
    public static Tree run(String path) throws Exception {
        EncodeReader encodeReader = new EncodeReader(path);

        if (encodeReader.getContent() == null) {
            throw new Exception();
        }

        var charContent = encodeReader.toCharArray();
        TreeBuilder treeIntz = new TreeBuilder(charContent);
        Leaf[] leafNodes = treeIntz.getSortedNodesByFrequency();

        if (leafNodes.length == 0) {
            System.out.println("ERRO: não há caracteres para encriptar.");
            return null;
        }

        Tree tree = new Tree(treeIntz.getRootNode());
        String[] huffmanCodes = tree.getHuffmanCodes(leafNodes);

        char[] characters = new char[leafNodes.length];
        String[] codes = new String[leafNodes.length];

        for (int i = 0; i < leafNodes.length; i++) {
            characters[i] = leafNodes[i].getC();
            codes[i] = huffmanCodes[i];
        }

        TreeParser tParser = new TreeParser(charContent, characters, codes);

        tParser.getCharCodeTable();
        System.out.println("\nToken: " + tParser.getToken());
        EncodeWriter.write(tParser.getToken());
        return tree;
    }
}

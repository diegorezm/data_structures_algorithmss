import java.util.Scanner;

import lib.Leaf;
import lib.Tree;
import utils.CharFrequency;
import utils.Reader;
import utils.TreeParser;

public class JavaEncoder {
  public static void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Insira o caminho do arquivo: ");
    String path = scanner.nextLine();

    Reader reader = new Reader(path);
    var charContent = reader.toCharArray();
    CharFrequency charFrequency = new CharFrequency(charContent);
    Leaf[] leafNodes = charFrequency.getSortedNodesByFrequency();

    if (leafNodes.length == 0) {
      System.out.println("No characters to encode.");
      scanner.close();
      return;
    }

    Tree tree = new Tree(leafNodes);
    String[] huffmanCodes = tree.getHuffmanCodes(leafNodes);

    char[] characters = new char[leafNodes.length];
    String[] codes = new String[leafNodes.length];

    for (int i = 0; i < leafNodes.length; i++) {
      characters[i] = leafNodes[i].getC();
      codes[i] = huffmanCodes[i];
    }

    TreeParser tParser = new TreeParser(charContent, characters, codes);
    boolean isRunning = true;
    while (isRunning) {
      System.out.println("-----------------------");
      System.out.println("Escolha uma ação: ");
      System.out.println(
          "1 - Printar a tabela\n2 - Printar o token gerado\n3 - Printar o token e a tabela\n4 - Sair");
      System.out.println("-----------------------");
      String action = scanner.nextLine();
      switch (action) {
        case "1":
          tParser.getCharCodeTable();
          break;
        case "2":
          System.out.println("Token: " + tParser.genEncodedString());
          break;
        case "3":
          tParser.getCharCodeTable();
          System.out.println("Token: " + tParser.genEncodedString());
          break;
        default:
          isRunning = false;
          break;
      }
    }
    scanner.close();
  }
}

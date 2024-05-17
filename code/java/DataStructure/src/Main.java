import exercicios.ex1.DecToBin;
import exercicios.ex2.QueueWithStack;
import lib.nodeList.recursive.RecursiveNodeList;

public class Main {
    public static void main(String[] args) {
        RecursiveNodeList list = new RecursiveNodeList();
        list.push(7);
        list.push(2);
        list.push(5);
        list.push(3);
        list.push(3);
        list.push(6);
        list.printEven();
    }
}
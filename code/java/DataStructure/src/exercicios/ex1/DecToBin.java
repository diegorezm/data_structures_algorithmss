package exercicios.ex1;

import lib.stack.NodeStack;

public class DecToBin {
    public static String decToBin(int n){
        NodeStack<Integer> stack = new NodeStack<>();
        while (n != 0){
            int remainder = n % 2;
            stack.push(remainder);
            n /= 2;
        }
        return stack.toString();
    }
}

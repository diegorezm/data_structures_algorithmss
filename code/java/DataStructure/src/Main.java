import array.Arr;
import stack.NodeStack;

public class Main {
    public static void main(String[] args) {
        Arr<Integer> arr = new Arr<>();
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.prepend(4);
        arr.insert(2,8);
        arr.show();
    }
}
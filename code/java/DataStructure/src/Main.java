import array.Arr;
import interfaces.CustomList;

public class Main {
    public static void main(String[] args) {
        CustomList<Integer> arr = new Arr<>();
        arr.append(1);
        arr.show();
    }
}
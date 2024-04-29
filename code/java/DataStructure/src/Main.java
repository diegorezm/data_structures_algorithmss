import array.Arr;
import interfaces.CustomList;

public class Main {
    public static void main(String[] args) {
        CustomList<Integer> arr = new Arr<>();
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.insert(1,8);
        arr.show();
    }
}
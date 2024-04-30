import exercicios.ex1.DecToBin;
import exercicios.ex2.QueueWithStack;

public class Main {
    public static void main(String[] args) {
        System.out.println(DecToBin.decToBin(12));
        QueueWithStack qs = new QueueWithStack();
        qs.push(7);
        qs.push(8);
        qs.push(9);
        qs.push(10);
        qs.pop();
        System.out.println(qs.peak());
        System.out.println(qs.isEmpty());
        qs.show();
    }
}
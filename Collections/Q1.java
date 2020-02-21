package Collections;
//1.
//Write Java code to define List .
// Insert 5 floating point numbers in List, and using an iterator, find the sum of the numbers in List.
import java.util.ArrayList;

public class Q1 {
    public static void main(String[] args) {
        ArrayList<Float> f = new ArrayList<Float>();
        f.add(3.4f);
        f.add(4.4f);
        f.add(5.4f);
        f.add(6.4f);
        f.add(7.4f);

        Float sum = 0.0f;

        for (int i = 0; i < f.size(); i++) {
            sum = sum + f.get(i);
        }


        System.out.println("The sum of the elements in the list is: " + sum);


    }
}

package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Q4.  Write a program to sort HashMap by value.
public class Q4 {
    public static void main(String[] args) {

        HashMap<Integer,String> map = new HashMap<Integer, String>();
        map.put(2,"cat");
        map.put(5,"dog");
        map.put(6,"anupam");
        map.put(10,"lakshya");
        map.put(7,"rabbit");
        map.put(1,"goose");
        ArrayList<String> val = new ArrayList<String>();
        for (Integer key : map.keySet()){
            String value = map.get(key);
            val.add(value);

        }

        Collections.sort(val);
        System.out.println(val);

    }
}

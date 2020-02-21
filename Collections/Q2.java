package Collections;

import java.util.HashSet;

//2. Write a method that takes a string and returns the number of unique characters in the string.
public class Q2 {
    static void countCharacter(String str)
        {
            char charArray[] = str.toCharArray();
            HashSet<Character> hashSet = new HashSet<>();
            for(int i =0;i<charArray.length; i++)
            {
                hashSet.add(charArray[i]);
            }
            System.out.println("number of unique characters in Subarno Chatterjee : "+hashSet.size());
        }
        public static void main(String[] args) {
            String myString = "Subarno Chatterjee";
            countCharacter(myString);

        }

}

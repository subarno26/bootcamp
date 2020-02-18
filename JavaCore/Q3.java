package JavaCore;

public class Q3 {
    public static void main(String[] args) {
        //Q3. Write a program to find the number of occurrences of a character in a string without using loop?


        String s="Hello World and Ukulele!";
        String element = "l";
        System.out.println(s.length()-(s.replace("l","")).length());
    }
}

package JavaCore;

public class Q1 {
    public static void main(String[] args) {
        //Q1. Write a program to replace a substring inside a string with other string ?
        String s1= "Hello";
        String s2= "World!";
        System.out.println(s1.replace(s1.substring(1,3),s2));
    }
}

package JavaCore;

//Q10.Write a single program for following operation using overloading
//  A) Adding 2 integer number
//  B) Adding 2 double
//  C) multiplying 2 float
//  D) multiplying 2 int
//  E) concatenate 2 string
//  F) Concatenate 3 String


public class Q10 {


    int sum(int a, int b){
        return a+b;
    }
    double sum(double a, double b){
        return a+b;
    }

    float multiply(float a, float b){
        return a*b;
    }

    int multiply(int a, int b){
        return a*b;
    }
    String concat(String a, String b){
        return a+b;
    }


    String concat(String a, String b, String c){
        return a+b+c;
    }


    public static void main(String[] args) {
        Q10 q = new Q10();
        System.out.println(q.sum(4,5));
        System.out.println(q.sum(4.01,5.99));
        System.out.println(q.multiply(18,7));
        System.out.println(q.multiply(18.7f,9.8f));
        System.out.println(q.concat("Hello"," World!"));
        System.out.println(q.concat("Hey"," there"," beautiful!"));
    }



}

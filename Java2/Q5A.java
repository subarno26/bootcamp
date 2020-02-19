package Java2;
//5. WAP to show object cloning in java using cloneable and copy constructor both.

import java.util.Scanner;

public class Q5A implements Cloneable {

        int rollno;
        String name;

        Q5A(int rollno, String name){
            this.rollno=rollno;
            this.name=name;
        }

        public Object clone()throws CloneNotSupportedException{
            return super.clone();
        }

        public static void main(String args[]){
            try{
                Q5A s1=new Q5A(101,"amit");

                Q5A s2=(Q5A) s1.clone();

                System.out.println(s1.rollno+" "+s1.name);
                System.out.println(s2.rollno+" "+s2.name);

            }catch(CloneNotSupportedException c){}

        }


}

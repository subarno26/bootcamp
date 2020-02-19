package Java2;
//9.  Design classes having attributes for furniture where there are wooden chairs and tables, metal chairs and tables.
// There are stress and fire tests for each products.
import java.util.Scanner;

public class Q9 {
    public static void main(String[] args){
        System.out.println("Enter Table Type");
        Q9_table table = null;
        Scanner input =  new Scanner(System.in);
        String str = input.next();
        if(str.equals("wooden")){
            table = new Q9_woodenTable();
        }
        else if (str.equals("metal")){
            table = new Q9_metalTable();
        }

        System.out.println(table.tableType());
        table.stressTest();
        table.fireTest();

        System.out.println("Enter Chair Type");
        Q9_Chair chair = null;
        Scanner input1 =  new Scanner(System.in);
        String str1 = input.next();
        if(str1.equals("wooden")){
            chair = new Q9_woodenChair();

        }
        else if (str1.equals("metal")){
            chair = new Q9_metalChair();
        }

        System.out.println(chair.chairType());
        chair.stressTest();
        chair.fireTest();




    }
}

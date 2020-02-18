package JavaCore;

/*Q9.Write a program to display values of enums using a constructor
& getPrice() method (Example display house & their prices)*/

enum House{
    HOUSE1(10000),HOUSE2(179000), HOUSE3(2800000), HOUSE4(30000);
    private int price;
    House(int x){
        price = x;
    }
    int getPrice(){
        return price;
    }

}

public class Q9 {

    public static void main(String[] args){
        System.out.println("Price of the first house is: "+ House.HOUSE1.getPrice());
        System.out.println("Price of the second house is: "+House.HOUSE2.getPrice());
        System.out.println("Price of the third house is: "+House.HOUSE3.getPrice());
        System.out.println("Price of the fourth house is: "+House.HOUSE4.getPrice());
    }
}
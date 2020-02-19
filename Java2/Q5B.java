package Java2;
//Copy constructor
public class Q5B implements Cloneable{

        int rollno;
        String name;

        Q5B(int rollno, String name){
            this.rollno=rollno;
            this.name=name;
        }

        public Object clone()throws CloneNotSupportedException{
            return super.clone();
        }

        public static void main(String args[]){
            try{
                Q5B s1=new Q5B(101,"amit");

                Q5B s2=(Q5B)s1.clone();

                System.out.println(s1.rollno+" "+s1.name);
                System.out.println(s2.rollno+" "+s2.name);

            }catch(CloneNotSupportedException c){}

        }

}

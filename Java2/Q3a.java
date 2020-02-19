package Java2;
//3. WAP to produce NoClassDefFoundError and ClassNotFoundException exception.
import com.sun.org.apache.bcel.internal.generic.LoadClass;

public class Q3a {
    public static void main(String[] args) {
        try{
            Class.forName("Subarno");
        }catch (ClassNotFoundException e){
            System.out.println(e.getStackTrace());
        }
    }
}




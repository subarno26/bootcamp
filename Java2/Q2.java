package Java2;
//2. WAP to sorting string without using string Methods?
public class Q2 {
    public static void main(String[] args) {
        String s = "helloadb";
        char a[] = s.toCharArray();
        char temp = 0;
        for (int i = 0; i<a.length; i++){
            for (int j=i+1; j<a.length;j++){
                if (a[i]>a[j]){
                    temp = a[j];
                    a[j]=a[i];
                    a[i]=temp;
                }
            }
        }

        for (int n=0; n<a.length; n++){
            System.out.println(a[n]);
        }
    }
}

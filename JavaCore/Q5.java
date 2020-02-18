package JavaCore;
//Q5. Find common elements between two arrays.
public class Q5 {

    public static void main(String[] args) {
        //Q5. Find common elements between two arrays.


        int arr1[]={1,2,3,4,5,6};
        int arr2[]= {2,3,5};
        for(int i=0; i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                if (arr1[i]==arr2[j]){
                    System.out.println(arr1[i]);
                }
            }
        }
    }
}

package exercise1
//Q5. Find common elements between two arrays.
fun main(){
    val array1 = arrayOf(1,2,3,4,5,6,7)
    val array2 = arrayOf(3,4,7,0,9)
    for (i in array1){
        for (j in array2){
            if (i==j){
                println(i)
            }
        }
    }
}
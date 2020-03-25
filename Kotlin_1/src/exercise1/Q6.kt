package exercise1
//Q6. Check letter in string which do not have pair.
fun main(){
    println("Enter a string: ")
    val input = readLine().toString()
    val str = input.toLowerCase().trim()
    val arr = str.split("").toTypedArray()
    for (i in arr.indices){
        var duplicate = 1
        for (j in i+1 until arr.size){
            if (arr[i] == arr[j]){
                duplicate++
                arr[j]= "0"

            }
        }
        if (arr[i] != "0" && duplicate == 1 ){
            println(arr[i])
        }
    }

}





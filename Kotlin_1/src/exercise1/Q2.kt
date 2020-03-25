package exercise1
//Q2. Write a program to find the number of occurrences of the duplicate words in a string and print them.
fun main(){
    val str = readLine().toString()
    val lowerStr = str.toLowerCase()
    val arr = lowerStr.split(" ").toTypedArray()

    for (i in arr.indices) {
        var duplicate = 1
        for (j in i+1 until arr.size) {
            if (arr[i] == arr[j]) {
                duplicate++
                arr[j] = "0"
            }
        }
        if (duplicate > 1 && arr[i] != "0")
            println("Word: " + arr[i] + " \nOccurences: " + duplicate)
    }
}


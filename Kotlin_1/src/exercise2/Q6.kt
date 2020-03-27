package exercise2

fun main(){
    val list1 = mutableListOf<Int>(17,23,31,64,57,89,93)
    println("The list is as follows: $list1")
    println("Enter index for replacement: ")
    val index = Integer.valueOf(readLine())
    println("Enter value for replacement: ")
    val value = Integer.valueOf(readLine())

    list1.set(index,value)
    println("The list after replacement is: $list1")
}
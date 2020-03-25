package exercise1
//Q1. Write a program to replace a substring inside a string with another string.
fun main(args: Array<String>){
    println("Enter the string: ")
    val string = readLine()
    println("Indices for substring")
    println("Enter start index: ")
    val start:Int = Integer.valueOf(readLine());

    println("Enter end index")
    val end:Int  = Integer.valueOf(readLine());

    println("Enter new string for replacement:")
    val newString: String = readLine().toString()

    val replaceString = string?.replace(string.substring(start,end),newString)
    println("String after replacement: ")
    println(replaceString)

//    val str="Hello World"
//    val index = str.replace(str.substring(3,7),"NEWSTRING")
//
//    print(index)
}


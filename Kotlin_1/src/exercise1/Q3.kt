package exercise1
//Q3. Write a program to find the number of occurrences of a character in a string without using loop.
fun main(){
    println("Enter string: ")
    val string = readLine().toString();

    println("Enter character to find the occurences of: ")
    val char = readLine().toString();

    val count:Int = string.length - (string.replace(char,"")).length

    println("The occurences of $char in the string $string is: $count ")
}
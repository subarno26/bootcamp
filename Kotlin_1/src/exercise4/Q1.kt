package exercise4

fun main(){
    val calc = {p:Double, r:Float, t: Int -> (p*r*t)/100}

    println("Enter the values to calculate Interest")
    println("Enter the principle amount: ")
    val principal = Integer.valueOf(readLine()).toDouble()
    println("Enter the rate of interest: ")
    val interest = Integer.valueOf(readLine()).toFloat()
    println("Enter the time period: ")
    val time = Integer.valueOf(readLine())

    println("The interest is: ${calc(principal,interest,time)}")
}
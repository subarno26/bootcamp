package exercise2

fun grade(marks:Int):String{
    when (marks) {
        in 50..60 -> {
            return "Good"
        }
        in 60..70 -> {
            return "Very Good"
        }
        in 70..80 -> {
            return "Excellent"
        }
        in 80..100 ->{
            return "Rockstar"
        }
        else -> {
            return "poor"
        }
    }
}

fun main(){
    println("Enter marks: ")
    val input:Int = Integer.valueOf(readLine())
    println(grade(input))
}
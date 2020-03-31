package exercise3

class A{
    init {
        println("Init block of class A")
    }
    fun inner(){
        println("inner function of class A")
    }
}

fun A.extension(){
    println("This is the extension function of class A")
}

fun main(){
    //Normal function call
    val a1 = A()
    a1.inner()
    
    //Extension function call
    a1.extension()
}
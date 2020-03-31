package exercise3

object SingletonExample{
    init {
        println("Singleton pattern")
    }

    fun singletonFunction(){
        println("Singleton Function")
    }
}

class SingletonClass{
    init {
        SingletonExample.singletonFunction()
    }
}

fun main(){
    val s = SingletonClass()
}
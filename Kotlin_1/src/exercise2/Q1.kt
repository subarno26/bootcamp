package exercise2

class Details(fname:String,lname:String,age:Int){

    init {
        val nameFirst = fname
        val nameLast = lname
        val age1 = age
        println("Init block called")
        println("First Name is: $nameFirst \nLast Name is: $nameLast \nAge is: $age1")
    }

}

class DetailsComp(){
    companion object{
        val fName = "Subarno"
        val lName = "Chatterjee"
        val age = 21
    }
}



fun main(){
    val initBlock = Details("Subarno", "Chatterjee", 22)
    println("Companion object called")
    println(DetailsComp.fName)
    println(DetailsComp.lName)
    println(DetailsComp.age)


}
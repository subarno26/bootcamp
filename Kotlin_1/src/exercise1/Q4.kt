package exercise1
//Q4. Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String.
fun main(){
    val input: String = readLine().toString()
    val string = input.trim()
//    val string:String = "hello1@34Up@"
    var upperCounter =0
    var lowerCounter =0
    var digitCounter =0
    var specialCounter =0
    for (i in string){
        when {
            i.isUpperCase() -> {
                upperCounter++
            }
            i.isLowerCase() -> {
                lowerCounter++
            }
            i.isDigit() -> {
                digitCounter++
            }
            else -> {
                specialCounter++
            }
        }

    }

    println("The count is as follows: ")
    println(" Uppercase characters: $upperCounter \n Lowercase characters: $lowerCounter\n Digits: $digitCounter\n Special characters: $specialCounter" )

    val length = string.length.toDouble()
    //println(length)
    val pUpper = (upperCounter/length)*100
    val pLower = (lowerCounter/length)*100
    val pDigit = (digitCounter/length)*100
    val pSpecial = specialCounter/length *100

    println("The percentages are as follows: \nUpper characters: $pUpper \nLower characters: $pLower \nDigits: $pDigit \nSpecial characters: $pSpecial")
}
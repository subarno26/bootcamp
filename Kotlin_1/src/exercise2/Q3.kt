package exercise2

open class Bank{
    var rateOfInterest:Int = 0
    var bankName:String =""
    var bankAddress:String = ""
}

class BOI: Bank() {

    fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    fun getDetails(){
        println("Bank name: "+this.bankName)
        println("Bank address: " + this.bankAddress)
        println("rate of interest: " + this.rateOfInterest)
        println("\n")
    }
}

class ICICI: Bank(){

    fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    fun getDetails(){
        println("Bank name: "+this.bankName)
        println("Bank address: " + this.bankAddress)
        println("rate of interest: " + this.rateOfInterest)
        println("\n")
    }
}

class SBI: Bank(){

    fun setDetails(roi: Int, bName: String, bAddress: String){
        this.rateOfInterest = roi
        this.bankName = bName
        this.bankAddress = bAddress
    }

    fun getDetails(){
        println("Bank name: "+this.bankName)
        println("Bank address: " + this.bankAddress)
        println("rate of interest: " + this.rateOfInterest)
        println("\n")
    }
}

fun main(){
    val sbi = SBI()
    val boi = BOI()
    val icici = ICICI()

    sbi.setDetails(7,"SBI","Noida")
    boi.setDetails(9,"BOI","Greater Nodia")
    icici.setDetails(8,"ICICI","Delhi")

    sbi.getDetails()
    boi.getDetails()
    icici.getDetails()
}
package exercise4

fun main(){
    val hmap:HashMap<String,Int> = HashMap()
    hmap.put("Subarno",42)
    hmap.put("Tanvi",37)
    hmap.put("Ashutosh",71)
    hmap.put("Anupam",23)
    hmap.put("Lakshya",21)

    println("Names of students with age > 30")
    for (i in hmap.keys){
        if (hmap[i]!! > 30){
            println(i)
        }
    }

}
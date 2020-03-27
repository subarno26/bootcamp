package exercise2

fun main() {
    val map: MutableMap<Int, String> = mutableMapOf<Int, String>(1 to "Ashutosh", 4 to "Lakshya", 2 to "Anupam",
        3 to "Subarno", 5 to "Tanvi")

    println("Map:")
    for (key in map.keys)
        println("Keys: $key, Value: ${map[key]}")
}
/**
 * 泛型
 */

class Box<T>(t: T) {
    var value = t
}

//泛型约束

fun main(){
    val boxString=Box<String>(" I am String type")
    val boxInt=Box<Int>(100)

    println(boxString.value)
    println(boxInt.value)

}
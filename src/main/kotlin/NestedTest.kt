/**
 *
 * Nested and inner classes
 * 嵌套和内部类
 *
 */
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
    inner class Inner {
        fun foo() = bar
    }
}



fun main(){
    val demo:Int = Outer.Nested().foo() // == 2
    val inner = Outer().Inner().foo()// == 2
    println(demo)
    println(inner)
}

/**
 * 委托
 * Delegation
 */

interface Base {
    fun print()
}

class BaseImpl(private val number: Int) : Base {
    override fun print() =println(number)
}

//委托
class Derived (b: Base) : Base by b {

    override fun print() {
        //为啥这里不能用b 的属性和方法？这样设计为何？
        println("委托：")
    }

}
class Derived2(private val base: Base) {

   fun print(){

       println("代理模式：")
       base.print()
   }
}

fun main() {
    val b = BaseImpl(10)

    //委托给 Derived
    Derived(b).print()

    Derived2(b).print()
}
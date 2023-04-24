/**
 *
 * 扩展函数
 */
class ExtensionsTest {

    fun test(){

        val sum=8

        println(sum.count(2))
    }


    /**
     * 扩展一个Int 的函数。
     * Java 的代码
     * int count(int $this$count, int number) {
     *       return $this$count + number;
     *    }
     */
    private fun Int.count(number: Int):Int{

        return this+number
    }
}

fun main(){


    ExtensionsTest().test()
}
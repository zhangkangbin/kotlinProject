package list

fun main(){
    val numbers = setOf(1, 2, 3)
    //map 修改 值
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })


    //filter条件过滤
    println(numbers.filter {
        it>1
    })

}
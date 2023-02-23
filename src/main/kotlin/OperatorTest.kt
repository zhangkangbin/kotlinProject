/**
 * 运算符重载的例子
 *
 *@see https://book.kotlincn.net/text/operator-overloading.html
 */
data class Point(val x: Int, val y: Int){


    operator fun plus(p:Point) :Point= Point(x+p.x, y+p.y)
    //重载减号
    operator fun minus(p:Point) :Point= Point(x-p.x, y-p.y)

    //重载 - 号。变负数。
    operator fun unaryMinus() :Point= Point(-x, -y)
}

data class Score(val score: Int){
   //重载 ! 号
    operator fun not() =
        if(score>80){
            "优秀如你！"
        }
       else if(score>60){
        "及格"
    }else{
        "不及格"
    }
}
fun main() {
    val point1 = Point(10, 20)
    val point2 = Point(30, 50)
    // 输出“Point(x=-10, y=-20)”
    println(point1 + point2)

    println(point2 -point1)
    val score = Score(90)
    println(!score)
    //println(-score!)
}
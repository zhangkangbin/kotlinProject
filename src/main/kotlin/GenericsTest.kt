/**
 * Generics
 * 泛型
 * out in
 * where  支持多条件来，限制泛型类型，
 */
open class BaseBean<in T>{

    var code:Int = 0;
   var text:String = "初始化";
    var obj: @UnsafeVariance T?=null;

}
 open class Person<T>()  {
    var age: T? = null;
}



 class StringBean() : BaseBean<String>() {



}
open class ListStringBean : BaseBean<MutableList<String>>() {



}
fun  getList(): MutableList<String> {
    val mutableList = mutableListOf("Ajax","Maxsu","Praka","Maxsu")
    mutableList.add("A")

    return mutableList;
}
//泛型约束

fun main(){



    val string=StringBean();
    string.text="成功获得数据"

    println( string.text)

    val list=ListStringBean();
    list.text="成功获得list数据"

    list.obj=getList();

    println( list.text)
    list.obj?.let { it ->
        it.forEach {

            println(it)
        }
    }

    //Java的泛型是不支持，向上或者向下转型的。
    //子类转父类，用out
    val foodOut: ListOut<Object> = ListOut<StringObj>()
    //val foodOut2: ProductionOut<FastFood> = ProductionOut<Food>()

    // 父类转子类，用in
    val foodIn: ListIn<StringObj> = ListIn<Object>()
    //val foodIn2: ProductionIn<Food> = ProductionIn<FastFood>()

    println(foodOut.product())   // 输出 a
    println(foodIn.product())   // 输出 a
}
// 定义一个支持协变的类
private class ListOut<out T>() {
    fun product() {

        println("productOut food!")
    }
}

class ListIn<in T>() {
    fun product() {

        println("productIn food!")
    }
}


// 定义一个支持逆变的类


open class Object{


}
class StringObj : Object(){


}
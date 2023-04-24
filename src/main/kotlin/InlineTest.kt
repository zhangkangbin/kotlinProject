/**
 *
 * 内联函数
 * 节约内存
 *inline 使用 inline 修饰类已经被弃用了。
 *
 * The inline modifier for inline classes is deprecated!
 */
@JvmInline
value class Password(val data: Data)
@JvmInline
value class Password2(val data: Int)


interface MyInterface {
    fun bar()
    fun foo() = "foo"
}

@JvmInline
value class MyInterfaceWrapper(private val myInterface: MyInterface) : MyInterface by myInterface
data class Data(val key: Int,val value: String)
fun main() {
    // 不存在 'Password' 类的真实实例对象
    // 在运行时，'securePassword' 仅仅包含 'String'
    val securePassword = Password(Data(1,"kang"))

    println(securePassword.data.value )
    //      使用内联，少创建一个对象 。如果不使用Data ,都是基本类似，那出来都是 Int String 等基本类型。
    //      Data securePassword = Password.constructor-impl(new Data(1, "kang"));
    //      String var1 = securePassword.getValue();
    //      System.out.println(var1);

    //      没有使用内联 多创建一个对象 Password
    //      Password securePassword = new Password(new Data(1, "kang"));
    //      String var1 = securePassword.getData().getValue();
    //      System.out.println(var1);

    val my = MyInterfaceWrapper(object : MyInterface {
        override fun bar() {
            // body
        }
    })
    println(my.foo()) // prints "foo"

  //  val (name, age) = person
}
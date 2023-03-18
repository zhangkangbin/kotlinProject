import kotlin.reflect.KProperty

/**
 * 委托,属性委托，标准委托
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
    val base=b;
    override fun print() {
        //为啥这里不能用b 的属性和方法？这样设计为何？
        println("委托：")
    }
}

/**  todo:Derived 会生成以下这个代码。其实就是一个代理模式。
 * public final class Derived implements Base {
 *    // $FF: synthetic field
 *    private final Base $$delegate_0;
 *
 *    public void print() {
 *       String var1 = "委托：";
 *       System.out.println(var1);
 *    }
 *
 *    public Derived(@NotNull Base b) {
 *       Intrinsics.checkNotNullParameter(b, "b");
 *       super();
 *       this.$$delegate_0 = b;
 *    }
 * }
 */
class Derived2(private val base: Base) {
   fun print(){
       println("代理模式：")
       base.print()
   }
}

/**
 * 属性委托
 */


class Delegate  {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }


}
class Example {
    var p: String by Delegate()
}

/**
public final class Example {
// $FF: synthetic field
static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Example.class, "p", "getP()Ljava/lang/String;", 0))};
@NotNull
private final Delegate p$delegate = new Delegate();

@NotNull
public final String getP() {
return this.p$delegate.getValue(this, $$delegatedProperties[0]);
}

public final void setP(@NotNull String var1) {
Intrinsics.checkNotNullParameter(var1, "<set-?>");
this.p$delegate.setValue(this, $$delegatedProperties[0], var1);
}
}

 */
fun main() {
    val b = BaseImpl(10)

    //委托给 Derived
    Derived(b).print()

    Derived2(b).print()

    //属性委托
    val e = Example()
    println(e.p)
    e.p = "NEW"//会调用setValue函数
    println("属性委托：${e.p}")

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    println(lazyValue)   // 第一次执行，执行两次输出表达式
    println(lazyValue)   // 第二次执行，只输出返回值

}
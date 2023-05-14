package test

import java.io.Serializable
import java.lang.reflect.Field
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Screen : Serializable {

 }

class A:Screen{

}
class B:Screen{

}
class Navigator:Stack<Screen> by toMutableStateStack(minSize = 1){



}
public interface Stack<Item> {

    fun  getName():Int
    fun add(view:Screen)
}
public class SnapshotStateStack<Item>() : Stack<Item> {

    private var name:Int=0
    override fun getName()=name

    override fun add(view: Screen) {
        name++
    }
}
public fun <Item> toMutableStateStack(
    minSize: Int = 0
): SnapshotStateStack<Item> =
    SnapshotStateStack()

fun  main(){


    val nav=Navigator()
    nav.add(A())
    nav.add(B())

    println( nav.getName())
    println( nav.javaClass.superclass)
    println( nav.javaClass.interfaces[0])
    val someImplementation = Navigator()


/*
    val privateStringField: Field = Navigator::class.java.interfaces[0].getDeclaredField("name")

    privateStringField.isAccessible = true

    //val data=SnapshotStateStack<Screen>()

    val list=privateStringField.get(nav)

    println(list)
*/




    //源码里会把我们生成的class 放在proxyClassCache 缓存起来 ，反射调取方法
    val proxySubject:Navigator = Proxy.newProxyInstance(nav.javaClass.classLoader,
    nav.javaClass.interfaces, MyProxyHandler(nav)) as Navigator

    proxySubject.getName()

}

class MyProxyHandler<T>(private val objectProxy: T) : InvocationHandler {
    /**
     * 动态代理
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable Throwable
     */
    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
        println("动态代理 start:我该做点什么？")
        val result = method.invoke(objectProxy, *args)
        println("动态代理 end:我该做点什么？")
        return result
    }
}

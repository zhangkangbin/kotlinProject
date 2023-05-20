package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * 这里是一次性返回。
 * 但如果我们需要的是，每次只返回一个。不是一次性呢。这个时候就需要用到Flow
 */
suspend fun simple(): List<Int> {
    delay(1000) // 假装我们在这里做了一些异步的事情
    return listOf(1, 2, 3)
}

fun simpleFlow(): Flow<Int> = flow { // 流构建器
    for (i in 1..3) {
        delay(1000) // 假装我们在这里做了一些有用的事情
        emit(i) // 发送下一个值
    }
}

suspend fun suspendCancellableCoroutineTest() {

    //等待返回结果,设计一个超时

    val data=withTimeoutOrNull(250) {

        delay(300)
        val call=suspendCancellableCoroutine {

            it.resume("kk")

        }

        return@withTimeoutOrNull call

    }

    if (data==null){

        println("data ==null")
    }else{

        println("data ==${data}")
    }


}

/**
 * 里面调用了suspendCoroutineUninterceptedOrReturn 来实现
 */
suspend fun withTimeoutOrNullTest() {

    withTimeoutOrNull(250) {
        println("----start----")

        delay(100)
        println("----1----")

        delay(200)
        //因为超时了，这里不会被输出
        println("----2----")


    }


}

suspend fun filterTest() {

    for (k in 1..3) {
        println("I'm not blocked $k")
        delay(1000)
    }

    // 接收，收集这个流
    simpleFlow().collect { value -> println(value) }

    simpleFlow().filter {
        it == 3
    }
}

fun main() = runBlocking<Unit> {
    //simple().forEach { value -> println(value) }
    // 启动并发的协程以验证主线程并未阻塞

   // withTimeoutOrNullTest()

    suspendCancellableCoroutineTest()

}
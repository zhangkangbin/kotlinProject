package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class CoroutineTest {
}

/*fun main()= runBlocking {

    println("--------start-----------")
    launch {
        delay(2000)
        println("--------GlobalScope-----------")

        withContext(Dispatchers.Main){
            //子线程处理文件
            println("--------withContext-----------")
        }
    }

    println("--------end-----------")
}*/

suspend fun main(): Unit = coroutineScope {
    //消息缓存数，缓存后面两条信息。
    val mutableSharedFlow = MutableSharedFlow<String>(
        replay = 2,
    )
    mutableSharedFlow.emit("Message1")
    mutableSharedFlow.emit("Message2")
    mutableSharedFlow.emit("Message3")

    println(mutableSharedFlow.replayCache)
    // [Message2, Message3]

    launch {
        mutableSharedFlow.collect {
            println("#1 received $it")
        }
        // #1 received Message2
        // #1 received Message3
    }

    delay(100)
    mutableSharedFlow.resetReplayCache()
    println(mutableSharedFlow.replayCache) // []

    suspendCancellableCoroutine {


    }
}
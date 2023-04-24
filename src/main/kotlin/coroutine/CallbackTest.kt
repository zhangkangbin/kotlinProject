package coroutine

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    testCoroutine()
}

suspend fun testCoroutine(){

    //包装CallBack
    val data=suspendCoroutine{
        setCallBack(object : CallBack {
            override fun onFail(code: Int) {

                it.resume(DataInfoBean(404))
            }

            override fun onSuccess(code: Int) {

                it.resume(DataInfoBean(code))
            }


        })

    }

    println("----------code----------------")
    println(data.code)
}


 fun setCallBack(callBack: CallBack){

    Thread.sleep(2000)
    callBack.onSuccess(200)

}



interface CallBack{

    fun onFail(code:Int)

    fun onSuccess(code:Int)
}
data class DataInfoBean(val code:Int)
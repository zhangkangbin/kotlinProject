package test


fun  main(){


    runCatching {


    }.onSuccess {

    }.onFailure {

    }

    onCall{it->
        println(it)
    }

    //function

    onCall2{it->
        it+"test"
    }
}

fun onCall(onString: (String)->Unit){

    onString("kk")

}

fun onCall2(onString: (String)->String){

    onString("kk")

}
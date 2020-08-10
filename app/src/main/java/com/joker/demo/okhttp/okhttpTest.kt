package com.joker.demo.okhttp

import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    var okHttpClient: OkHttpClient? = null
    var builder = OkHttpClient.Builder()

    okHttpClient = OkHttpClient.Builder().build()


    //请求对象
    var request = Request.Builder().url("").build()

    var response = okHttpClient.newCall(request).execute()
    testReturn()


}




fun testReturn():Int {
    var i=0
    while (true) {
        i++
        System.out.println(i)
        if(i==10){
            return i
        }
    }
}


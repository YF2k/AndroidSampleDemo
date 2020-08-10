package com.joker.myktokhttp

//提供给应用层API
class MyKthttpClient(builder:Builder) {
    var dispather:Dispather?=null

    var interceptors:List<Interceptor>?=null

    var retryTimes=3

    private var connectionPool: ConnectionPool? = null


    init{
        dispather = builder.dispather
        interceptors = builder.interceptors
        retryTimes = builder.retryTimes
        connectionPool = builder.connectionPool
    }

     fun getRetryTimes(): Int? {
        return retryTimes
    }

    fun getDispather1(): Dispather? {
        return dispather
    }

    fun getInterceptors1(): List<Interceptor?>? {
        return interceptors
    }

    fun getConnectionPool(): ConnectionPool? {
        return connectionPool
    }



    class Builder{
        var dispather:Dispather?=null

        var interceptors:List<Interceptor>?= emptyList()

        var retryTimes=3

        var connectionPool: ConnectionPool? = null
    }

    fun addInterceptors(interceptor: Interceptor){
    }

}
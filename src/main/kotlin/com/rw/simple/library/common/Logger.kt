package com.rw.simple.library.common

object Logger {
    fun log(msg: String) {
        println(msg)
    }

    fun logInline(msg: String) {
        print(msg)
    }

    fun logError(msg: String) {
        System.err.println(msg)
    }
}

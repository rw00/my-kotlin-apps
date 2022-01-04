package com.rw.simple.library.apps

import kotlin.math.pow

object BinaryNumbers {
    fun toInteger(binary: String): Int {
        var integer = 0
        val l = binary.length - 1
        for (i in binary.indices) {
            val bit = binary[i].digitToInt()
            integer += (bit * 2.0.pow(l - i)).toInt()
        }
        return integer
    }

    fun toBinary(integer: Int): String {
        if (integer == 0) {
            return "0"
        }
        val binary = StringBuilder()
        var intNumber = integer
        while (intNumber > 0) {
            binary.append(intNumber % 2)
            intNumber /= 2
        }
        return binary.reverse().toString()
    }
}

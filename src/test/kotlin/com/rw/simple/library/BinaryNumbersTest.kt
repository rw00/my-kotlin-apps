package com.rw.simple.library

import com.rw.simple.library.apps.BinaryNumbers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BinaryNumbersTest {
    @ParameterizedTest
    @MethodSource("binaryNumbersTestArgsProvider")
    fun testBinaryToInteger(inputBinaryString: String, expectedInteger: Int) {
        val result = BinaryNumbers.toInteger(inputBinaryString)
        Assertions.assertEquals(expectedInteger, result)
    }

    @ParameterizedTest
    @MethodSource("binaryNumbersTestArgsProvider")
    fun testIntegerToBinary(expectedBinaryString: String, inputInteger: Int) {
        val result = BinaryNumbers.toBinary(inputInteger)
        Assertions.assertEquals(expectedBinaryString, result)
    }

    companion object {
        @JvmStatic
        fun binaryNumbersTestArgsProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of("10", 2),
                Arguments.of("11", 3),
                Arguments.of("100", 4),
                Arguments.of("10110", 22),
                Arguments.of("101010", 42),
                Arguments.of("111111111", 511)
            )
    }
}

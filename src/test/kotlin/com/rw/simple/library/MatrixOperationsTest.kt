package com.rw.simple.library

import com.rw.simple.library.apps.MatrixOperations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MatrixOperationsTest {
    @Test
    fun `matrices addition`() {
        val a: Array<IntArray> = arrayOf(intArrayOf(1, 2, 3))
        val b: Array<IntArray> = arrayOf(intArrayOf(9, 8, 7))
        val result = MatrixOperations.add(a, b)
        Assertions.assertTrue(arrayOf(intArrayOf(10, 10, 10)).contentDeepEquals(result))
    }

    @Test
    fun `matrices multiplication`() {
        val a: Array<IntArray> = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
        val b: Array<IntArray> = arrayOf(intArrayOf(5, 6, 7), intArrayOf(8, 9, 10))
        val result = MatrixOperations.multiply(a, b)
        Assertions.assertTrue(
            arrayOf(
                intArrayOf(21, 24, 27),
                intArrayOf(47, 54, 61)
            ).contentDeepEquals(result)
        )
    }

    @Test
    fun `matrix transposing`() {
        val m: Array<IntArray> =
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(5, 5, 5), intArrayOf(9, 8, 7))
        val result = MatrixOperations.transpose(m)
        Assertions.assertTrue(
            arrayOf(
                intArrayOf(1, 5, 9),
                intArrayOf(2, 5, 8),
                intArrayOf(3, 5, 7)
            ).contentDeepEquals(result)
        )
    }

    @Test
    fun `matrix multiplication by constant`() {
        val m: Array<IntArray> =
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(5, 5, 5), intArrayOf(9, 8, 7))
        MatrixOperations.multiplyByConstant(m, 2)
        Assertions.assertTrue(
            arrayOf(
                intArrayOf(2, 4, 6),
                intArrayOf(10, 10, 10),
                intArrayOf(18, 16, 14)
            ).contentDeepEquals(m)
        )
    }

    @Test
    fun `matrix determinant`() {
        val m: Array<IntArray> =
            arrayOf(intArrayOf(1, 5, 9), intArrayOf(8, 5, 2), intArrayOf(10, 5, 10))
        val result = MatrixOperations.determinant(m)
        Assertions.assertEquals(-350, result)
    }

    @Test
    fun `matrix trace`() {
        val m: Array<IntArray> =
            arrayOf(intArrayOf(21, 42, 100), intArrayOf(10, 8, 100), intArrayOf(9, -1, 9))
        val result = MatrixOperations.trace(m)
        Assertions.assertEquals(38, result)
    }
}

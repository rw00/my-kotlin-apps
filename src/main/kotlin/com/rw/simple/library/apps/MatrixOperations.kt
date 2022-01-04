package com.rw.simple.library.apps

import com.rw.simple.library.common.Logger
import kotlin.math.pow

object MatrixOperations {

    fun add(matrixA: Array<IntArray>?, matrixB: Array<IntArray>?): Array<IntArray?> {
        if (matrixA == null || matrixB == null) {
            throw IllegalArgumentException("null matrix")
        }
        if (!haveEqualSize(matrixA, matrixB)) {
            throw IllegalArgumentException("different matrices sizes")
        }
        val n = matrixA.size
        val result = arrayOfNulls<IntArray>(n) // this is the reason I hate Kotlin
        for (row in 0 until n) {
            result[row] = IntArray(matrixA[row].size)
            for (col in matrixA[row].indices) {
                result[row]!![col] = matrixA[row][col] + matrixB[row][col]
            }
        }
        return result
    }

    fun multiply(matrixA: Array<IntArray>?, matrixB: Array<IntArray>?): Array<IntArray> {
        if (matrixA == null || matrixB == null) {
            throw IllegalArgumentException("null matrix")
        }
        if (matrixA[0].size != matrixB.size || !isRectangular(matrixA) || !isRectangular(matrixB)) {
            throw IllegalArgumentException("matrices not rectangular")
        }
        val sizeA = matrixA.size
        val sizeB = matrixB[0].size
        val result = Array(sizeA) { IntArray(sizeB) }
        for (row in 0 until sizeA) {
            for (col in 0 until sizeB) {
                result[row][col] = dotProduct(matrixA[row], columnArrayCopy(matrixB, col))
            }
        }
        return result
    }

    fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        if (!isRectangular(matrix)) {
            throw IllegalArgumentException("matrix not rectangular")
        }
        val cols = matrix[0].size
        val rows = matrix.size
        val transposed = Array(cols) { IntArray(rows) }
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                transposed[col][row] = matrix[row][col]
            }
        }
        return transposed
    }

    fun multiplyByConstant(matrix: Array<IntArray>, k: Int) {
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                matrix[row][col] = (k * matrix[row][col])
            }
        }
    }

    fun determinant(matrix: Array<IntArray>): Int {
        if (!isSquare(matrix)) {
            throw IllegalArgumentException("matrix not square")
        }
        val n = matrix.size
        var determinant = 0
        if (n == 1) {
            determinant = matrix[0][0]
        } else if (n == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]
        } else { // for sign and first row elements
            for (c in 0 until n) {
                val m = Array(n - 1) { IntArray(n - 1) } // make new smaller matrix
                for (i in 0 until n - 1) {
                    var k = 0
                    for (j in 0 until n) {
                        if (j == c) {
                            continue
                        }
                        m[i][k] = matrix[i + 1][j]
                        k++
                    }
                }
                determinant += ((-1.0).pow(c.toDouble()) * matrix[0][c] * determinant(m)).toInt()
            }
        }
        return determinant
    }

    fun trace(matrix: Array<IntArray>): Int {
        if (!isSquare(matrix)) {
            throw IllegalArgumentException("matrix not square")
        }
        var trace = 0
        val n = matrix.size
        for (i in 0 until n) {
            trace += matrix[i][i]
        }
        return trace
    }

    fun isSquare(matrix: Array<IntArray>): Boolean {
        return matrix.isEmpty() || ((matrix.size == matrix[0].size) && isRectangular(matrix))
    }

    fun isRectangular(matrix: Array<IntArray>): Boolean {
        for (row in matrix.indices) {
            if (matrix[0].size != matrix[row].size) {
                return false
            }
        }
        return true
    }

    fun isTriangular(matrix: Array<IntArray>): Boolean {
        return isSquare(matrix) &&
                (isDiagonal(matrix) || isUpperTriangular(matrix) || isLowerTriangular(matrix))
    }

    fun isLowerTriangular(matrix: Array<IntArray>): Boolean {
        val n = matrix.size
        for (row in 0 until n) {
            for (col in row..n) {
                if (matrix[row][col] != 0) {
                    return false
                }
            }
        }
        return true
    }

    fun isUpperTriangular(matrix: Array<IntArray>): Boolean {
        val n = matrix.size
        for (row in 0 until n) {
            for (col in 0..row) {
                if (matrix[row][col] != 0) {
                    return false
                }
            }
        }
        return true
    }

    fun isDiagonal(matrix: Array<IntArray>): Boolean {
        return isUpperTriangular(matrix) && isLowerTriangular(matrix)
    }

    fun isJagged(matrix: Array<IntArray>): Boolean {
        for (i in matrix.indices) {
            if (matrix[0].size != matrix[i].size) {
                return true
            }
        }
        return false
    }

    fun dotProduct(arrayA: IntArray, arrayB: IntArray): Int {
        assert(arrayA.size == arrayB.size) { "different array lengths" }
        var dotProduct = 0
        for (index in arrayA.indices) {
            dotProduct += (arrayA[index] * arrayB[index])
        }
        return dotProduct
    }

    /**
     * Checks that the matrices (could be jagged) have equal row and column sizes
     */
    fun haveEqualSize(matrixA: Array<IntArray>, matrixB: Array<IntArray>): Boolean {
        val n = matrixA.size
        if (n != matrixB.size) {
            return false
        }
        for (i in 0 until n) {
            if (matrixA[i].size != matrixB[i].size) {
                return false
            }
        }
        return true
    }

    fun columnArrayCopy(matrix: Array<IntArray>, col: Int): IntArray {
        if (col >= matrix[0].size || col < 0) {
            throw IndexOutOfBoundsException("column index out of bounds")
        }
        val rows = matrix.size
        val columnArray = IntArray(rows)
        for (row in 0 until rows) {
            columnArray[row] = matrix[row][col]
        }
        return columnArray
    }

    fun printMatrix(matrix: Array<IntArray>) {
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                Logger.log(String.format("%4d ", matrix[row][col]))
            }
            println()
        }
        println()
    }
}

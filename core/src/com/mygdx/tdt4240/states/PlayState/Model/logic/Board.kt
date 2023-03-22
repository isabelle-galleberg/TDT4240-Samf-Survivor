package com.mygdx.tdt4240.states.PlayState.Model.logic
class Board  {
    private val row = 10
    private val column = 10

    val grid = Array(row) { IntArray(column) }

    fun board(arr: Array<IntArray>) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (i == 0 || j == 0 || i == arr.size -1 || j == arr[i].size -1) {
                    arr[i][j] = 1

                }

                print("${arr[i][j]} ")
            }
            println()
        }
    }

}
fun main() {
    val b = Board()
    val g = b.grid
    print(b.board(g))

}


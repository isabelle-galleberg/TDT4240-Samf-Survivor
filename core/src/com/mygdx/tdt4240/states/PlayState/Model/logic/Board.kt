package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world


class Board  {
    private val row = 10
    private val column = 10

    val grid = Array(10) { arrayOfNulls<Entity>(10) }



    fun initBoard(arr: Array<Array<Entity?>>) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                print("${arr[i][j]} ")
            }
            println()
        }
    }

    fun drawBoard(arr: Array<Array<Entity?>>, world: World) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                //Walls
                if (i == 0 || j == 0 || i == arr.size -1 || j == arr[i].size -1) {
                    arr[i][j] = WallFactory.createWall(world, Pair(i,j))
                }
                //Crates
                else
                     {
                    arr[i][j] = CrateFactory.createCrate(world, Pair(i,j))
                }
            }
        }
        //Players
        arr[1][1] = PlayerFactory.createPlayer(world, Pair(1,1))
        arr[8][8] = PlayerFactory.createPlayer(world, Pair(8,8))

        initBoard(arr)

    }

}
fun main() {
    val b = Board()
    val g = b.grid
    val world = world {}
    print(b.drawBoard(g, world))

}


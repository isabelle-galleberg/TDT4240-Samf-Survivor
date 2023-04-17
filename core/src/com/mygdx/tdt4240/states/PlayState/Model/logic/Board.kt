package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world


class Board  {

    val grid = Array(9) { arrayOfNulls<Entity>(9) }

    private fun initBoard(arr: Array<Array<Entity?>>) {
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
                if (i == 0 || j == 0 || i == arr.size -1 || j == arr[i].size -1) {
                    arr[i][j] = WallFactory.createWall(world, i,j)  //Walls
                }
                else
                     {
                    arr[i][j] = CrateFactory.createCrate(world, i,j) //Crates
                }
            }
        }
        //Players
        arr[1][1] = PlayerFactory.createPlayer(world, 1, 1)
        arr[7][7] = PlayerFactory.createPlayer(world, 7,7)

        initBoard(arr)
    }

}
fun main() {
    val b = Board()
    val g = b.grid
    val world = world {}

    print(b.drawBoard(g, world))
}


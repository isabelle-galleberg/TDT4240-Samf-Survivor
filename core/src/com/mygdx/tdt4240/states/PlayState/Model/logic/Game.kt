package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.sprites.Player
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.utils.Constants
import java.util.*
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH


/* Game logic */
class Game (val world: World){

    private val entityFactory = EntityFactory

    private var timer = false;

    private var bombCount = 0;

    private var firePressed = false;

    val board = Array(9) { arrayOfNulls<Entity>(9) }
    val player = PlayerFactory.createPlayer(world, (GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.45f).toInt(),(GAME_HEIGHT * 0.85f).toInt())
    val npc = NPCFactory.createNPC(world, 0, 0)


    fun initBoard(): Array<Array<Entity?>> {
        //Player
        board[0][8] = player
        //NPC
        board[0][0] = npc

        for (i in board.indices) {
            for (j in board[i].indices) {
                //Walls
                if (i % 2 != 0 && j % 2 != 0){
                    board[i][j] = WallFactory.createWall(world, i, j)
                }
                //Crates
                //TO DO where crates??
                else if (i == 3) {
                    board[i][j] = CrateFactory.createCrate(world, i, j)
                }
            }
        }
        return board
    }

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
                    arr[i][j] = WallFactory.createWall(world, i,j)
                }
                //Crates
                else {
                    arr[i][j] = CrateFactory.createCrate(world, i,j)
                }
            }
        }
    }

    fun drawPlayer(arr: Array<Array<Entity?>>,world: World, x: Int, y: Int) {
        arr[x][y] = PlayerFactory.createPlayer(world, x, y)
    }

    fun initGame() {
        initBoard();
        bombCount = 0;
        timer = true;

    }

    private fun getPlayer(arr: Array<Array<Entity?>>, component: String): Int {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (arr[i][j]?.has(ScoreComponent) == true) { //Player
                    if (component == "x") {
                        return i
                    } else if (component == "y") {
                        return j
                    }
                }
            }
        }
        return 0
    }

    fun movePlayer(arr: Array<Array<Entity?>>, direction: String) {
        var currentPosX = getPlayer(arr, "x")
        var currentPosY = getPlayer(arr, "y")

        if (direction == "DOWN") {
            if (currentPosY-1 < 0) {
                return
            }
            arr[currentPosX][currentPosY] = null
            arr[currentPosX][currentPosY-1] = player

        } else if (direction == "UP") {
            if (currentPosY+1 > 8) {
                return
            }
            arr[currentPosX][currentPosY] = null
            arr[currentPosX][currentPosY+1] = player

        } else if (direction == "RIGHT") {
            if (currentPosX+1 > 8) {
                return
            }
            arr[currentPosX][currentPosY] = null
            arr[currentPosX+1][currentPosY] = player

        } else if (direction == "LEFT") {

            if (currentPosX-1 < 0) {
                return
            }
            arr[currentPosX][currentPosY] = null
            arr[currentPosX-1][currentPosY] = player
        }
    }

    fun getBombs(): Int {
        if(PlayerSystem.getPosition() == BombSystem.getPosition()) {
            bombCount += 1;
        }
        return bombCount;
    }

    fun placeBombs(world: World,x:Int, y:Int) {
        if(firePressed && bombCount > 0 && !ObstacleSystem.getPositions().contains(Pair(x, y))) {
            BombSystem.dropBomb(world,x,y);
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    FireFactory.createFire(world, x+3, y+3) //fix range
                }
            }, 2000)
        }
    }

    fun randomSpawn() {
        var randomTypes = PowerupType.values().toList().shuffled()
        var powerupPositions: MutableList<Pair<Int,Int>> = mutableListOf()

      //fix

    }
}
fun main() {
    val world = world {}
    val b = Game(world)
    val g = b.board
    b.initGame()

    //b.drawBoard(g, world)
    //b.drawPlayer(g,world,1,1)

    //print(b.initBoard(g));
}
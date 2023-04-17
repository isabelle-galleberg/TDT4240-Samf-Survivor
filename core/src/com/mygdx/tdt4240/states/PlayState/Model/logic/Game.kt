package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.badlogic.gdx.graphics.g2d.Sprite
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.sprites.Player
import com.mygdx.tdt4240.states.PlayState.Controller.PlayController
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.ObstacleSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PlayerSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PowerUpSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
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
    val bomb = BombFactory.createBomb(world,0,0);

    fun init() {
        initBoard();
        bombCount = 0;
    }

    private fun initBoard(): Array<Array<Entity?>> {
        //Player
        board[0][8] = player
        //NPC
        board[8][0] = npc

        for (i in board.indices) {
            for (j in board[i].indices) {
                //Walls
                if (i % 2 != 0 && j % 2 != 0){
                    board[i][j] = WallFactory.createWall(world, i, j)
                } else if (i == 3) {
                    board[i][j] = CrateFactory.createCrate(world, i, j)
                }
            }
        }
        return board
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
/*
    fun drawPlayer(arr: Array<Array<Entity?>>,world: World, x: Int, y: Int) {
        arr[x][y] = PlayerFactory.createPlayer(world, x, y)
    }
 */
    fun getPlayerCoordinate(arr: Array<Array<Entity?>>, component: String): Int {
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

    private fun getNPCCoordinate(arr: Array<Array<Entity?>>, component: String): Int {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (arr[i][j]?.has(CharacterComponent) == true) {
                    if (arr[i][j]?.has(ScoreComponent) == false) { //NPC
                        if (component == "x") {
                            return i
                        } else if (component == "y") {
                            return j
                        }
                    }
                }
            }
        }
        return 0
    }

    fun moveNPC(arr: Array<Array<Entity?>>, direction: String) {
        var currentPosX = getNPCCoordinate(arr, "x")
        var currentPosY = getNPCCoordinate(arr, "y")

        moveObject(arr, direction, npc, currentPosX, currentPosY)
    }

    fun movePlayer(arr: Array<Array<Entity?>>, direction: String) {
        var currentPosX = getPlayerCoordinate(arr, "x")
        var currentPosY = getPlayerCoordinate(arr, "y")

        moveObject(arr, direction, player, currentPosX, currentPosY)
    }

    private fun moveObject(arr: Array<Array<Entity?>>, direction: String, character: Entity?, x: Int, y: Int) {
        if (direction == "DOWN") {
            if (y-1 < 0 || arr[x][y-1]?.has(ObstacleComponent) == true) {
                return
            }
            arr[x][y] = null
            arr[x][y-1] = character

        } else if (direction == "UP") {
            if (y+1 > 8 || arr[x][y+1]?.has(ObstacleComponent) == true) {
                return

            }
            arr[x][y] = null
            arr[x][y+1] = character

        } else if (direction == "RIGHT") {
            if (x+1 > 8 || arr[x+1][y]?.has(ObstacleComponent) == true) {
                return
            }
            arr[x][y] = null
            arr[x+1][y] = character

        } else if (direction == "LEFT") {

            if (x-1 < 0 || arr[x-1][y]?.has(ObstacleComponent) == true) {
                return
            }
            arr[x][y] = null
            arr[x-1][y] = character
        }
    }


    fun placeBombs(arr: Array<Array<Entity?>>, x:Int, y: Int) {
        if( !ObstacleSystem.getPositions().contains(Pair(x,y))) {
            println("yoyo")
            arr[x][y] = bomb
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    arr[x][y] = null
                }
            }, (2000))
        }
    }

    fun powerUp(arr: Array<Array<Entity?>>, x:Int, y: Int) {
        var currentPosX = getPlayerCoordinate(arr, "x")
        var currentPosY = getPlayerCoordinate(arr, "y")

        var randomTypes = PowerupType.values().toList().shuffled()
        var powerupPositions: MutableList<Pair<Int,Int>> = mutableListOf()

        if(currentPosX == PowerUpSystem.getPosition().first && currentPosY == PowerUpSystem.getPosition().second) {

        }
      //fix

    }
}

fun main() {
    val world = world {}
    val b = Game(world)
    val g = b.board
    b.init()

    //b.drawBoard(g, world)
    //b.drawPlayer(g,world,1,1)

    //print(b.initBoard(g));
}
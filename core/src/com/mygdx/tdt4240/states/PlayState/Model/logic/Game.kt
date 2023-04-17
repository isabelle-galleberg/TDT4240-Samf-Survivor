package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.PlayerComponent
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
    private val player = PlayerFactory.createPlayer(world, (GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.45f).toInt(),(GAME_HEIGHT * 0.85f).toInt())
    private val npc = NPCFactory.createNPC(world, 0, 0)
    private val bomb = BombFactory.createBomb(world,0,0);

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
/*
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
 */
    fun getPlayerCoordinate(arr: Array<Array<Entity?>>, component: String): Int {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (arr[i][j]?.has(PlayerComponent) == true) { //Player
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
                    if (arr[i][j]?.has(PlayerComponent) == false) { //NPC
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


    fun placeBomb() {
        var x = PlayerSystem.getPosition().first
        var y = PlayerSystem.getPosition().second
        board[x][y] = EntityFactory.createBomb(world,x,y)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                board[x][y] = null
                fire(x,y)
            } }, (2000))
        }

    fun fire(x: Int, y:Int) {
        var fireLength = PlayerSystem.getFireLength()
        var fireCoordinates = arrayOfNulls<Pair<Int,Int>>(fireLength*4)
        board[x][y] = EntityFactory.createFire(world,x,y)
        var stop = false
        //fix fire when crate is threr
        while(!stop) {
            for (i in 1 until PlayerSystem.getFireLength()) {
                if (ObstacleSystem.getPositions().contains(Pair(x+i,y))) {
                    break
                }
                board[x+i][y] = EntityFactory.createFire(world,x+i,y)
            }
        }
        for (i in 1 until PlayerSystem.getFireLength()) {
            if (!ObstacleSystem.getPositions().contains(Pair(x+i,y))) {
                board[x+i][y] = EntityFactory.createFire(world,x+i,y)
            }
            if (!ObstacleSystem.getPositions().contains(Pair(x,y+i))) {
                board[x][y+i] = EntityFactory.createFire(world,x+i,y+i)
            }

        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                board[x][y] = null
            } }, (2000))

    }

    fun powerUp(arr: Array<Array<Entity?>>, x:Int, y: Int) {
        //var currentPosX = getPlayerCoordinate(arr, "x")
        //var currentPosY = getPlayerCoordinate(arr, "y")

        var randomTypes = PowerupType.values().toList().shuffled()
        var powerupPositions: MutableList<Pair<Int,Int>> = mutableListOf()

        //if(currentPosX == PowerUpSystem.getPosition().first && currentPosY == PowerUpSystem.getPosition().second) {

        //}
      //fix

    }
}

fun main() {
    val world = world {}
    val b = Game(world)
    b.init()
}
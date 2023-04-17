package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.sprites.Player
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.PlayerComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import java.util.*
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH


/* Game logic */
class Game (val world: World){

    val board = Array(9) { arrayOfNulls<Entity>(9) }
    private val player = PlayerFactory.createPlayer(world, (GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.45f).toInt(),(GAME_HEIGHT * 0.85f).toInt())
    private val npc = NPCFactory.createNPC(world, 0, 0)

    fun init() {
        initBoard();
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

    fun movePlayer() {
        var x = PlayerSystem.getPosition().first
        var y = PlayerSystem.getPosition().second
        var direction = PlayerSystem.getDirection()
        var obstacles = ObstacleSystem.getPositions()
        if (PlayerSystem.getDirection() == DirectionType.DOWN) {
            if (y-1 < 0 || obstacles.contains(Pair(x,y-1))) {
                return
            }
            board[x][y] = null
            board[x][y-1] = player

        } else if (direction == DirectionType.UP) {
            if (y+1 > 8 || obstacles.contains(Pair(x,y+1))) {
                return

            }
            board[x][y] = null
            board[x][y+1] = player

        } else if (direction == DirectionType.RIGHT) {
            if (x+1 > 8 || obstacles.contains(Pair(x+1,y))) {
                return
            }
            board[x][y] = null
            board[x+1][y] = player

        } else if (direction == DirectionType.LEFT) {

            if (x-1 < 0 || obstacles.contains(Pair(x-1,y))) {
                return
            }
            board[x][y] = null
            board[x-1][y] = player
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
        var fireCoordinates = mutableListOf<Pair<Int,Int>>()
        fireCoordinates.add(Pair(x,y))
        var stop = false

        //Fire right
        for (i in 1 until PlayerSystem.getFireLength()) {
            if (ObstacleSystem.getPositions().contains(Pair(x+i,y))) {
                if (board[x+i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x+1,y))
                }
                break
            }
            fireCoordinates.add(Pair(x+1,y))
        }
        //Fire left
        for (i in 1 until PlayerSystem.getFireLength()) {
            if (ObstacleSystem.getPositions().contains(Pair(x-i,y))) {
                if (board[x-i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x-1,y))
                }
                break
            }
            fireCoordinates.add(Pair(x+1,y))
        }
        //Fire down
        for (i in 1 until PlayerSystem.getFireLength()) {
            if (ObstacleSystem.getPositions().contains(Pair(x,y+i))) {
                if (board[x][y+i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y+i))
                }
                break
            }
            fireCoordinates.add(Pair(x+1,y))
        }

        //Fire up
        for (i in 1 until PlayerSystem.getFireLength()) {
            if (ObstacleSystem.getPositions().contains(Pair(x,y-i))) {
                if (board[x][y-i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y-i))
                }
                break
            }
            fireCoordinates.add(Pair(x+1,y))
        }

        for (cor in fireCoordinates) {
            board[cor.first][cor.second] = EntityFactory.createFire(world,cor.first,cor.second)
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                for (cor in fireCoordinates) {
                    board[cor.first][cor.second] = null
                }
            } }, (1000))

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
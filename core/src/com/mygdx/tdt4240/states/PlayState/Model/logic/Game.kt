package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import java.util.*
import kotlin.random.Random


/* Game logic */
class Game (val world: World){

    val board = Array(9) { arrayOfNulls<Entity>(9) }
    private val player = PlayerFactory.createPlayer(world, (GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.45f).toInt(),(GAME_HEIGHT * 0.85f).toInt())
    private val npc = NPCFactory.createNPC(world, 0, 0)

    private var game = false

    private var movePlayer = 0;

    fun init() {
        initBoard()
        game = true
    }

    private fun initBoard(): Array<Array<Entity?>> {
        PlayerSystem.setPosition(Pair(0,8)) //Player
        NPCSystem.setPosition(Pair(8,0)) //NPC

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i % 2 != 0 && j % 2 != 0) {
                    board[i][j] = WallFactory.createWall(world, i, j) //Wall
                } else if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7) {
                    board[i][j] = CrateFactory.createCrate(world, i, j) //Crate
                }
            }
        }
        return board
    }

    fun movePlayer() {
        if(movePlayer < PlayerSystem.getSpeed()) {
            movePlayer++
            return
        }
        val x = PlayerSystem.getPosition().first
        val y = PlayerSystem.getPosition().second
        val direction = PlayerSystem.getDirection()

        if (PlayerSystem.getDirection() == DirectionType.DOWN) {
            if (y-1 < 0 || board[x][y-1]?.has(ObstacleComponent) == true) {
                return
            } else if(board[x][y-1]?.has(BoostComponent) == true)
            {
                booster(board[x][y-1]?.get(BoostComponent)?.powerupType);
                board[x][y-1] = null
            }
            PlayerSystem.setPosition(Pair(x,y-1))

        } else if (direction == DirectionType.UP) {
            if (y+1 > 8 || board[x][y+1]?.has(ObstacleComponent) == true) {
                return
            }else if(board[x][y+1]?.has(BoostComponent) == true)
            {
                booster(board[x][y+1]?.get(BoostComponent)?.powerupType);
                board[x][y+1] = null
            }
            PlayerSystem.setPosition(Pair(x, y+1))

        } else if (direction == DirectionType.RIGHT) {
            if (x+1 > 8 || board[x+1][y]?.has(ObstacleComponent) == true) {
                return
            }else if(board[x+1][y]?.has(BoostComponent) == true)
            {
                booster(board[x+1][y]?.get(BoostComponent)?.powerupType);
                board[x+1][y] = null
            }
            PlayerSystem.setPosition(Pair(x+1,y))

        } else if (direction == DirectionType.LEFT) {
            if (x-1 < 0 || board[x-1][y]?.has(ObstacleComponent) == true) {
                return
            }else if(board[x-1][y]?.has(BoostComponent) == true)
            {
                booster(board[x-1][y]?.get(BoostComponent)?.powerupType);
                board[x-1][y] = null
            }
            PlayerSystem.setPosition(Pair(x-1,y))
            movePlayer = 0;
        }
    }

    fun placeBomb() {
        val x = PlayerSystem.getPosition().first
        val y = PlayerSystem.getPosition().second
        board[x][y] = EntityFactory.createBomb(world,x,y)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                board[x][y] = null
                fire(x,y)
            } }, (2000))
    }

    fun fire(x: Int, y:Int) {
        val fireLength = PlayerSystem.getFireLength()
        val fireCoordinates = mutableListOf<Pair<Int,Int>>()
        fireCoordinates.add(Pair(x,y))

        //Fire right
        for (i in 1 until fireLength) {
            if (x+i > 8) {
                break
            }
            if (board[x+i][y]?.has(ObstacleComponent) == true) {
                if (board[x+i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x+i,y))
                }
                break
            }
            fireCoordinates.add(Pair(x+i,y))
        }
        //Fire left
        for (i in 1 until fireLength) {
            if (x-i < 0) {
                break
            }
            if (board[x-i][y]?.has(ObstacleComponent) == true) {
                if (board[x-i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x-i,y))

                }
                break
            }
            fireCoordinates.add(Pair(x-i,y))
        }
        //Fire down
        for (i in 1 until fireLength) {
            if (y-i < 0) {
                break
            }
            if (board[x][y-i]?.has(ObstacleComponent) == true) {
                if (board[x][y-i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y-i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y-i))
        }
        //Fire up
        for (i in 1 until fireLength) {
            if (y+i > 8) {
                break
            }
            if (board[x][y+i]?.has(ObstacleComponent) == true) {
                if (board[x][y+i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y+i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y+i))
        }

        for (cor in fireCoordinates) {
            board[cor.first][cor.second] = EntityFactory.createFire(world,cor.first,cor.second, 2)
            if (cor.first == PlayerSystem.getPosition().first && cor.second == PlayerSystem.getPosition().second) {
                PlayerSystem.reduceLives()
            } else if(cor.first == NPCSystem.getPosition().first && cor.second == NPCSystem.getPosition().second) {
                NPCSystem.reduceLives()
            }
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                for (cor in fireCoordinates) {
                    board[cor.first][cor.second] = null
                }
            } }, (1000))
    }
    fun powerUp() {
        var x = Random.nextInt(0, 8)
        var y = Random.nextInt(0, 8)
        while (board[x][y]?.has(ObstacleComponent) == true || board[x][y]?.has(LifetimeComponent) == true) {
            x = Random.nextInt(0, 8)
            y = Random.nextInt(0, 8)
        }

        val randomTypes = PowerupType.values().toList().shuffled()
        board[x][y] = EntityFactory.createPowerup(world, x, y, randomTypes.first())
        }
    }

fun booster(powerUp: PowerupType?) {

    if (powerUp == PowerupType.POINTS) {
        PlayerSystem.addScore(PowerupType.POINTS.value);
    }

    else if (powerUp == PowerupType.RANGE) {
        PlayerSystem.setFireLength(PowerupType.RANGE.value)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                PlayerSystem.setFireLength(3)
            }
        }, 7000)
    }

    else if ( powerUp == PowerupType.SPEED) {
        PlayerSystem.setSpeed(PowerupType.SPEED.value)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                PlayerSystem.setSpeed(5)
            }
        }, 5000)
    }
}




fun main() {
    val world = world {}
    val b = Game(world)
    b.init()
}


package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior.NPCBehavior
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
    private var NPCmove = 0

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
        val x = PlayerSystem.getPosition().first
        val y = PlayerSystem.getPosition().second
        val direction = PlayerSystem.getDirection()
        if (direction == DirectionType.DOWN) {
            if (y-1 < 0 || board[x][y-1]?.has(ObstacleComponent) == true) {
                return
            }
            PlayerSystem.setPosition(Pair(x,y-1))

        } else if (direction == DirectionType.UP) {
            if (y+1 > 8 || board[x][y+1]?.has(ObstacleComponent) == true) {
                return
            }
            PlayerSystem.setPosition(Pair(x, y+1))

        } else if (direction == DirectionType.RIGHT) {
            if (x+1 > 8 || board[x+1][y]?.has(ObstacleComponent) == true) {
                return
            }
            PlayerSystem.setPosition(Pair(x+1,y))

        } else if (direction == DirectionType.LEFT) {
            if (x-1 < 0 || board[x-1][y]?.has(ObstacleComponent) == true) {
                return
            }
            PlayerSystem.setPosition(Pair(x-1,y))
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
            if (x+1 == 9) {
                break
            }
            if (board[x+1][y]?.has(ObstacleComponent) == true) {
                if (board[x+i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x+1,y))
                }
                break
            }
            fireCoordinates.add(Pair(x+1,y))
        }
        //Fire left
        for (i in 1 until fireLength) {
            if (x-1 < 0) {
                break
            }
            if (board[x-1][y]?.has(ObstacleComponent) == true) {
                if (board[x-i][y]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x-1,y))

                }
                break
            }
            fireCoordinates.add(Pair(x-1,y))
        }
        //Fire down
        for (i in 1 until fireLength) {
            if (y+1 == 9) {
                break
            }
            if (board[x][y+1]?.has(ObstacleComponent) == true) {
                if (board[x][y+i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y+i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y+1))
        }
        //Fire up
        for (i in 1 until fireLength) {
            if (y-1 < 0) {
                break
            }
            if (board[x][y-1]?.has(ObstacleComponent) == true) {
                if (board[x][y-i]?.get(ObstacleComponent)?.wall == false) {
                    fireCoordinates.add(Pair(x,y-i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y-1))
        }

        for (cor in fireCoordinates) {
            board[cor.first][cor.second] = EntityFactory.createFire(world,cor.first,cor.second, 2)
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                for (cor in fireCoordinates) {
                    if (cor.first == PlayerSystem.getPosition().first && cor.second == PlayerSystem.getPosition().second) {
                        PlayerSystem.reduceLives()
                    } else if(cor.first == NPCSystem.getPosition().first && cor.second == NPCSystem.getPosition().second) {
                        NPCSystem.reduceLives()
                    }
                    board[cor.first][cor.second] = null
                }
            } }, (1000))
    }

    fun powerUp() {
        while(game) {
            val x = Random.nextInt(0,9)
            val y = Random.nextInt(0,9)

            val randomTypes = PowerupType.values().toList().shuffled()

            if(board[x][y]?.has(ObstacleComponent) == false) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        EntityFactory.createPowerup(world, x, y, randomTypes.first())
                    }
                }, Random.nextLong(2000,10000))
            }
        }
    }

    fun moveNPC() {
        if (NPCmove < NPCSystem.getSpeed()) {
            NPCmove += 1
            return
        }
        NPCBehavior.setBombs(world,npc,board, this)
        val x = NPCSystem.getPosition(npc).first
        val y = NPCSystem.getPosition(npc).second
        val direction = NPCSystem.getDirection(npc)
        if (direction == DirectionType.DOWN) {
            NPCSystem.setPosition(Pair(x,y-1))
        } else if (direction == DirectionType.UP) {
            NPCSystem.setPosition(Pair(x, y+1))
        } else if (direction == DirectionType.RIGHT) {
            NPCSystem.setPosition(Pair(x+1,y))

        } else if (direction == DirectionType.LEFT) {
            NPCSystem.setPosition(Pair(x-1,y))
        }
        NPCmove = 0

    }
}

fun main() {
    val world = world {}
    val b = Game(world)
    b.init()
}


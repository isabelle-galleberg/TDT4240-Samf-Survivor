package com.mygdx.tdt4240.states.PlayState.Controller

import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.EntityFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import java.util.*
import kotlin.random.Random

class PlayController {
    private val world = world {
        systems {
            add(BombSystem)
            add(NPCSystem)
            add(ObstacleSystem)
            add(PlayerSystem)
            add(PowerUpSystem)
        }
    }

    private var uiBoard = Array(9) { arrayOfNulls<String>(9) }
    private var game = Game(world)
    private var gameWon = false
    private var timerOver = false
    private var worldTimer: Int? = 300
    private var timeCount = 0f
    val timerTasks = mutableListOf<TimerTask>()

    init {
        game.init()

    }

    fun updateTime(dt: Float) {
        timeCount += dt
        if (timeCount >= 1) {
            if (worldTimer!! > 0) {
                worldTimer = worldTimer!! - 1
            } else {
                timerOver = true
            }
            timeCount = 0F
        }
    }

    fun getTime(): Int? {
        return worldTimer
    }

    fun drawBoard(): Array<Array<String?>> {
        uiBoard = Array(9){ arrayOfNulls(9) }
        for (i in 0 until 9) {
            for (j in 0 until 9) {

                if (game.board[i][j]?.has(ObstacleComponent) == true) {
                    if (game.board[i][j]?.get(ObstacleComponent)?.wall == true) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                } else if (game.board[i][j]?.has(ObservableComponent) == true) {
                    uiBoard[i][j] = "bomb"

                } else if (game.board[i][j]?.has(LifetimeComponent) == true) {
                    if (game.board[i][j]?.get(LifetimeComponent)?.fire == true) {
                        uiBoard[i][j] = "fire"
                    }

                }else if (game.board[i][j]?.has(BoostComponent) == true) {
                    if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.SPEED) {
                        uiBoard[i][j] = "speed"
                    }
                    else if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.RANGE) {
                        uiBoard[i][j] = "range"
                    }
                    else if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.POINTS) {
                        uiBoard[i][j] = "points"
                    }

                }
            }
        }
        return uiBoard
    }


    fun getPlayerPosition() : Pair<Int,Int> {
        return PlayerSystem.getPosition()
    }

    fun getNPCPosition() : Pair<Int,Int> {
        return NPCSystem.getPosition()
    }

    fun isGameWon(): Boolean {
        return this.gameWon
    }

    fun updatePos(direction: String) {
        if (direction == "RIGHT") {
            PlayerSystem.setDirection(DirectionType.RIGHT)
            game.movePlayer()
        }
        if (direction == "LEFT") {
            PlayerSystem.setDirection(DirectionType.LEFT)
            game.movePlayer()
        }
        if (direction == "UP") {
            PlayerSystem.setDirection(DirectionType.UP)
            game.movePlayer()
        }
        if (direction == "DOWN") {
            PlayerSystem.setDirection(DirectionType.DOWN)
            game.movePlayer()
        } else {
            PlayerSystem.setDirection(DirectionType.NONE)
        }
    }

    fun updatePosNPC() {
        game.moveNPC()
    }

    fun bomb() {
        game.placeBomb()
    }
    fun spawnPowerUp() {
        var randInt = Random.nextInt(0,500)
        if(randInt < 2) {
            game.powerUp()
        }
    }

    fun isGameOver(): Boolean {
        if (NPCSystem.getLives() == 0) {
            gameWon = true
            return true
        } else if (PlayerSystem.getLives() == 0) {
            gameWon = false
            return true
        } else if (timerOver) {
            return true
        }
        return false
    }

    fun score(): Int {
        if(!gameWon) {
            return 0;
        }
        var score = PlayerSystem.getScore()
        score += PlayerSystem.getLives() * 250 * (getTime()?.toInt() ?: 1)

        return score



    }
}

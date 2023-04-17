package com.mygdx.tdt4240.states.PlayState.Controller

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*

import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.FireFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.PlayerFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import java.util.*

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
    var uiBoard = Array(9) { arrayOfNulls<String>(9) }
    private var game = Game(world)

    var score = 0
    var gameOver = false
    var gameWon = false
    var timerOver = false
    private var worldTimer: Int? = 300
    private var timeCount = 0f

    init {
        game.init()
    }

    fun updateTime(dt: Float) {
        timeCount += dt;
        if (timeCount >= 1) {
            if (worldTimer!! > 0) {
                worldTimer = worldTimer!! - 1;
            } else {
                timerOver = true;
            }
            timeCount = 0F;
        }
    }

    fun getTime(): Int? {
        return worldTimer
    }

    fun drawBoard(): Array<Array<String?>> {
        uiBoard = Array(9){ arrayOfNulls<String>(9) }
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

    fun bomb() {
        game.placeBomb()
    }



    fun booster(string: String, powerUp: PowerupType) {
        var x = PlayerSystem.getPosition().first
        var y = PlayerSystem.getPosition().second

        var boosterX = PowerUpSystem.getPosition().first
        var boosterY = PowerUpSystem.getPosition().second

        if (x == boosterX && y == boosterY && powerUp == PowerupType.POINTS) {
            score++;
        }

        if (x == boosterX && y == boosterY && powerUp == PowerupType.RANGE) { //fix
            return
        }
        if (x == boosterX && y == boosterY && powerUp == PowerupType.SPEED) {
            PlayerSystem.setspeed(PowerupType.SPEED.value + 5)
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    PlayerSystem.setspeed(5)

                }
            }, 3000)
        }
    }

    fun isGameOver(): Boolean {

        if (NPCSystem.getLives() == 0) {
            gameWon = true;
            score = PlayerSystem.getLives() * 250 // * tid igjen på timer
            return true
        } else if (timerOver) {
            return true
        }
        return false
    }
}

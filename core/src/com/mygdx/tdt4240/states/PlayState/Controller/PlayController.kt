package com.mygdx.tdt4240.states.PlayState.Controller

import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import com.mygdx.tdt4240.utils.Globals
import kotlin.random.Random

object PlayController {

    private var uiBoard = Array(9) { arrayOfNulls<String>(9) }
    private var game = Game
    private var timerOver = false
    private var worldTimer: Int? = 180
    private var timeCount = 0f

    fun newGame() {
        worldTimer = 180
        timeCount = 0f
        timerOver = false
        game.newGame()
    }

    fun update(dt: Float) {
        if (Globals.newGame) {
            newGame()
        }
        timeCount += dt
        if (timeCount >= 1) {
            if (worldTimer!! > 0) {
                worldTimer = worldTimer!! - 1
            } else {
                timerOver = true
            }
            timeCount = 0F
        }
        spawnPowerUp()
        updatePosNPC()
    }

    fun getTime(): Int? {
        return worldTimer
    }

    fun drawBoard(): Array<Array<String?>> {
        uiBoard = Array(9) { arrayOfNulls(9) }
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (game.board[i][j]?.has(ObstacleComponent) == true) {
                    if (game.board[i][j]?.get(ObstacleComponent)?.wall == true) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                } else if (game.board[i][j]?.has(BoostComponent) == true) {
                    if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.SPEED) {
                        uiBoard[i][j] = "speed"
                    } else if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.RANGE) {
                        uiBoard[i][j] = "range"
                    } else if (game.board[i][j]?.get(BoostComponent)?.powerupType == PowerupType.POINTS) {
                        uiBoard[i][j] = "points"
                    }

                } else if (game.board[i][j]?.has(LifetimeComponent) == true) {
                    if (game.board[i][j]?.get(LifetimeComponent)?.fire == true) {
                        uiBoard[i][j] = "fire"
                    } else {
                        uiBoard[i][j] = "bomb"
                    }

                }
            }}
            return uiBoard

    }

    fun getPlayerPosition() : Pair<Int,Int> {
        return PlayerSystem.getPosition()
    }

    fun getNPCPositions() : Array<Pair<Int,Int>> {
        return NPCSystem.getPositions()
    }

    fun isGameWon(): Boolean {
        if (timerOver) {
            return false
        }
        return game.gameWon()
    }

    fun getPlayerLives(): Int {
        return PlayerSystem.getLives()
    }

    fun getNPCLives() : Array<Int> {
        return NPCSystem.getLives()

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
        game.bomb()
    }
    fun spawnPowerUp() {
        val randInt = Random.nextInt(0,700)
        if(randInt < 2) {
            game.powerUp()
        }
    }

    fun isGameOver(): Boolean {
        if (game.gameOver()) {
            return true
        } else if (timerOver) {
            return true
        }
        return false
    }

    fun currentScore(): Int {
        return PlayerSystem.getScore()
    }

    fun finalScore(): Int {
        if (!isGameWon()) {
            return 0
        }
        return currentScore() + PlayerSystem.getLives() * 100 + (getTime() ?: 1)
    }

}

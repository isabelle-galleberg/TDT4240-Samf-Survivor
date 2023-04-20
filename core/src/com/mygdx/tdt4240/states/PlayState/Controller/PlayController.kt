package com.mygdx.tdt4240.states.PlayState.Controller

import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.PowerupType
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
                if (ObstacleSystem.contains(game.board[i][j])) {
                    if (ObstacleSystem.isWall(game.board[i][j])) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                } else if (PowerupSystem.contains(game.board[i][j])) {
                    if (PowerupSystem.getPowerupType(game.board[i][j]) == PowerupType.SPEED) {
                        uiBoard[i][j] = "speed"
                    } else if (PowerupSystem.getPowerupType(game.board[i][j])== PowerupType.RANGE) {
                        uiBoard[i][j] = "range"
                    } else if (PowerupSystem.getPowerupType(game.board[i][j]) == PowerupType.POINTS) {
                        uiBoard[i][j] = "points"
                    }

                } else if (LifeSystem.contains(game.board[i][j])) {
                    if (LifeSystem.isFire(game.board[i][j])) {
                        uiBoard[i][j] = "fire"
                    } else {
                        uiBoard[i][j] = "bomb"
                    }

                }
            }}
            return uiBoard

    }

    fun getPlayerPosition() : Pair<Int,Int> {
        return game.getPlayerPosition()
    }

    fun getNPCPositions() : Array<Pair<Int,Int>> {
        return game.getNpcPositions()
    }

    fun isGameWon(): Boolean {
        if (timerOver) {
            return false
        }
        return game.gameWon()
    }

    fun getPlayerLives(): Int {
        return game.getPlayerLives()
    }

    fun getNPCLives() : Array<Int> {
        return game.getNpcLives()

    }

    fun updatePos(direction: String) {
        var directionType = DirectionType.NONE
        if (direction == "RIGHT") {
            directionType = DirectionType.RIGHT
        }
        if (direction == "LEFT") {
            directionType = DirectionType.LEFT
        }
        if (direction == "UP") {
            directionType = DirectionType.UP
        }
        if (direction == "DOWN") {
            directionType = DirectionType.DOWN
        }
        game.setPlayerDirection(directionType)
        game.movePlayer()
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
        return ScoreSystem.getScore()
    }

    fun finalScore(): Int {
        if (!isGameWon()) {
            return 0
        }
        return currentScore() + game.getPlayerLives() * 100 + (getTime() ?: 1)
    }

}

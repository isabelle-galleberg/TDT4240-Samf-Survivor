package com.mygdx.tdt4240.states.PlayState.Controller

import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.PowerupType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import com.mygdx.tdt4240.utils.Globals
import kotlin.random.Random

object PlayController {
    private var uiBoard = Array(9) { arrayOfNulls<String>(9) }
    private var timerOver = false
    private var worldTimer: Int? = 180
    private var timeCount = 0f
    private val world = world {
        systems {
            add(ObstacleSystem)
            add(ScoreSystem)
            add(LifeSystem)
            add(CharacterSystem)
            add(PowerupSystem)
        }}
    private var game : Game? = null

    fun newGame() {
        println("start new game")
        game?.dispose()
        println(game)
        game = Game(world)
        println(game)
        worldTimer = 180
        timeCount = 0f
        timerOver = false
        Globals.newGame = false
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
                val tile = game?.getBoard(i,j)
                if (ObstacleSystem.contains(tile)) {
                    if (ObstacleSystem.isWall(tile)) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                } else if (PowerupSystem.contains(tile)) {
                    if (PowerupSystem.getPowerupType(tile) == PowerupType.SPEED) {
                        uiBoard[i][j] = "speed"
                    } else if (PowerupSystem.getPowerupType(tile)== PowerupType.RANGE) {
                        uiBoard[i][j] = "range"
                    } else if (PowerupSystem.getPowerupType(tile) == PowerupType.POINTS) {
                        uiBoard[i][j] = "points"
                    }

                } else if (LifeSystem.contains(tile)) {
                    if (LifeSystem.isFire(tile)) {
                        uiBoard[i][j] = "fire"
                    } else {
                        uiBoard[i][j] = "bomb"
                    }

                }
            }}
            return uiBoard

    }

    fun getPlayerPosition() : Pair<Int,Int> {
        return game?.getPlayerPosition() ?: Pair(0,0)
    }

    fun getNPCPositions() : Array<Pair<Int,Int>> {
        return game?.getNpcPositions() ?: Array(1) {Pair(0,0)}
    }

    fun isGameWon(): Boolean {
        if (timerOver) {
            return false
        }
        return game?.gameWon() ?: false
    }

    fun getPlayerLives(): Int {
        return game?.getPlayerLives() ?: 0
    }

    fun getNPCLives() : Array<Int> {
        return game?.getNpcLives() ?: Array(1) {0}

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
        game?.setPlayerDirection(directionType)
        game?.movePlayer()
    }

    fun updatePosNPC() {
        game?.moveNPC()

    }

    fun bomb() {
        game?.bomb()
    }
    fun spawnPowerUp() {
        val randInt = Random.nextInt(0,700)
        if(randInt < 2) {
            game?.powerUp()
        }
    }

    fun isGameOver(): Boolean {
        if (game!!.gameOver()) {
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
        return currentScore() + game!!.getPlayerLives() * 100 + (getTime() ?: 1)
    }

}

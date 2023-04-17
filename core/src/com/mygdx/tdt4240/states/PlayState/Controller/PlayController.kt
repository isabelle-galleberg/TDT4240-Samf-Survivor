package com.mygdx.tdt4240.states.PlayState.Controller

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObservableComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.FireFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.PlayerFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import com.mygdx.tdt4240.utils.Constants
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
    private var gameOver = false;
    private var gameWon = false;
    private var score = 0
    private var timer = false;
    private var lives = 3;

    init {
        game.initBoard()
    }

    fun drawBoard(): Array<Array<String?>> {
        for (i in 0 until 9) {
            for (j in 0 until 9) {

                if (game.board[i][j]?.has(ObstacleComponent) == true) {
                    if (game.board[i][j]?.get(ObstacleComponent)?.wall == true) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                }
                else if(game.board[i][j]?.has(ObservableComponent) == true) {
                    uiBoard[i][j] = "bomb"
                }
                    else if (game.board[i][j]?.has(CharacterComponent) == true) {
                    if (game.board[i][j]?.has(ScoreComponent) == true) { //Player
                        uiBoard[i][j] = "player"
                    } else {
                        uiBoard[i][j] = "npc"
                    }
                }
            }
        }
        return uiBoard
    }

    //!ObstacleSystem.getPositions().contains(Pair(playerPosition.first, playerPosition.second - 1)*/

    fun updatePos(direction: String) {
            if (direction == "RIGHT") {
                PlayerSystem.setDirection(DirectionType.RIGHT)
                game.movePlayer(game.board, "RIGHT")

            }
            if (direction == "LEFT") {
                PlayerSystem.setDirection(DirectionType.LEFT)
                game.movePlayer(game.board, "LEFT")
            }
            if (direction == "UP") {
                PlayerSystem.setDirection(DirectionType.UP)
                game.movePlayer(game.board, "UP")
            }
            if (direction == "DOWN") {
                PlayerSystem.setDirection(DirectionType.DOWN)
                game.movePlayer(game.board, "DOWN")

            } else {
                PlayerSystem.setDirection(DirectionType.NONE)
        }
    }

    fun bombs(string: String) {
        var x = game.getPlayer(game.board, "x")
        var y = game.getPlayer(game.board, "y")
        if(string == "FIRE") {
            game.placeBombs(game.board, x, y)
        }

    }

    fun booster(string: String, powerUp: PowerupType) {
        var x = game.getPlayer(game.board, "x")
        var y = game.getPlayer(game.board, "y")

        var boosterX = PowerUpSystem.getPosition().first
        var boosterY = PowerUpSystem.getPosition().second

        if(x == boosterX && y == boosterY && powerUp == PowerupType.POINTS) {
            score++;
        }

        if(x == boosterX && y == boosterY && powerUp == PowerupType.RANGE) { //fix
            return
        }
        if(x == boosterX && y == boosterY && powerUp == PowerupType.SPEED) {
            PlayerSystem.setspeed(PowerupType.SPEED.value + 5)

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    PlayerSystem.setspeed(5)

                }
            }, 3000)

        }

    }


    fun isGameOver(entity: Entity): Int {
        if(NPCSystem.getLives(entity) == 0) {
            gameWon = true;
            score = PlayerSystem.getLives() * 250 // * tid igjen på timer
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {

            }
        }, 2000)
        if(true) {
            gameOver = true;
            score = 0;

        }
        return score
    }
}
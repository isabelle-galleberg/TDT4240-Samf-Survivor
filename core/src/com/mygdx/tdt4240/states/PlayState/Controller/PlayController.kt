package com.mygdx.tdt4240.states.PlayState.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.FireFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
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
    private var game = Game(world)



    private var gameOver = false;
    private var gameWon = false;

    private var score = 0
    private var timer = false;

    private var lives = 3;

    fun drawBoard(): Array<Array<String?>> {
        game.initBoard()
        var uiBoard = Array(9) { arrayOfNulls<String>(9) }
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (game.board[i][j]?.has(ObstacleComponent) == true) {
                    if (game.board[i][j]?.get(ObstacleComponent)?.wall == true) {
                        uiBoard[i][j] = "wall"
                    } else {
                        uiBoard[i][j] = "crate"
                    }
                } else if (game.board[i][j]?.has(CharacterComponent) == true) {
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




    fun updatePos(direction: String) {
            var playerPosition = PlayerSystem.getPosition();

            if (direction == "RIGHT" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first + 1, playerPosition.second))
            ) {
                PlayerSystem.setDirection(DirectionType.RIGHT)
                game.movePlayerRight()
            }
            if (direction == "LEFT" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first - 1, playerPosition.second))
            ) {
                PlayerSystem.setDirection(DirectionType.LEFT)
                game.movePlayerLeft()
            }
            if (direction == "UP" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first, playerPosition.second + 1))
            ) {
                PlayerSystem.setDirection(DirectionType.UP)
                game.movePlayerUp()
            }
            if (direction == "DOWN" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first, playerPosition.second - 1))
            ) {
                PlayerSystem.setDirection(DirectionType.DOWN)
                game.movePlayerDown()
            } else {
                PlayerSystem.setDirection(DirectionType.NONE)

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
package com.mygdx.tdt4240.states.PlayState.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.FireFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.ObstacleSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PlayerSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import java.util.*

class PlayController {

    private var gameOver = false;
    private var gameWon = false;

    private var score = 0
    private var timer = false;

    private var lives = 3;

    var gameObject = Game();

    var world = world {  }




    fun updatePos(direction: String) {
            var playerPosition = PlayerSystem.getPosition();

            if (direction == "RIGHT" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first + 1, playerPosition.second))
            ) {
                PlayerSystem.setDirection(DirectionType.RIGHT)
                gameObject.movePlayerRight()
            }
            if (direction == "LEFT" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first - 1, playerPosition.second))
            ) {
                PlayerSystem.setDirection(DirectionType.LEFT)
                gameObject.movePlayerLeft()
            }
            if (direction == "UP" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first, playerPosition.second + 1))
            ) {
                PlayerSystem.setDirection(DirectionType.UP)
                gameObject.movePlayerUp()
            }
            if (direction == "DOWN" && !ObstacleSystem.getPositions()
                    .contains(Pair(playerPosition.first, playerPosition.second - 1))
            ) {
                PlayerSystem.setDirection(DirectionType.DOWN)
                gameObject.movePlayerDown()
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
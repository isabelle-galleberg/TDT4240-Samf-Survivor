package com.mygdx.tdt4240.states.PlayState.Controller

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.BombSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PowerUpSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PowerUpSystem.hasNo
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import com.mygdx.tdt4240.utils.Constants

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


}
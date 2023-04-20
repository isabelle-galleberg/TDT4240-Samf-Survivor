package com.mygdx.tdt4240.states.PlayState.Model.logic.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.CharacterSystem
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import kotlin.random.Random

/* Set bombs with a random delay between 2 and 10 seconds*/
open class Layer3 : Layer2() {
    fun setBombs(entity: Entity?, board: Array<Array<Entity?>>, game: Game) {
        val randInt = Random.nextInt(0,40)
        if (randInt < 3) {
            if (checkEscape(entity)) {
                game.placeBomb(entity)
            }
        }
        avoidCollision(entity, board)
    }

    fun checkEscape(entity: Entity?) : Boolean {
        val x = CharacterSystem.getPosition(entity).first
        val y = CharacterSystem.getPosition(entity).second
        if (x < 3 && (y < 3 || y > 5) || x > 5 && (y < 3 || y > 5)) {
            return false
        }
        return true


    }
}
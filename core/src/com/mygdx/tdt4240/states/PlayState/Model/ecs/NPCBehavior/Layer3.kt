package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import kotlin.random.Random

/* Set bombs with a random delay between 2 and 10 seconds*/
open class Layer3 : Layer2() {
    fun setBombs(entity: Entity, board: Array<Array<Entity?>>, game: Game) {
        val randInt = Random.nextInt(0,75)
        if (randInt < 3) {
            game.placeBomb(entity)
        }
        avoidCollision(entity, board)
    }
}
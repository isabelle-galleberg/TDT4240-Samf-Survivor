package com.mygdx.tdt4240.states.PlayState.Model.logic.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.logic.PlayLogic

/**
 * Handles the NPC behavior
 */
object NPCBehavior : Layer3()  {

    fun npcBehave(entity: Entity?, board: Array<Array<Entity?>>, game: PlayLogic) {
        setBombs(entity, board, game)
    }
}
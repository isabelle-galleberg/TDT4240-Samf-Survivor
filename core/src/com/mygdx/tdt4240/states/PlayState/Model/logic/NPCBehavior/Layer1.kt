package com.mygdx.tdt4240.states.PlayState.Model.logic.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.CharacterSystem
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.DirectionType

/* Change direction*/
open class Layer1 {
    fun changeDirection(entity: Entity?, direction: DirectionType) {
        CharacterSystem.setDirection(entity, direction)
    }
}
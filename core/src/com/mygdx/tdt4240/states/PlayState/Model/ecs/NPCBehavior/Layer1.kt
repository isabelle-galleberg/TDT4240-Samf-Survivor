package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* Change direction*/
open class Layer1 {
    fun changeDirection(entity: Entity, direction: DirectionType) {
        NPCSystem.setDirection(entity, direction)
    }
}
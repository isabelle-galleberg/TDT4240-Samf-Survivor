package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* Change direction*/
open class Layer1 {
    fun changeDirection(direction: DirectionType) {
        NPCSystem.setDirection(direction)
    }
}
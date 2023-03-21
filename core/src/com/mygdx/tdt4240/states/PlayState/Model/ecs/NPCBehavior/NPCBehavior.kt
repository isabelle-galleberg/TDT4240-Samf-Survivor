package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

class NPCBehavior(NPC: NPCSystem) {


}

class Layer1 {
    fun changeDirection(NPC: NPCSystem, direction: DirectionType) {
        NPC.changeDirection(direction)
    }
}
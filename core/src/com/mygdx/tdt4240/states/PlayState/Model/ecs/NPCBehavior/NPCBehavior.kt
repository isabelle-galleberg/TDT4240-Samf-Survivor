package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

class NPCBehavior(NPC: NPCSystem) {


}


/* Avoid collision*/
class Layer2 {
    fun avoidCollision(NPC: NPCSystem) {
        var NPCPosition = NPC.getPosition()
        var NPCDirection = NPC.getDirection()
        var collision = true
        while(collision) {
            if (NPCDirection.equals(DirectionType.DOWN)){

            }
        }
    }

    fun checkPosition(position: Pair<Int, Int>) {

    }


}

/* Change direction*/
class Layer1 {
    fun changeDirection(NPC: NPCSystem, direction: DirectionType) {
        NPC.setDirection(direction)
    }
}
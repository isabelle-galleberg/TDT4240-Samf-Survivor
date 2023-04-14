package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import kotlin.math.abs

/* Move away from bombs nearby*/
open class Layer3 : Layer2() {
    fun avoidBomb(bombPos: Pair<Int, Int> ) {
        NPCSystem.avoidBomb(bombPos)
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import kotlin.math.abs

/* Move away from bombs nearby*/
open class Layer3 : Layer2() {
    fun avoidBomb(bombPos: Pair<Int, Int> ) {
        var NPCPosition = NPCSystem.getPosition()
        if (bombPos.first == NPCPosition.first && abs(bombPos.second - NPCPosition.second) <= 3) {
            if (bombPos.second < NPCPosition.second) {
                changeDirection(randomDirection(DirectionType.UP))
            } else {
                changeDirection(randomDirection(DirectionType.DOWN))
            }
            avoidCollision()
        } else if (bombPos.second == NPCPosition.second && abs(bombPos.first - NPCPosition.first) <= 3) {
            if (bombPos.first < NPCPosition.first) {
                changeDirection(randomDirection(DirectionType.LEFT))
            } else {
                changeDirection(randomDirection(DirectionType.RIGHT))
            }
            avoidCollision()
        }

    }
}
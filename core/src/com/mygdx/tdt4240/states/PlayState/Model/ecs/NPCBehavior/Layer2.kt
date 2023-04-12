package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.TileSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* Avoid collision with fire, walls and crates*/
open class Layer2 : Layer1() {
    fun avoidCollision() {
        var NPCDirection = NPCSystem.getDirection()
        var NPCPosition = NPCSystem.getPosition()
        var collision = true
        while (collision) {
            if (NPCDirection == DirectionType.DOWN) {
                if (NPCPosition.second == 8 || TileSystem.getPositions().contains(Pair(NPCPosition.first, NPCPosition.second + 1))) {
                    NPCDirection = randomDirection(DirectionType.DOWN)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.UP) {
                if (NPCPosition.second == 0 || TileSystem.getPositions().contains(Pair(NPCPosition.first, NPCPosition.second - 1))) {
                    NPCDirection = randomDirection(DirectionType.UP)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.LEFT) {
                if (NPCPosition.first == 0 || TileSystem.getPositions().contains(Pair(NPCPosition.first - 1, NPCPosition.second))) {
                    NPCDirection = randomDirection(DirectionType.LEFT)
                } else {
                    collision = false
                }
            } else {
                if (NPCPosition.first == 8 || TileSystem.getPositions().contains(Pair(NPCPosition.first + 1, NPCPosition.second))) {
                    NPCDirection = randomDirection(DirectionType.RIGHT)
                } else {
                    collision = false
                }
            }
        }
        changeDirection(NPCDirection)

    }
    /* Finds new random direction that is not the NotDirection*/
    fun randomDirection(NotDirection: DirectionType): DirectionType {
        var randomDirections = DirectionType.values().toList().shuffled()
        return if (randomDirections.first() == NotDirection)
            randomDirections.last()
        else {
            randomDirections.first()
        }
    }
}
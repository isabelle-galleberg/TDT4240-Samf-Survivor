package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.CharacterSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.has
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* Avoid collision with fire, bombs, walls, crates*/
open class Layer2 : Layer1() {
    fun avoidCollision(entity: Entity, board : Array<Array<Entity?>>) : DirectionType {
        var NPCDirection = CharacterSystem.getDirection(entity)
        val x = CharacterSystem.getPosition(entity).first
        val y = CharacterSystem.getPosition(entity).second
        var collision = true
        while (collision) {
            if (NPCDirection == DirectionType.DOWN) {
                if (y -1 < 0 || board[x][y-1]?.has(ObstacleComponent) == true || board[x][y-1]?.has(LifetimeComponent) == true) {
                    NPCDirection = randomDirection(DirectionType.DOWN)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.UP) {
                if (y +1 > 8 || board[x][y+1]?.has(ObstacleComponent) == true || board[x][y+1]?.has(LifetimeComponent) == true ) {
                    NPCDirection = randomDirection(DirectionType.UP)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.LEFT) {
                if (x -1 < 0 || board[x-1][y]?.has(ObstacleComponent) == true || board[x-1][y]?.has(LifetimeComponent) == true) {
                    NPCDirection = randomDirection(DirectionType.LEFT)
                } else {
                    collision = false
                }
            } else {
                if (x + 1 > 8 || board[x+1][y]?.has(ObstacleComponent) == true || board[x+1][y]?.has(LifetimeComponent) == true) {
                    NPCDirection = randomDirection(DirectionType.RIGHT)
                } else {
                    collision = false
                }
            }
        }
        changeDirection(entity, NPCDirection)
        return NPCDirection

    }
    /* Finds new random direction that is not the NotDirection*/
    fun randomDirection(NotDirection: DirectionType): DirectionType {
        return DirectionType.values().filterNot { d -> (d==DirectionType.NONE || d==NotDirection) }.random()

    }
}
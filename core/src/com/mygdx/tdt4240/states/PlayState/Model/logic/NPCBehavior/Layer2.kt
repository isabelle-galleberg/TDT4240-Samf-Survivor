package com.mygdx.tdt4240.states.PlayState.Model.logic.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.CharacterSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.LifeSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.ObstacleSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PowerupSystem
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.DirectionType
import kotlin.random.Random

/* Avoid collision with fire, bombs, walls, crates*/
open class Layer2 : Layer1() {
    fun avoidCollision(entity: Entity?, board : Array<Array<Entity?>>) : DirectionType {
        var NPCDirection = CharacterSystem.getDirection(entity)
        val randInt = Random.nextInt(0,8)
        if (randInt == 0) {
            NPCDirection = randomDirection(DirectionType.NONE)
        }
        val x = CharacterSystem.getPosition(entity).first
        val y = CharacterSystem.getPosition(entity).second
        var collision = true
        while (collision) {
            if (NPCDirection == DirectionType.DOWN) {
                if (y -1 < 0 || checkIfCanWalk(board[x][y-1])) {
                    NPCDirection = randomDirection(DirectionType.DOWN)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.UP) {
                if (y +1 > 8 || checkIfCanWalk(board[x][y+1]) ) {
                    NPCDirection = randomDirection(DirectionType.UP)
                } else {
                    collision = false
                }
            } else if (NPCDirection == DirectionType.LEFT) {
                if (x -1 < 0 || checkIfCanWalk(board[x-1][y])) {
                    NPCDirection = randomDirection(DirectionType.LEFT)
                } else {
                    collision = false
                }
            } else {
                if (x + 1 > 8 || checkIfCanWalk(board[x+1][y])) {
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
        return DirectionType.values().filterNot { d -> (d== DirectionType.NONE || d==NotDirection) }.random()

    }

    fun checkIfCanWalk(entity: Entity?):Boolean {
        return ObstacleSystem.contains(entity) || (LifeSystem.contains(entity) && !PowerupSystem.contains(entity))
    }
}
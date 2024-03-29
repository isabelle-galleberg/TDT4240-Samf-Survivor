package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent

/**
 * The concrete factory following the abstract factory pattern for creating walls on game board
 */
object WallFactory {
    fun createWall(world: World): Entity {
        val entity: Entity = world.entity {
            it += ObstacleComponent(true)
        }
        return entity
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent

/**
 * The concrete factory following the abstract factory pattern for creating crates on gameboard
 */
object CrateFactory {
    fun createCrate(world: World): Entity {
        val entity: Entity = world.entity {
            it += ObstacleComponent(false)
        }
        return entity
    }
}
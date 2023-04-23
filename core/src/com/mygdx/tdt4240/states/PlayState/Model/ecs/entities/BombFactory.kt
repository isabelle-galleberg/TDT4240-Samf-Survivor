package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent

/**
 * The concrete factory following the abstract factory pattern for creating bombs on gameboard
 */
object BombFactory {
    fun createBomb(world: World): Entity{
        val entity: Entity = world.entity {
            it += LifetimeComponent()
        }
        return entity
    }
}
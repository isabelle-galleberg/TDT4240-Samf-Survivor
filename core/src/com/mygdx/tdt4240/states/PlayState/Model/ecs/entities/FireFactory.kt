package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent

/* The concrete factory following the abstract factory pattern for creating fire on gameboard */
object FireFactory {
    fun createFire(world: World): Entity {
        val entity: Entity = world.entity {
            it += LifetimeComponent(true,2000)
        }
        return entity
    }
}
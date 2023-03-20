package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObservableComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* The concrete factory following the abstract factory pattern for creating fire on gameboard */
object FireFactory {
    fun createFire(world: World, position: Pair<Int, Int>): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(position)
            it += LifetimeComponent()
        }
        return entity
    }
}
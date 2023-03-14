package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.VisibleComponent

/* The concrete factory following the abstract factory pattern for creating crates on gameboard */
object CrateFactory {
    fun createCrate(world: World, position: Pair<Int, Int>): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(position)
            it += VisibleComponent(true)
        }
        return entity
    }
}
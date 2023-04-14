package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* The concrete factory following the abstract factory pattern for creating crates on gameboard */
object CrateFactory {
    fun createCrate(world: World, x:Int, y: Int): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(x,y)

        }
        return entity
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* The concrete factory following the abstract factory pattern for creating walls on gameboard */
object WallFactory {
    fun createWall(world: World, position: Pair<Int, Int>): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(position)
        }
        return entity
    }
}
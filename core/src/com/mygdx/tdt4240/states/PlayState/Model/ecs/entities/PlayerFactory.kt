package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*

/* The concrete factory following the abstract factory pattern for creating player(s) on gameboard */
object PlayerFactory {
    fun createPlayer(world: World, position: Pair<Int, Int>): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(position)
            it += CharacterComponent()
            it += ScoreComponent()
        }
        return entity
    }
}
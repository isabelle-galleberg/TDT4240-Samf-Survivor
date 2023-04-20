package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent

/* The concrete factory following the abstract factory pattern for creating NPC(s) on gameboard */
object NPCFactory {
    fun createNPC(world: World, x: Int, y: Int): Entity {
        val entity: Entity = world.entity {
            it += CharacterComponent(x,y)
        }
        return entity
    }
}
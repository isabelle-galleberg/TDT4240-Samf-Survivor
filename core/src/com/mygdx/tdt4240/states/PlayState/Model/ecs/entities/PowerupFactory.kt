package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

/* The concrete factory following the abstract factory pattern for creating powerups on gameboard */
object PowerupFactory {

    fun createPowerup(world: World,x: Int, y: Int, powerupType: PowerupType ): Entity {
        val entity: Entity = world.entity {
            it += SpriteComponent(x,y)
            it += BoostComponent(powerupType)
        }
        return entity
    }
}
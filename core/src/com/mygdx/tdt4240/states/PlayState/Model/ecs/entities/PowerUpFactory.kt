package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerUpType

/**
 * The concrete factory following the abstract factory pattern for creating powerups on gameboard
 */
object PowerUpFactory {
    fun createPowerUp(world: World, powerUpType: PowerUpType): Entity {
        val entity: Entity = world.entity {
            it += BoostComponent(powerUpType)
            it += LifetimeComponent(false,7000)
        }
        return entity
    }
}
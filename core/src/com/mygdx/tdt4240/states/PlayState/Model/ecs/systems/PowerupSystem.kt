package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.PowerupType

object PowerupSystem : IteratingSystem(
    World.family { all(BoostComponent) }
) {

    fun getPowerupType(entity: Entity?) : PowerupType {
        return entity?.get(BoostComponent)?.powerupType ?: PowerupType.POINTS
    }

    fun contains(entity: Entity?) : Boolean {
        if (entity != null) {
            return family.contains(entity)
        }
        return false
    }


    override fun onTickEntity(entity: Entity) {

    }


}
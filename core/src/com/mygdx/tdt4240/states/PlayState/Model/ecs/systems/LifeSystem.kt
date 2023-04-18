package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

/* System for the powerups and bombs*/
object LifeSystem : IteratingSystem(
    World.family { all(LifetimeComponent) }
) {

    fun getLifeTime(entity: Entity?): Long {
        return entity?.get(LifetimeComponent)?.lifetime ?: 2000
    }

    fun getPowerupType(entity: Entity?) : PowerupType {
        return entity?.get(BoostComponent)?.powerupType ?: PowerupType.POINTS
    }





    override fun onTickEntity(entity: Entity) {

    }


}
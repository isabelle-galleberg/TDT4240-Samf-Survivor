package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*

/**
 * System for powerups, bombs and fire
 */
object LifeSystem : IteratingSystem(
    World.family { all(LifetimeComponent) }
) {

    fun getLifeTime(entity: Entity?): Long {
        return entity?.get(LifetimeComponent)?.lifetime ?: 2000
    }

    fun contains(entity: Entity?) : Boolean {
        if (entity != null) {
            return family.contains(entity)
        }
        return false
    }
    fun isFire(entity: Entity?) : Boolean {
        return entity?.get(LifetimeComponent)?.fire ?: false
    }

    override fun onTickEntity(entity: Entity) {

    }


}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObstacleComponent

/**
 * System for walls and crates
 */
object ObstacleSystem : IteratingSystem(
    World.family { all(ObstacleComponent) }
) {

    fun contains(entity: Entity?) : Boolean {
        if (entity != null) {
            return family.contains(entity)
        }
        return false
    }

    fun isWall(entity: Entity?) : Boolean {
        return entity?.get(ObstacleComponent)?.wall ?: false
    }

    override fun onTickEntity(entity: Entity) {

    }
}
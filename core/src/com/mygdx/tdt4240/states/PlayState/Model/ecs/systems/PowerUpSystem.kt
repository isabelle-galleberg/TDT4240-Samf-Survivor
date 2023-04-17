package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*

object PowerUpSystem : IteratingSystem(
    World.family { all(BoostComponent) }
) {

    fun getPosition(): Pair<Int, Int> {
        return Pair(family.first()[SpriteComponent].x, family.first()[SpriteComponent].y)

    }

    fun getLifeTime(): Int {
        return family.first()[LifetimeComponent].lifetime
    }




    override fun onTickEntity(entity: Entity) {

    }


}
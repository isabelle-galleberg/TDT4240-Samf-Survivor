package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.ObstacleSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PlayerSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

object PowerUpSystem : IteratingSystem(
    World.family { all(BoostComponent) }
) {

    fun getPosition(): Pair<Int, Int> {
        return Pair(family.first().get(SpriteComponent).x, family.first().get(SpriteComponent).y)

    }

    fun getLifeTime(): Int {
        return family.first().get(LifetimeComponent).lifetime
    }




    override fun onTickEntity(entity: Entity) {

    }


}
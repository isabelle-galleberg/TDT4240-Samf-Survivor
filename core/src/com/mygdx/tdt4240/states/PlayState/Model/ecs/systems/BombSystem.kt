package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory

object BombSystem: IteratingSystem(
    World.family { all(SpriteComponent) }
) {

    fun getPosition(): Pair<Int, Int> {
        return Pair(family.first()[SpriteComponent].x, family.first()[SpriteComponent].y)
    }

    fun dropBomb(world: World, x: Int, y: Int) {
        BombFactory.createBomb(world, x, y)
    }

    fun getLifeTime(): Int {
        return family.first()[LifetimeComponent].lifetime
    }

    override fun onTickEntity(entity: Entity) {

    }
}
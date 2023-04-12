package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior.Observer
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.LifetimeComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObservableComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* The concrete factory following the abstract factory pattern for creating bombs on gameboard */
object BombFactory {
    private var observers = mutableListOf<Observer>()

    fun callObservers(bombPos: Pair<Int, Int>) {
        for(obs in observers) obs.update(bombPos)
    }

    fun attach(obs : Observer) {
        observers.add(obs)
    }

    fun detach(obs : Observer) {
        observers.remove(obs)
    }

    fun createBomb(world: World, x: Int, y: Int): Entity{
        val entity: Entity = world.entity {
            it += SpriteComponent(x,y)
            it += ObservableComponent()
            it += LifetimeComponent()
        }
        callObservers(Pair(x,y))
        return entity
    }
}
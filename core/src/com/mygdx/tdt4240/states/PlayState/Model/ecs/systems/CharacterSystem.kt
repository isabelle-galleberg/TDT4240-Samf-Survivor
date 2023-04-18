package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.github.quillraven.fleks.World.Companion.family

/* System for NPC and Player*/
object CharacterSystem : IteratingSystem(
        family { all(CharacterComponent) }
    ) {
    fun setDirection(entity: Entity, direction: DirectionType) {
        entity[CharacterComponent].changeDirection(direction)
    }

    fun getPosition(entity: Entity):Pair<Int,Int> {
        return Pair(entity[CharacterComponent].x, entity[CharacterComponent].y)
    }

    fun setPosition(entity: Entity, x:Int, y:Int) {
        entity[CharacterComponent].setPosition(x,y)
    }

    fun getDirection(entity: Entity): DirectionType {
        return entity[CharacterComponent].direction
    }

    fun getFirelength(entity: Entity): Int {
        return entity[CharacterComponent].firelength
    }

    fun getLives(entity: Entity?): Int {
        return entity?.get(CharacterComponent)?.lives ?: 0
    }

    fun reduceLives(entity: Entity) {
        entity[CharacterComponent].reduceLives()
    }


    override fun onTickEntity(entity: Entity) {

    }

    fun getSpeed(entity: Entity): Int {
        return entity[CharacterComponent].speed
    }

    fun setSpeed(entity: Entity, speed: Int) {
        entity[CharacterComponent].changeSpeed(speed)
    }

    fun setFirelength(entity: Entity, range: Int) {
        entity[CharacterComponent].changeFirelength(range)
    }
}
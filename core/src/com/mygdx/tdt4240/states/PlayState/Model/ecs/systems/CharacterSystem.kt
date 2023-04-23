package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.utils.Constants

/**
 * System for NPC and Player
 */
object CharacterSystem : IteratingSystem(
        family { all(CharacterComponent) }
    ) {

    fun getPosition(entity: Entity?):Pair<Int,Int> {
        val x = entity?.get(CharacterComponent)?.x
        val y = entity?.get(CharacterComponent)?.y
        if (x != null && y != null) {
            return Pair(x,y)
        }
        return Pair(0,0)
    }

    fun setPosition(entity: Entity?, x:Int, y:Int) {
        entity?.get(CharacterComponent)?.setPosition(x,y)
    }

    fun getDirection(entity: Entity?): DirectionType {
        return entity?.get(CharacterComponent)?.direction ?: DirectionType.DOWN
    }

    fun setDirection(entity: Entity?,direction: DirectionType) {
        entity?.get(CharacterComponent)?.changeDirection(direction)
    }

    fun getFireLength(entity: Entity?): Int {
        return entity?.get(CharacterComponent)?.fireLength ?: Constants.STARTFIRELENGTH
    }

    fun getLives(entity: Entity?): Int {
        return entity?.get(CharacterComponent)?.lives ?: 0
    }

    fun reduceLives(entity: Entity?) {
        entity?.get(CharacterComponent)?.reduceLives()
    }

    fun getSpeed(entity: Entity?): Int {
        return entity?.get(CharacterComponent)?.speed ?: Constants.STARTSPEED
    }

    fun setSpeed(entity: Entity?, speed: Int) {
        entity?.get(CharacterComponent)?.changeSpeed(speed)
    }

    fun setFireLength(entity: Entity?, range: Int) {
        entity?.get(CharacterComponent)?.changeFireLength(range)
    }

    override fun onTickEntity(entity: Entity) {

    }
}
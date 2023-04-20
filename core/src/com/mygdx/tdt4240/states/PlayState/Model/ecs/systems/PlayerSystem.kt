package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* System for the player and NPC*/
object PlayerSystem : IteratingSystem(
    family { all(ScoreComponent) }
) {

    fun getDirection(): DirectionType {
        return family.first()[CharacterComponent].direction
    }

    fun setDirection(direction: DirectionType) {
        family.first()[CharacterComponent].changeDirection(direction)
    }

    fun getPosition():Pair<Int,Int> {
        return Pair(family.first()[CharacterComponent].x, family.first()[CharacterComponent].y)
    }

    fun getLives(): Int {
        return family.first()[CharacterComponent].lives
    }
    fun setSpeed(speed: Int) {
        family.first()[CharacterComponent].changeSpeed(speed)
    }

    fun setPosition(x:Int,y:Int) {
        family.first()[CharacterComponent].setPosition(x,y)
    }
    fun getScore():Int {
        return family.first()[ScoreComponent].score
    }

    fun addScore(score: Int) {
        family.first()[ScoreComponent].addScore(score)
    }

    override fun onTickEntity(entity: Entity) {

    }
}

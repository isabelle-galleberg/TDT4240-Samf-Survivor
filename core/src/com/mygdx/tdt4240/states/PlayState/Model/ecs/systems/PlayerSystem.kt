package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* System for the player and NPC*/
object PlayerSystem : IteratingSystem(
    family { all(PlayerComponent) }
) {

    fun getDirection(): DirectionType {
        return family.first()[CharacterComponent].direction
    }

    fun setDirection(direction: DirectionType) {
        family.first()[CharacterComponent].changeDirection(direction)
    }

    fun getPosition():Pair<Int,Int> {
        return Pair(family.first()[SpriteComponent].x, family.first()[SpriteComponent].y)
    }

    fun getLives(): Int {
        return family.first()[CharacterComponent].lives
    }
    fun setSpeed(speed: Int) {
        family.first()[CharacterComponent].changeSpeed(speed)
    }

    fun setPosition(position: Pair<Int,Int>) {
        family.first()[SpriteComponent].changePosition(position)
    }

    fun reduceLives() {
        family.first()[CharacterComponent].reduceLives()
    }

    fun resetLives() {
        family.first()[CharacterComponent].resetLives()
    }

    fun getScore():Int {
        return family.first()[PlayerComponent].score

    }

    fun getFireLength():Int {
        return family.first()[PlayerComponent].fireLength

    }

    fun setScore(score: Int) {
        family.first()[PlayerComponent].changeScore(score)
    }

    override fun onTickEntity(entity: Entity) {

    }
}

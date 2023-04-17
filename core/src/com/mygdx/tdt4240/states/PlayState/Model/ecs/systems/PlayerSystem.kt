package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType


/* System for the player and NPC*/
object PlayerSystem : IteratingSystem(
    family { all(PlayerComponent) }
) {
    private var isHit = false;

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
    fun setspeed(speed: Int) {
        family.first()[CharacterComponent].changeSpeed(speed)
    }

    fun setPosition(component: String, value: Int) {
        if (component == "x") {
            family.first()[SpriteComponent].changePositionX(value);
        } else if (component == "y") {
            family.first()[SpriteComponent].changePositionY(value);
        }
    }

    fun reduceLives() {
        family.first()[CharacterComponent].reduceLives()
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

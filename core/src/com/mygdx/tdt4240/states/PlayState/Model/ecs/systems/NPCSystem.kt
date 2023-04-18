package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.collection.EntityBag
import com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior.NPCBehavior
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.PlayerComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import kotlin.math.abs
import kotlin.random.Random

/* System for the NPC*/
object NPCSystem : IteratingSystem(
    World.family { all(CharacterComponent).none(PlayerComponent)}
) {

    fun setDirection(entity: Entity,direction: DirectionType) {
        entity[CharacterComponent].changeDirection(direction)
    }

    fun getPosition():Pair<Int,Int> {
        return Pair(family.first()[SpriteComponent].x, family.first()[SpriteComponent].y)
    }

    fun getPosition(entity: Entity):Pair<Int,Int> {
        return Pair(entity[SpriteComponent].x, entity[SpriteComponent].y)
    }

    fun setPosition(component: String, value: Int) {
        if (component == "x") {
            family.first()[SpriteComponent].changePositionX(value)
        } else if (component == "y") {
            family.first()[SpriteComponent].changePositionY(value)
        }
    }
    fun setPosition(position: Pair<Int,Int>) {
        family.first()[SpriteComponent].changePosition(position)
    }

    fun getDirection(entity: Entity): DirectionType {
        return entity[CharacterComponent].direction
    }

    fun getLives(): Int {
        return family.first()[CharacterComponent].lives
    }

    fun getSpeed(): Int {
        return family.first()[CharacterComponent].speed
    }

    fun reduceLives() {
        family.first()[CharacterComponent].reduceLives()
    }

    fun resetLives() {
        family.first()[CharacterComponent].resetLives()
    }

    fun getNPCs() : EntityBag {
        return family.entities
    }

    fun getRandomNPC() : Entity {
        val randIndex = Random.nextInt(0,family.numEntities)
        return family.entities[randIndex]
    }

    override fun onTickEntity(entity: Entity) {
        TODO("Not yet implemented")
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
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
            family.first()[SpriteComponent].changePositionX(value);
        } else if (component == "y") {
            family.first()[SpriteComponent].changePositionY(value);
        }
    }

    fun getDirection(entity: Entity): DirectionType {
        return entity[CharacterComponent].direction
    }
    fun getLives(): Int {
        return family.first()[CharacterComponent].lives
    }

    fun avoidBomb(bombPos: Pair<Int, Int>) {
        family.forEach { e ->
            var NPCPosition = getPosition(e)
            if (bombPos.first == NPCPosition.first && abs(bombPos.second - NPCPosition.second) <= 3) {
                if (bombPos.second < NPCPosition.second) {
                    setDirection(e,NPCBehavior.randomDirection(DirectionType.UP))
                } else {
                    setDirection(e,NPCBehavior.randomDirection(DirectionType.UP))
                }
                NPCBehavior.avoidCollision(e)
            } else if (bombPos.second == NPCPosition.second && abs(bombPos.first - NPCPosition.first) <= 3) {
                if (bombPos.first < NPCPosition.first) {
                    setDirection(e,NPCBehavior.randomDirection(DirectionType.LEFT))
                } else {
                    setDirection(e,NPCBehavior.randomDirection(DirectionType.RIGHT))
                }
                NPCBehavior.avoidCollision(e)
            }
        }
    }

    fun getRandomNPC() : Entity {
        var randIndex = Random.nextInt(0,family.numEntities)
        return family.entities[randIndex]
    }

    override fun onTickEntity(entity: Entity) {
        TODO("Not yet implemented")
    }
}
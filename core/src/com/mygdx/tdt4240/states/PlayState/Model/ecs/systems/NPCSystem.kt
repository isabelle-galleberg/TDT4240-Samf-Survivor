package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

/* System for the NPC*/
object NPCSystem : IteratingSystem(
    World.family { all(CharacterComponent).none(ScoreComponent)}
) {

    fun setDirection(direction: DirectionType) {
        family.first().get(CharacterComponent).changeDirection(direction)
    }

    fun getPosition():Pair<Int,Int> {
        return Pair(family.first().get(SpriteComponent).x, family.first().get(SpriteComponent).y)
    }

    fun getDirection(): DirectionType {
        return family.first().get(CharacterComponent).direction
    }

    override fun onTickEntity(entity: Entity) {
        TODO("Not yet implemented")
    }
}
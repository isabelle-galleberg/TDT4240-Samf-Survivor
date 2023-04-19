package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.collection.EntityBag
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.PlayerComponent

/* System for the NPC*/
object NPCSystem : IteratingSystem(
    World.family { all(CharacterComponent).none(PlayerComponent)}
) {
    fun getNPCs() : EntityBag {
        return family.entities
    }

    fun getSpeed(): Int {
        return family.entities.first()[CharacterComponent].speed
    }

    fun getPositions() : Array<Pair<Int,Int>> {
        val pos = Array<Pair<Int,Int>>(family.numEntities){Pair(0,0)}
        for (i in pos.indices) {
            pos[i] = Pair(family.entities[i].get(CharacterComponent).x,family.entities[i].get(CharacterComponent).y)
        }
        return pos
    }

    fun getLives(): Array<Int> {
        val lives = Array<Int>(family.numEntities){0}
        for (i in lives.indices) {
            lives[i] = family.entities[i].get(CharacterComponent).lives
        }
        return lives
    }

    override fun onTickEntity(entity: Entity) {
        TODO("Not yet implemented")
    }
}
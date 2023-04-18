package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObservableComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* System for wall, crate and fire*/
object ObstacleSystem : IteratingSystem(
    World.family { all(SpriteComponent).none(CharacterComponent, ObservableComponent, BoostComponent) }
) {
    private var positions: MutableList<Pair<Int, Int>> = createPositionList()
    //TODO(update this list when setting positions of tiles)

    override fun onTickEntity(entity: Entity) {
        TODO("Funksjon som oppdaterer alle spillerne (f.eks. sprites og posisjon")
    }

    private fun createPositionList(): MutableList<Pair<Int, Int>> {
        val newPositions: MutableList<Pair<Int,Int>> = mutableListOf()
        family.entities.forEach {
            newPositions.add(Pair(it[SpriteComponent].x, it[SpriteComponent].y))
        }
        return newPositions
    }

    fun getPositions(): MutableList<Pair<Int,Int>> {
        return positions
    }
}
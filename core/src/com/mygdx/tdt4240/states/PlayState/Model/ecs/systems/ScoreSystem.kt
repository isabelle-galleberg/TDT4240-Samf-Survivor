package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*

/**
 * System for the player and NPC
 */
object ScoreSystem : IteratingSystem(
    family { all(ScoreComponent) }
) {
    fun getScore():Int {
        return family.first()[ScoreComponent].score
    }

    fun addScore(score: Int) {
        family.first()[ScoreComponent].addScore(score)
    }

    override fun onTickEntity(entity: Entity) {

    }
}

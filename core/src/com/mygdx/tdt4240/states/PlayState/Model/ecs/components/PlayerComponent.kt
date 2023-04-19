package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

/* Component containing score of player */
data class PlayerComponent(
    var score:Int = 0
) : Component<PlayerComponent> {

    fun addScore(score: Int) {
        this@PlayerComponent.score += score
    }

    override fun type() = PlayerComponent

    companion object : ComponentType<PlayerComponent>()
}
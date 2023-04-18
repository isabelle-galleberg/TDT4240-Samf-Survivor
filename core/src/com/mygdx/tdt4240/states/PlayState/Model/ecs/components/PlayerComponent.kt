package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class PlayerComponent(
    var score:Int = 0,
    var fireLength:Int = 3
) : Component<PlayerComponent> {

    fun changeScore(score: Int) {
        this@PlayerComponent.score = score
    }

    override fun type() = PlayerComponent

    companion object : ComponentType<PlayerComponent>()
}
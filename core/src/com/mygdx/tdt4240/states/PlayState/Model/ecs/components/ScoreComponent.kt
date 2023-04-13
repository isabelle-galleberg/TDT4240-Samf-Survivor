package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

data class ScoreComponent(
    var score:Int = 0
) : Component<ScoreComponent> {

    fun changeScore(score: Int) {
        this@ScoreComponent.score = score
    }

    override fun type() = ScoreComponent

    companion object : ComponentType<ScoreComponent>()
}
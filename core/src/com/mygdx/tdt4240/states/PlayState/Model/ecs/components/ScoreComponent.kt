package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class ScoreComponent(
    var score:Int = 0
) : Component<ScoreComponent> {

    override fun type() = ScoreComponent

    companion object : ComponentType<ScoreComponent>()
}
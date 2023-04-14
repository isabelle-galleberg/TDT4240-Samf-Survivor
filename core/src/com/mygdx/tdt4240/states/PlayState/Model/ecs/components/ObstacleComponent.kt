package com.mygdx.tdt4240.states.PlayState.Model.ecs.components


import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

data class ObstacleComponent(
    var wall:Boolean = false,
) : Component<ObstacleComponent> {

    override fun type() = ObstacleComponent

    companion object : ComponentType<ObstacleComponent>()
}
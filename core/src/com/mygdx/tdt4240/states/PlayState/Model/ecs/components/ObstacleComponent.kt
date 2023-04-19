package com.mygdx.tdt4240.states.PlayState.Model.ecs.components


import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

/* Component containing what type of obstacle */
data class ObstacleComponent(
    var wall:Boolean = false //if false: crate
) : Component<ObstacleComponent> {

    override fun type() = ObstacleComponent

    companion object : ComponentType<ObstacleComponent>()
}
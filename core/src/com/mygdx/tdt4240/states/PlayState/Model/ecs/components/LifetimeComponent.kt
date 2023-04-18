package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class LifetimeComponent(
    var lifetime:Int = 2, //lifetime in seconds
    var fire:Boolean = false,
) : Component<LifetimeComponent> {

    override fun type() = LifetimeComponent

    companion object : ComponentType<LifetimeComponent>()
}
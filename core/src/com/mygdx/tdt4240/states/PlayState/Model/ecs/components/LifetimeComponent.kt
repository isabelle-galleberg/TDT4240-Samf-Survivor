package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

/* Component containing whether entity is fire and lifetime in seconds */
data class LifetimeComponent(
    var fire:Boolean = false, //if false: bomb
    var lifetime:Long = 2000, //lifetime in milliseconds
) : Component<LifetimeComponent> {

    override fun type() = LifetimeComponent

    companion object : ComponentType<LifetimeComponent>()
}
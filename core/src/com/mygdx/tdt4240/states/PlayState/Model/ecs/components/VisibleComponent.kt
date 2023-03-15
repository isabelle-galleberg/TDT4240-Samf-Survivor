package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class VisibleComponent(
    var visible : Boolean = false
) : Component<VisibleComponent> {

    override fun type() = VisibleComponent

    companion object : ComponentType<VisibleComponent>()
}
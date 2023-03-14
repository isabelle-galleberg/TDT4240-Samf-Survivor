package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class ObservableComponent(
    //TODO: Add Observers observers
    var lifetime:Int = 2 //remove this
) : Component<ObservableComponent> {

    override fun type() = ObservableComponent

    companion object : ComponentType<ObservableComponent>()
}
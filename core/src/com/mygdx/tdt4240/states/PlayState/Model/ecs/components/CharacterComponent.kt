package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class CharacterComponent(
    var x:Int = 0,
    var y:Int = 0,
    var speed:Int = 5,
    var lives:Int = 3,
    var fireLength:Int = 3
) : Component<CharacterComponent> {

    override fun type() = CharacterComponent

    companion object : ComponentType<CharacterComponent>()
}

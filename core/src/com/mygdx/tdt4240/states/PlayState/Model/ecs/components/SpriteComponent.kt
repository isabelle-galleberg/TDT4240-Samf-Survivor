package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class SpriteComponent(
    // TODO: add Sprite picture
    var x: Int = 0,
    var y: Int = 0
) : Component<SpriteComponent> {

    override fun type() = SpriteComponent

    companion object : ComponentType<SpriteComponent>()
}
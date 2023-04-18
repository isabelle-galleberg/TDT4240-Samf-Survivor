package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

data class SpriteComponent(
    // TODO: add Sprite picture
    var x: Int = 0,
    var y: Int = 0
) : Component<SpriteComponent> {

    fun changePosition(position: Pair<Int,Int>) {
        this@SpriteComponent.x = position.first
        this@SpriteComponent.y = position.second

    }

    fun changePositionX(x: Int) {
        this@SpriteComponent.x = x

    }

    fun changePositionY(y: Int) {
        this@SpriteComponent.y = y

    }


    override fun type() = SpriteComponent

    companion object : ComponentType<SpriteComponent>()
}
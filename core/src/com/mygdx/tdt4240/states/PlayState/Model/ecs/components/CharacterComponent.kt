package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType

data class CharacterComponent(
    var direction:DirectionType = DirectionType.DOWN,
    var speed:Int = 5,
    var lives:Int = 3,
) : Component<CharacterComponent> {

    override fun type() = CharacterComponent

    fun changeDirection(direction: DirectionType) {
        this@CharacterComponent.direction = direction
    }

    fun changeSpeed(speed: Int) {
        this@CharacterComponent.speed = speed
    }

    fun reduceLives() {
        this@CharacterComponent.lives -= 1
    }

    companion object : ComponentType<CharacterComponent>()
}

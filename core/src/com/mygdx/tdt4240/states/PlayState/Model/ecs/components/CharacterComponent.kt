package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.utils.Constants

/* Component containing direction, speed, amount of lives and length of fire from bombs*/
data class CharacterComponent(
    var x: Int = 0,
    var y: Int = 0,
    var direction: DirectionType = DirectionType.DOWN,
    var speed:Int = Constants.STARTSPEED,
    var lives:Int = 3,
    var firelength:Int = Constants.STARTFIRELENGTH
) : Component<CharacterComponent> {


    override fun type() = CharacterComponent

    fun changeDirection(direction: DirectionType) {
        this@CharacterComponent.direction = direction
    }

    fun setPosition(x:Int,y:Int) {
        this@CharacterComponent.x = x
        this@CharacterComponent.y = y

    }

    fun changeSpeed(speed: Int) {
        this@CharacterComponent.speed = speed
    }

    fun reduceLives() {
        if (this@CharacterComponent.lives > 0) {
            this@CharacterComponent.lives -= 1
        }
    }

    fun changeFirelength(range: Int) {
        this@CharacterComponent.firelength = range
    }

    companion object : ComponentType<CharacterComponent>()
}

package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

data class BoostComponent(
    var bombRange: PowerupType = PowerupType.RANGE,
    var boostSpeed: PowerupType = PowerupType.SPEED,
    var points: PowerupType = PowerupType.POINTS
) : Component<BoostComponent> {

    override fun type() = BoostComponent

    companion object : ComponentType<BoostComponent>() {

    }
}
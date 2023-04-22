package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerUpType

/**
 * Component containing poweruptype
 */
data class BoostComponent(
    var powerUpType: PowerUpType = PowerUpType.SPEED
) : Component<BoostComponent> {

    override fun type() = BoostComponent

    companion object : ComponentType<BoostComponent>() {

    }
}
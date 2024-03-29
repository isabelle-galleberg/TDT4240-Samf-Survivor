package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

/**
 * Component containing poweruptype
 */
data class BoostComponent(
    var powerUpType: PowerupType = PowerupType.SPEED
) : Component<BoostComponent> {

    override fun type() = BoostComponent

    companion object : ComponentType<BoostComponent>() {

    }
}
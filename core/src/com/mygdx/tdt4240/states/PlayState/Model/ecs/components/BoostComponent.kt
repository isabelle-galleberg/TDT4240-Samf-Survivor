package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.logic.types.PowerupType

/* Component containing poweruptype */
data class BoostComponent(
    var powerupType: PowerupType = PowerupType.SPEED
) : Component<BoostComponent> {

    override fun type() = BoostComponent

    companion object : ComponentType<BoostComponent>() {

    }
}
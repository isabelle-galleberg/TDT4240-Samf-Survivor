package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

data class BoostComponent(
    var type: PowerupType = PowerupType.BOMB
) : Component<BoostComponent> {

    override fun type() = BoostComponent

    companion object : ComponentType<BoostComponent>() {

    }
}
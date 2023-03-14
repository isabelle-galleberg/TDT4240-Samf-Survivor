package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.badlogic.ashley.core.Component
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

class BoostComponent : Component{
    // TODO: should replace BOMB with input type
    public val type = PowerupType.BOMB
}
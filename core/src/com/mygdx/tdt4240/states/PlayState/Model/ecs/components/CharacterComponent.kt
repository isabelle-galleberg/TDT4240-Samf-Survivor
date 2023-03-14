package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.badlogic.ashley.core.Component

class CharacterComponent() : Component {
    // TODO: should replace direction with an input
    public val direction:Pair<Int, Int> = Pair(0, 0)
    public val speed = 5
    public val lives = 3
    public val fireLength = 3
}
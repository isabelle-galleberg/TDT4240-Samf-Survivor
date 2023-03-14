package com.mygdx.tdt4240.states.PlayState.Model.ecs.components

import com.badlogic.ashley.core.Component

class SpriteComponent : Component{
    // TODO: sprite is a picture
    //public val Sprite =
    public val position:Pair<Int, Int> = Pair(0, 0)
}
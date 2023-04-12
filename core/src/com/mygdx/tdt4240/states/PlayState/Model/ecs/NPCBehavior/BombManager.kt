package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory

/* Manages action when bombs are set */
class BombManager : Observer{
    init {
        BombFactory.attach(this)
    }
    override fun update(bombPos: Pair<Int, Int>) {
        NPCBehavior.avoidBomb(bombPos)
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

interface Observer {
    fun update(bombPos: Pair<Int, Int>)
}
package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.PlayerFactory

object Player {
    private var player: Entity? = null

    fun createPlayer(world: World, position: Pair<Int, Int>): Entity? {
        player = PlayerFactory.createPlayer(world, position)

        return player
    }

    fun getPlayer(): Entity? {
        if (player != null) {
            return player
        }
        return null
    }
}
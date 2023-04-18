package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.EntityFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game
import java.util.*
import kotlin.random.Random

/* Set bombs with a random delay between 2 and 10 seconds*/
open class Layer3 : Layer2() {
    fun setBombs(world: World, entity: Entity, board: Array<Array<Entity?>>, game: Game) {
        var randInt = Random.nextInt(0,75)
        if (randInt < 3) {
            var x = NPCSystem.getPosition(entity).first
            var y = NPCSystem.getPosition(entity).second
            board[x][y] = EntityFactory.createBomb(world,x,y)
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    board[x][y] = null
                    game.fire(x,y)
                } }, (2000))
        }
        avoidCollision(entity, board)
    }
}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import java.util.*
import kotlin.random.Random

/* Set bombs with a random delay between 2 and 10 seconds*/
open class Layer4 : Layer3() {
    fun setBombs(world: World) {
        //TO DO: Stop this loop when game is over
        while(true){
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    BombFactory.createBomb(world, NPCSystem.getPosition().first, NPCSystem.getPosition().second )
                }
            }, Random.nextLong(2000,10000))
        }
    }
}
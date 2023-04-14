package com.mygdx.tdt4240.states.PlayState.Model.ecs.NPCBehavior

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import java.util.*
import kotlin.random.Random

/* Set bombs with a random delay between 2 and 10 seconds*/
open class Layer4 : Layer3() {
    fun setBombs(world: World, entity: Entity) {
        //TO DO: Stop this loop when game is over
        while(true){
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    var randomNPC = NPCSystem.getRandomNPC()
                    BombFactory.createBomb(world, NPCSystem.getPosition(randomNPC).first, NPCSystem.getPosition(randomNPC).second )
                }
            }, Random.nextLong(0,10000))
        }
    }
}
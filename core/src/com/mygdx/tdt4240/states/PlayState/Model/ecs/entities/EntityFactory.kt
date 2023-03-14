package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

/* The abstract factory following the abstract factory pattern for creating entities for gameboard */
object EntityFactory {
    fun createBomb(world: World, position: Pair<Int, Int>): Entity{
        return BombFactory.createBomb( world, position)
    }
    fun createCrate(world: World, position: Pair<Int, Int>): Entity {
        return CrateFactory.createCrate(world,position)
    }

    fun createFire(world: World, position: Pair<Int, Int>): Entity {
        return FireFactory.createFire(world, position)
    }

    fun createNPC(world: World, position: Pair<Int, Int>): Entity {
        return NPCFactory.createNPC(world,position)
    }

    fun createPlayer(world: World, position: Pair<Int, Int>): Entity {
        return PlayerFactory.createPlayer(world, position)
    }

    fun createPowerup(world: World,position: Pair<Int, Int>, powerupType: PowerupType): Entity {
        return PowerupFactory.createPowerup(world,position,powerupType)
    }

    fun createWall(world: World, position: Pair<Int, Int>): Entity {
        return WallFactory.createWall(world,position)
    }

}
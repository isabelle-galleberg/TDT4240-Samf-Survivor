package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType

/* The abstract factory following the abstract factory pattern for creating entities for gameboard */
object EntityFactory {
    fun createBomb(world: World, x: Int, y: Int): Entity{
        return BombFactory.createBomb( world, x,y)
    }
    fun createCrate(world: World, x: Int, y: Int): Entity {
        return CrateFactory.createCrate(world,x,y)
    }

    fun createFire(world: World,x: Int, y: Int): Entity {
        return FireFactory.createFire(world, x,y)
    }

    fun createNPC(world: World, x: Int, y: Int): Entity {
        return NPCFactory.createNPC(world,x,y)
    }

    fun createPlayer(world: World, x: Int, y: Int): Entity {
        return PlayerFactory.createPlayer(world, x,y)
    }

    fun createPowerup(world: World,x: Int, y: Int, powerupType: PowerupType): Entity {
        return PowerupFactory.createPowerup(world,x,y,powerupType)
    }

    fun createWall(world: World, x: Int, y: Int): Entity {
        return WallFactory.createWall(world,x,y)
    }

}
package com.mygdx.tdt4240.states.PlayState.Model.ecs.entities

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerUpType

/**
 * The abstract factory following the abstract factory pattern for creating entities for gameboard
 */
object EntityFactory {
    fun createBomb(world: World): Entity{
        return BombFactory.createBomb( world)
    }
    fun createCrate(world: World): Entity {
        return CrateFactory.createCrate(world)
    }

    fun createFire(world: World): Entity {
        return FireFactory.createFire(world)
    }

    fun createNPC(world: World, x: Int, y: Int): Entity {
        return NPCFactory.createNPC(world,x,y)
    }

    fun createPlayer(world: World, x: Int, y: Int): Entity {
        return PlayerFactory.createPlayer(world, x,y)
    }

    fun createPowerUp(world: World, powerUpType: PowerUpType): Entity {
        return PowerUpFactory.createPowerUp(world,powerUpType)
    }

    fun createWall(world: World): Entity {
        return WallFactory.createWall(world)
    }

}
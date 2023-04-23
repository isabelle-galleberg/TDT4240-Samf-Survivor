package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.logic.NPCBehavior.NPCBehavior
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.ScoreSystem.remove
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import com.mygdx.tdt4240.utils.Constants
import com.mygdx.tdt4240.utils.Globals
import java.util.*
import kotlin.random.Random

/**
 * Handles the logic for the game.
 *
 * @param world The world is the container for entities, components, systems and families
 */
class PlayLogic (private val world: World) { //set number of NPCs default to 1
    private var board = Array(9) { arrayOfNulls<Entity>(9) }
    private var npcNum = 1 // Number of NPCs
    private var player:Entity? = null
    private var npcList = mutableListOf<Entity>()
    private var npcMove = 0 //Count times npcmove-function is called
    private var playerMove = 0 //Count times playermove-function is called
    private var timer = Timer()
    private var timerTasks = mutableListOf<TimerTask>()

    init {
        player = EntityFactory.createPlayer(world,0,8)
        for (i in 0 until npcNum) {
            npcList.add(EntityFactory.createNPC(world,8,0+i))
        }
        initBoard()
        Globals.newGame = false
    }

    fun dispose() {
        timerTasks.forEach{ t -> t.cancel() } // Cancel all ongoing timertasks
        world.forEach { e -> e.remove() } // Remove all entities from world
    }

    fun getBoard(i : Int, j : Int) : Entity? {
        return board[i][j]
    }

    private fun initBoard(): Array<Array<Entity?>> {
        board = Array(9) { arrayOfNulls(9) }
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i % 2 != 0 && j % 2 != 0) {
                    board[i][j] = EntityFactory.createWall(world) //Wall
                } else if (i in 1..7) {
                    // Set crates in random positions
                    val randInt = Random.nextInt(0, 2)
                    if (randInt == 0) {
                        board[i][j] = EntityFactory.createCrate(world) //Crate
                    }
                }
            }
        }
        return board
    }

    fun movePlayer(direction: DirectionType) {
        // Only move player if counter is at speed of player
        if(playerMove < CharacterSystem.getSpeed(player)) {
            playerMove++
            return
        }
        val x = CharacterSystem.getPosition(player).first
        val y = CharacterSystem.getPosition(player).second
        if (direction == DirectionType.DOWN) {
            if (y-1 < 0 || ObstacleSystem.contains(board[x][y-1])) {
                return
            } else if(PowerupSystem.contains(board[x][y-1]))
            {
                powerUp(board[x][y-1])
                board[x][y-1] = null
            }
            CharacterSystem.setPosition(player,x,y-1)
        } else if (direction == DirectionType.UP) {
            if (y+1 > 8 || ObstacleSystem.contains(board[x][y+1])) {
                return
            }else if(PowerupSystem.contains(board[x][y+1]))
            {
                powerUp(board[x][y+1])
                board[x][y+1] = null
            }
            CharacterSystem.setPosition(player,x, y+1)
        } else if (direction == DirectionType.RIGHT) {
            if (x+1 > 8 || ObstacleSystem.contains(board[x+1][y])) {
                return
            }else if(PowerupSystem.contains(board[x+1][y]))
            {
                powerUp(board[x+1][y])
                board[x+1][y] = null
            }
            CharacterSystem.setPosition(player,x+1,y)
        } else if (direction == DirectionType.LEFT) {
            if (x-1 < 0 || ObstacleSystem.contains(board[x-1][y])) {
                return
            }else if(PowerupSystem.contains(board[x-1][y]))
            {
                powerUp(board[x-1][y])
                board[x-1][y] = null
            }
            CharacterSystem.setPosition(player,x-1,y)
        }
        playerMove = 0
    }

    /*function for when player places bomb*/
    fun playerPlaceBomb() {
        placeBomb(player)
    }

    fun placeBomb(entity: Entity?) {
        val x = CharacterSystem.getPosition(entity).first
        val y = CharacterSystem.getPosition(entity).second
        board[x][y] = EntityFactory.createBomb(world)
        val t: TimerTask = object : TimerTask() {
            override fun run() {
                board[x][y]?.remove()
                board[x][y] = null
                fire(entity,x,y)
                timerTasks.remove(this)
            }
        }
        timerTasks.add(t)
        timer.schedule(t, LifeSystem.getLifeTime(board[x][y]))
    }

    fun fire(entity: Entity?,x: Int, y:Int) {
        val fireLength = CharacterSystem.getFireLength(entity)
        val fireCoordinates = mutableListOf<Pair<Int,Int>>()
        fireCoordinates.add(Pair(x,y))

        //Fire right
        for (i in 1 until fireLength) {
            if (x+i > 8) {
                break
            }
            if (ObstacleSystem.contains(board[x+i][y])) {
                if (!ObstacleSystem.isWall(board[x+i][y])) {
                    fireCoordinates.add(Pair(x+i,y))
                }
                break
            }
            fireCoordinates.add(Pair(x+i,y))
        }
        //Fire left
        for (i in 1 until fireLength) {
            if (x-i < 0) {
                break
            }
            if (ObstacleSystem.contains(board[x-i][y])) {
                if (!ObstacleSystem.isWall(board[x-i][y])) {
                    fireCoordinates.add(Pair(x-i,y))

                }
                break
            }
            fireCoordinates.add(Pair(x-i,y))
        }
        //Fire down
        for (i in 1 until fireLength) {
            if (y-i < 0) {
                break
            }
            if (ObstacleSystem.contains(board[x][y-i])) {
                if (!ObstacleSystem.isWall(board[x][y-i])) {
                    fireCoordinates.add(Pair(x,y-i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y-i))
        }
        //Fire up
        for (i in 1 until fireLength) {
            if (y+i > 8) {
                break
            }
            if (ObstacleSystem.contains(board[x][y+i])) {
                if (!ObstacleSystem.isWall(board[x][y+i])) {
                    fireCoordinates.add(Pair(x,y+i))
                }
                break
            }
            fireCoordinates.add(Pair(x,y+i))
        }

        // Set up fireentities
        for (cor in fireCoordinates) {
            board[cor.first][cor.second]?.remove()
            board[cor.first][cor.second] = EntityFactory.createFire(world)
        }

        // Reduce lives of characters in fire
        if (fireCoordinates.contains(CharacterSystem.getPosition(player))) {
            CharacterSystem.reduceLives(player)
        }

        val deadNPCs = mutableListOf<Entity>()
        npcList.forEach { npc ->
            if (fireCoordinates.contains(CharacterSystem.getPosition(npc))) {
                CharacterSystem.reduceLives(npc)
                if(CharacterSystem.getLives(npc) == 0) {
                    deadNPCs.add(npc)
                }
                ScoreSystem.addScore(50)
            }}
        npcList.removeAll(deadNPCs) // Remove dead NPCss from list of NPCs
        if (npcList.isEmpty()) {
            timerTasks.forEach { t -> t.cancel() }
            return
        }
        deadNPCs.forEach { npc -> npc.remove() }

        val t: TimerTask = object : TimerTask() {
            override fun run() {
                for (cor in fireCoordinates) {
                    board[cor.first][cor.second]?.remove()
                    board[cor.first][cor.second] = null
                    timerTasks.remove(this)

                }
            }
        }
        timerTasks.add(t)
        timer.schedule(t, LifeSystem.getLifeTime(board[x][y]))
    }

    fun spawnPowerUp() {
        val randInt = Random.nextInt(0,700)
        if(randInt > 2) {
            return
        }
        // Random location on board
        var x = Random.nextInt(0, 8)
        var y = Random.nextInt(0, 8)
        while (ObstacleSystem.contains(board[x][y])|| LifeSystem.contains(board[x][y])) {
            x = Random.nextInt(0, 8)
            y = Random.nextInt(0, 8)
        }

        val randomTypes = PowerupType.values().toList().shuffled()
        board[x][y] = EntityFactory.createPowerUp(world, randomTypes.first())
        }


private fun powerUp(entity: Entity?) {
    val powerUp = PowerupSystem.getPowerUpType(entity)

    if (powerUp == PowerupType.POINTS) {
        ScoreSystem.addScore(PowerupType.POINTS.value)
    }
    else if (powerUp == PowerupType.RANGE) {
        CharacterSystem.setFireLength(player, PowerupType.RANGE.value)
        val t: TimerTask = object : TimerTask() {
            override fun run() {
                CharacterSystem.setFireLength(player,Constants.STARTFIRELENGTH)
                timerTasks.remove(this)
            }
        }
        timerTasks.add(t)
        timer.schedule(t, LifeSystem.getLifeTime(entity))

    }

    else if ( powerUp == PowerupType.SPEED) {
            CharacterSystem.setSpeed(player, 20 - PowerupType.SPEED.value)
            val t: TimerTask = object : TimerTask() {
                override fun run() {
                    CharacterSystem.setSpeed(player, Constants.STARTSPEED)
                    timerTasks.remove(this)
                }
            }
            timerTasks.add(t)
            timer.schedule(t, LifeSystem.getLifeTime(entity))
            entity?.remove()
        }
    }

    fun moveNPC() {
        if (npcList.isEmpty()) {
            return
        }
        // Only move NPC if counter is at NPC-speed
        if (npcMove < CharacterSystem.getSpeed(npcList[0])) {
            npcMove += 1
            return
        }
        npcList.forEach {npc ->
            NPCBehavior.npcBehave(npc,board, this)
            val x = CharacterSystem.getPosition(npc).first
            val y = CharacterSystem.getPosition(npc).second
            val direction = CharacterSystem.getDirection(npc)
            if (direction == DirectionType.DOWN) {
                CharacterSystem.setPosition(npc,x,y-1)
            } else if (direction == DirectionType.UP) {
                CharacterSystem.setPosition(npc,x, y+1)
            } else if (direction == DirectionType.RIGHT) {
                CharacterSystem.setPosition(npc,x+1,y)
            } else if (direction == DirectionType.LEFT) {
                CharacterSystem.setPosition(npc,x-1,y)
            }
            npcMove = 0
        }
    }

    fun getPlayerPosition() : Pair<Int,Int> {
        return CharacterSystem.getPosition(player)
    }

    fun getNpcPositions() : Array<Pair<Int,Int>> {
        val pos = Array(npcList.size){Pair(0,0)}
        npcList.forEach {npc -> pos[npcList.indexOf(npc)] = CharacterSystem.getPosition(npc) }
        return pos
    }

    fun getNpcLives() : Array<Int> {
        val lives = Array(npcList.size){0}
        npcList.forEach {npc -> lives[npcList.indexOf(npc)] = CharacterSystem.getLives(npc) }
        return lives
    }

    fun getPlayerLives() : Int {
        return CharacterSystem.getLives(player)
    }

    fun gameWon(): Boolean {
        return CharacterSystem.getLives(player) != 0
    }

    fun isGameOver(): Boolean {
        return CharacterSystem.getLives(player) == 0 || npcList.isEmpty()
    }
}

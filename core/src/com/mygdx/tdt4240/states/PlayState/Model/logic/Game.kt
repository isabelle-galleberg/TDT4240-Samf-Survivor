package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType
import java.util.*


/* Game logic */
class Game (val world: World){

    private val entityFactory = EntityFactory


    private var timer = false;

    private var bombCount = 0;

    private var firePressed = false;



    val board = Array(9) { arrayOfNulls<Entity>(9) }

    fun initBoard() {
        //Player
        board[0][0] = PlayerFactory.createPlayer(world, 0, 0)

        //NPC
        board[0][0] = NPCFactory.createNPC(world, 0, 0)
        for (i in board.indices) {
            for (j in board[i].indices) {
                //Walls
                if (i % 2 != 0 && j % 2 != 0){
                    board[i][j] = WallFactory.createWall(world, i, j)
                }

                //Crates
                //TO DO where crates??
                else if (i == 3) {
                    board[i][j] = CrateFactory.createCrate(world, i, j)
                }
            }
        }
    }

    fun initBoard(arr: Array<Array<Entity?>>) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                print("${arr[i][j]} ")
            }
            println()
        }
    }

    fun drawBoard(arr: Array<Array<Entity?>>, world: World) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {

                //Walls
                if (i == 0 || j == 0 || i == arr.size -1 || j == arr[i].size -1) {
                    arr[i][j] = WallFactory.createWall(world, i,j)

                }

                //Crates
                else
                {
                    arr[i][j] = CrateFactory.createCrate(world, i,j)
                }
            }
        }
    }

    fun drawPlayer(arr: Array<Array<Entity?>>,world: World, x: Int, y: Int) {
        arr[x][y] = PlayerFactory.createPlayer(world, x, y)

    }

    fun initGame() {
        initBoard(board);
        bombCount = 0;
        timer = true;

    }

    fun movePlayerRight() {
       var posX = PlayerSystem.getPosition().first
        var posY = PlayerSystem.getPosition().second
        if(!ObstacleSystem.getPositions().contains(Pair(posX+1,posY))) {
                PlayerSystem.setPositionX(posX +1)
        }
    }

    fun movePlayerLeft() {
        var posX = PlayerSystem.getPosition().first
        var posY = PlayerSystem.getPosition().second
        if(!ObstacleSystem.getPositions().contains(Pair(posX-1,posY))) {
            PlayerSystem.setPositionX(posX -1)
        }
    }

    fun movePlayerUp() {
        var posX = PlayerSystem.getPosition().first
        var posY = PlayerSystem.getPosition().second
        if(!ObstacleSystem.getPositions().contains(Pair(posX,posY+1))) {
            PlayerSystem.setPositionX(posY +1)
        }
    }

    fun movePlayerDown() {
        var posX = PlayerSystem.getPosition().first
        var posY = PlayerSystem.getPosition().second
        if(!ObstacleSystem.getPositions().contains(Pair(posX,posY-1))) {
            PlayerSystem.setPositionX(posY -1)
        }
    }


    fun getBombs(): Int {
        if(PlayerSystem.getPosition() == BombSystem.getPosition()) {
            bombCount += 1;
        }
        return bombCount;

    }

    fun placeBombs(world: World,x:Int, y:Int) {
        if(firePressed && bombCount > 0 && !ObstacleSystem.getPositions().contains(Pair(x, y))) {
            BombSystem.dropBomb(world,x,y);
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    FireFactory.createFire(world, x+3, y+3) //fix range
                }
            }, 2000)
        }
    }



    fun randomSpawn() {
        var randomTypes = PowerupType.values().toList().shuffled()
        var powerupPositions: MutableList<Pair<Int,Int>> = mutableListOf()

      //fix

    }



}
fun main() {
    val world = world {}
    val b = Game(world)
    val g = b.board
    b.initGame()

    b.drawBoard(g, world)
    b.drawPlayer(g,world,1,1)

    print(b.initBoard(g));



}
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
class Game {

    private val entityFactory = EntityFactory

    private var gameOver = false;
    private var gameWon = false;

    private var score = 0

    private var timer = false;

    private var bombCount = 0;

    private var firePressed = false;



    val grid = Array(9) { arrayOfNulls<Entity>(9) }

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
        initBoard(grid);
        bombCount = 0;
        timer = true;
    }

    fun movePlayer() {
        var playerPosition = PlayerSystem.getPosition();

        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && !ObstacleSystem.getPositions().contains(Pair(playerPosition.first + 1, playerPosition.second ))) {
            PlayerSystem.setDirection(DirectionType.RIGHT)
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && !ObstacleSystem.getPositions().contains(Pair(playerPosition.first - 1, playerPosition.second ))) {
            PlayerSystem.setDirection(DirectionType.LEFT)
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) &&  !ObstacleSystem.getPositions().contains(Pair(playerPosition.first, playerPosition.second + 1 ))) {
            PlayerSystem.setDirection(DirectionType.UP)
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) &&  !ObstacleSystem.getPositions().contains(Pair(playerPosition.first, playerPosition.second - 1))) {
            PlayerSystem.setDirection(DirectionType.DOWN)
        }
        else {
            PlayerSystem.setDirection(DirectionType.NONE)

        }

    }

    fun move(world: World, x:Int, y: Int) {
        drawPlayer(grid, world, x,y)

    }

    fun updateBoard() {

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


    fun isGameOver(entity: Entity): Int {
        if(NPCSystem.getLives(entity) == 0) {
            gameWon = true;
            score = PlayerSystem.getLives() * 250 // * tid igjen på timer
        }
        if(timer) {
            gameOver = true;
            score = 0;

        }
        return score
    }



}
fun main() {
    val b = Game()
    val g = b.grid
    val world = world {}
    b.initGame()

    b.drawBoard(g, world)
    b.drawPlayer(g,world,1,1)

    print(b.initBoard(g));



}
package com.mygdx.tdt4240.states.PlayState.Model.logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.*
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent



class Player {

    //Touch functions
    private var rightPressed = false;
    private var leftPressed = false;
    private var upPressed = false;
    private var downPressed = false;

    private var gameOver = false;

    val world = world {}

    val entity = world.entity {
        it += CharacterComponent(0,0,5, 3)
    }

    fun changePos() {
        with(world) {
            if (rightPressed) {
                entity[CharacterComponent].x += entity[CharacterComponent].speed

            }
             if (leftPressed) {
                 entity[CharacterComponent].x -= entity[CharacterComponent].speed

             }
            if (upPressed) {
                entity[CharacterComponent].y += entity[CharacterComponent].speed

            }
            if (downPressed) {
                entity[CharacterComponent].y -= entity[CharacterComponent].speed

            }

        }

    }

    fun lives(isHit: Boolean): Int {
        with(world) {
            if(isHit) {
                entity[CharacterComponent].lives -= 1;
            }
           return entity[CharacterComponent].lives
        }

    }

    fun isGameOver() {
        with(world){
            if(entity[CharacterComponent].lives == 0) {
                gameOver = true;

            }
        }
    }

}
fun main(){
    val p = Player()
    val world = world {  }
    val entity = world.entity {
        it += CharacterComponent(0,0,5, 3)
    }
    p.lives(true);
    with(world) {
        print(entity[CharacterComponent].lives)

    }




}
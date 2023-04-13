package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent


/* System for the player and NPC*/
class PlayerSystem : IteratingSystem(
    family { all(CharacterComponent) }
) {
    //Touch functions
    private var rightPressed = false;
    private var leftPressed = false;
    private var upPressed = false;
    private var downPressed = false;

    private var firePressed = false;

    private var gameOver = false;
    private var isHit = false;


/*
    fun changePos(entity: Entity) {
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

    }*/
    fun lives(entity: Entity): Int {
        if(isHit) {
                entity[CharacterComponent].lives -= 1;
            }
        return entity[CharacterComponent].lives
        //fix, skriv kode for når isHit blir true.
    }

    fun powerUps(entity: Entity) {
       entity[CharacterComponent].speed += entity[BoostComponent].boostSpeed.value
        entity[CharacterComponent].fireLength +=  entity[BoostComponent].bombRange.value

        entity[ScoreComponent].score += entity[BoostComponent].points.value




    }




    override fun onTickEntity(entity: Entity) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            entity[SpriteComponent].x += entity[CharacterComponent].speed

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            entity[SpriteComponent].x -= entity[CharacterComponent].speed

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            entity[SpriteComponent].y += entity[CharacterComponent].speed

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            entity[SpriteComponent].y -= entity[CharacterComponent].speed

        }

        if(entity[CharacterComponent].lives == 0) {
            gameOver = true;
        }



    }
}
fun main() {
    var p = PlayerSystem()

}
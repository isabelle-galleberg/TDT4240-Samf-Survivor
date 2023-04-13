package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.github.quillraven.fleks.world
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ScoreComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.entities.BombFactory
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem.get
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.DirectionType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.types.PowerupType


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

    fun getDirection(): DirectionType {
        return family.first().get(CharacterComponent).direction
    }

    fun lives(entity: Entity): Int {
        if(isHit) {
                entity[CharacterComponent].lives -= 1;
            }
        return entity[CharacterComponent].lives
        //fix, skriv kode for når isHit blir true.
    }

    fun powerUps(entity: Entity, type: PowerupType) {

        if(type == PowerupType.POINTS) {
            entity[ScoreComponent].score += entity[BoostComponent].points.value

        }
        if (type == PowerupType.RANGE) {
            entity[CharacterComponent].fireLength +=  entity[BoostComponent].bombRange.value
        }

        if(type == PowerupType.SPEED) {
            entity[CharacterComponent].speed += entity[BoostComponent].boostSpeed.value

        }






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
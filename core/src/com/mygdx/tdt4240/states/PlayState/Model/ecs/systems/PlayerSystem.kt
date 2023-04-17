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
object PlayerSystem : IteratingSystem(
    family { all(ScoreComponent) }
) {
    private var isHit = false;

    fun getDirection(): DirectionType {
        return family.first()[CharacterComponent].direction
    }

    fun setDirection(direction: DirectionType) {
        family.first()[CharacterComponent].changeDirection(direction)
    }

    fun getPosition():Pair<Int,Int> {
        return Pair(family.first()[SpriteComponent].x, family.first()[SpriteComponent].y)
    }

    fun getLives(): Int {
        return family.first()[CharacterComponent].lives
    }

    fun setspeed(speed: Int) {
        family.first()[CharacterComponent].changeSpeed(speed)
    }

    fun setPositionX(x:Int) {
        family.first()[SpriteComponent].changePositionX(x);
    }
    fun setPositionY(y:Int) {
        family.first()[SpriteComponent].changePositionY(y);
    }
    fun reduceLives() {
        family.first()[CharacterComponent].reduceLives()
    }

    fun getScore():Int {
        return family.first()[ScoreComponent].score

    }

    fun setScore(score: Int) {
        family.first()[ScoreComponent].changeScore(score)
    }

    override fun onTickEntity(entity: Entity) {

    }
}

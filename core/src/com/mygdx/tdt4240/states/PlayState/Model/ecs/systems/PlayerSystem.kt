package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.badlogic.gdx.Gdx
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.badlogic.gdx.Input
import com.mygdx.tdt4240.states.PlayState.Model.logic.Player

/* System for the player and NPC*/
class PlayerSystem : IteratingSystem(
    family { all(CharacterComponent) }
) {
    var player = Player.getPlayer()

    override fun onTickEntity(entity: Entity) {
        if (Gdx.input.isButtonJustPressed(Input.Keys.DOWN)) {
            // TODO: (Legge til bevegelse)
            return
        }

        if (Gdx.input.isButtonJustPressed(Input.Keys.RIGHT)) {
            // TODO: (Legge til bevegelse)
            return
        }

        if (Gdx.input.isButtonJustPressed(Input.Keys.UP)) {
            // TODO: (Legge til bevegelse)
            return
        }

        if (Gdx.input.isButtonJustPressed(Input.Keys.DOWN)) {
            // TODO: (Legge til bevegelse)
            return
        }
        TODO("Funksjon som oppdaterer alle spillerne (f.eks. sprites og posisjon")
    }
}
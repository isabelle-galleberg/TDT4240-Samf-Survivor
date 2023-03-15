package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
/* System for the player and NPC*/
class PlayerSystem : IteratingSystem(
    family { all(CharacterComponent) }
) {

    override fun onTickEntity(entity: Entity) {
        TODO("Funksjon som oppdaterer alle spillerne (f.eks. sprites og posisjon")
    }
}
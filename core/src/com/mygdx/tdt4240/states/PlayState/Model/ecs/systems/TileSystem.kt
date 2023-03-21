package com.mygdx.tdt4240.states.PlayState.Model.ecs.systems

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BoostComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.CharacterComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.ObservableComponent
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.SpriteComponent

/* System for wall, crate and fire*/
class TileSystem : IteratingSystem(
    World.family { all(SpriteComponent).none(CharacterComponent, ObservableComponent, BoostComponent) }
) {

    override fun onTickEntity(entity: Entity) {
        TODO("Funksjon som oppdaterer alle spillerne (f.eks. sprites og posisjon")
    }
}
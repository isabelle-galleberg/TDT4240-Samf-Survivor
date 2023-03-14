package com.mygdx.tdt4240.states.PlayState.Model.ecs.components
import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

data class BotBehaviorComponent(
    //TODO: create botbehaviorclass and add here
    //var botBehavior: BotBehavior = BotBehavior
    var life: Int = 5 //remove this
) : Component<BotBehaviorComponent> {

    override fun type() = BotBehaviorComponent

    companion object : ComponentType<BotBehaviorComponent>()
}
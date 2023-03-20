package com.mygdx.tdt4240.states.PlayState.Model.ecs.components
import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.mygdx.tdt4240.states.PlayState.Model.ecs.components.BotBehavior.BotBehavior

data class BotBehaviorComponent(
    var botBehavior: BotBehavior = BotBehavior,
    var life: Int = 5 //remove this
) : Component<BotBehaviorComponent> {

    override fun type() = BotBehaviorComponent

    companion object : ComponentType<BotBehaviorComponent>()
}
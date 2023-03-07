package com.mygdx.tdt4240.states
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainMenuState(
    stateManager: StateManager
) : State(stateManager){

    private val logo = Texture("logo.png")
    private val titleText = BitmapFont()

    override fun update(deltaTime: Float) {
        // TODO
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        titleText.draw(sprites, "This is the main menu", 20f, 200f)
        sprites.draw(logo, 0f, 300f)
        sprites.end()
    }

    override fun dispose() {
        logo.dispose()
    }
}
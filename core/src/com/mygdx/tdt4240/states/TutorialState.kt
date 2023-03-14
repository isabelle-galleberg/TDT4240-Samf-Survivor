package com.mygdx.tdt4240.states
import com.mygdx.tdt4240.sprites.Common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch



class TutorialState(stateManager: StateManager) : State(stateManager) {
      private val titleText = Common().titleText

    override fun update(deltaTime: Float) {

    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        titleText.draw(sprites, "This is the tutorial view", 20f, 200f)
        //sprites.draw(logo, 0f, 300f)
        Common().createLogo(sprites)
        sprites.end()
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }


}
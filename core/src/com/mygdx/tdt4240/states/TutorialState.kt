package com.mygdx.tdt4240.states
import com.mygdx.tdt4240.sprites.Common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.utils.Constants


class TutorialState(stateManager: StateManager) : State(stateManager) {
    private val titleText = Common().titleText
    val wintex=Common().winTex
    val logo=Common().logoSprite
    val samf=Sprite(Texture("samfundet.png"), Constants.GAME_WIDTH,Constants.GAME_HEIGHT)


    override fun update(deltaTime: Float) {

    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        logo.draw(sb)
        logo.setPosition(0f,Constants.GAME_HEIGHT.toFloat()-(logo.height))
        wintex.draw(sb)
        wintex.setPosition(Constants.GAME_WIDTH.toFloat()/10, (Constants.GAME_HEIGHT.toFloat()-logo.height)-400f)
        //samf.draw(sb)
        //samf.setPosition(0f,0f)

        //titleText.draw(sb, "This is the tutorial view", 20f, 100f)
        sb.end()
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }


}
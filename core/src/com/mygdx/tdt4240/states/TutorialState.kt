package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.mygdx.tdt4240.sprites.Common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.mygdx.tdt4240.utils.Constants


class TutorialState(stateManager: StateManager) : State(stateManager) {
    private val titleText = Common().titleText
    val ws=Common().windowSprite
    val logo=Common().logoTexture
    val samf=Common().samfTexture

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val nextButton = Common().createSmallButton("Next")
    private val backButton=Common().createSmallButton("Back")

    init {
        nextButton.setPosition((Constants.GAME_WIDTH.toFloat() / 5)*4 - nextButton.width / 2, 20f)
        backButton.setPosition((Constants.GAME_WIDTH.toFloat() / 5) - nextButton.width / 2, 20f)
        stage.addActor(nextButton)
        stage.addActor(backButton)
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(samf,0f,0f,Constants.GAME_WIDTH.toFloat(),Constants.GAME_HEIGHT.toFloat())
        sb.draw(logo,Constants.GAME_WIDTH.toFloat()/2-logo.width/2,Constants.GAME_HEIGHT.toFloat()-logo.height)
        ws.draw(sb)
        ws.setCenter(Constants.GAME_WIDTH.toFloat()/2,Constants.GAME_HEIGHT.toFloat()/3)
        //titleText.draw(sb, "This is the tutorial view", 20f, 100f)
        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }


}
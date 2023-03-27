package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.mygdx.tdt4240.sprites.Buttons

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.TUTORIAL_HEIGHT
import com.mygdx.tdt4240.utils.Constants.TUTORIAL_WIDTH


/**
 * State for the tutorial.
 * @param stateManager Manager of all game states.
 */

class TutorialState(stateManager: StateManager) : State(stateManager) {
    private val logo = Texture("logo.png")
    private val background = Texture("samfundet.png")
    private val stage = Stage()
    private val nextButton = Buttons().createSmallButton("Next")
    private val backButton = Buttons().createSmallButton("Back")
    private val pixmap = Pixmap(TUTORIAL_WIDTH, TUTORIAL_HEIGHT, Pixmap.Format.RGBA8888)
    private val tutorialWindow: Texture


    init {
        pixmap.setColor(128F, 0f, 0f, 1f)
        pixmap.fillRectangle(0, 0, TUTORIAL_WIDTH, TUTORIAL_HEIGHT)
        tutorialWindow = Texture(pixmap)
        nextButton.setPosition((GAME_WIDTH / 5)*4 - nextButton.width / 2, 20f)
        backButton.setPosition((GAME_WIDTH / 5) - nextButton.width / 2, 20f)
        stage.addActor(nextButton)
        stage.addActor(backButton)
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(background,0f,0f,GAME_WIDTH,GAME_HEIGHT)
        sprites.draw(logo,GAME_WIDTH/2-logo.width/2,GAME_HEIGHT-logo.height)
        sprites.draw(tutorialWindow,GAME_WIDTH/2-tutorialWindow.width/2,0f)
        sprites.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        logo.dispose()
        background.dispose()
        pixmap.dispose()
        tutorialWindow.dispose()
    }

}
package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.states.PlayState.View.PlayView
import com.mygdx.tdt4240.utils.Constants

class PauseState(
    stateManager: StateManager, private val api: API
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()

    private var label = Label("You have paused the game.", skin).apply {
        color = Color.BLACK
        setSize(Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT)
        setPosition((Constants.GAME_WIDTH - Constants.INPUT_WIDTH) * 0.5f, Constants.GAME_HEIGHT * 0.65f)
    }

    private val playBtn = TextButton("CONTINUE GAME", skin).apply {
        color = Color.RED
        setSize(Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT)
        setPosition((Constants.GAME_WIDTH - Constants.INPUT_WIDTH) * 0.5f, Constants.GAME_HEIGHT * 0.45f)
    }

    private val tutorialBtn = TextButton("TUTORIAL", skin).apply {
        color = Color.FIREBRICK
        setSize(Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT)
        setPosition((Constants.GAME_WIDTH - Constants.INPUT_WIDTH) * 0.5f, Constants.GAME_HEIGHT * 0.25f)
    }

    private val mainMenuBtn = TextButton("QUIT GAME", skin).apply {
        color = Color.FIREBRICK
        setSize(Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT)
        setPosition((Constants.GAME_WIDTH - Constants.INPUT_WIDTH) * 0.5f, Constants.GAME_HEIGHT * 0.05f)
    }

    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java)

    init {
        textFieldStyle.font.data.setScale(Constants.FONT_SIZE)

        label.setAlignment(Align.center)

        stage.addActor(label)

        stage.addActor(playBtn)
        stage.addActor(tutorialBtn)
        stage.addActor(mainMenuBtn)

        handleClick(playBtn, PlayView(stateManager, api))
        handleClick(tutorialBtn, TutorialState(stateManager))
        handleClick(mainMenuBtn, MainMenuState(stateManager, api))
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    private fun handleClick(button: TextButton, state: State){
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(state)
                Gdx.input.inputProcessor = null
            }
        })
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        logo.draw(sprites)
        sprites.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }


}
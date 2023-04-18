package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.tdt4240.sprites.Window
import com.mygdx.tdt4240.states.PlayState.View.PlayView
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE

class PauseState(
    stateManager: StateManager
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val background = Texture("samfundet.png")
    private val pauseWindow= Window().createPauseWindow()

    private var label = Label("You have paused the game.", skin).apply {
        color = Color.BLACK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.55f)
        setAlignment(Align.center)
    }

    private val playBtn = TextButton("CONTINUE GAME", skin).apply {
        color = Color.RED
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.35f)
    }

    private val mainMenuBtn = TextButton("QUIT GAME", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.15f)
    }

    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java).apply {
        font.data.setScale(FONT_SIZE)
    }

    init {
        stage.addActor(label)
        stage.addActor(playBtn)
        stage.addActor(mainMenuBtn)

        handleClick(playBtn, PlayView(stateManager))
        handleClick(mainMenuBtn, MainMenuState(stateManager))
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
        sprites.draw(background,0f,0f, GAME_WIDTH, GAME_HEIGHT)
        pauseWindow.draw(sprites)
        sprites.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
        background.dispose()
    }


}
package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.tdt4240.states.PlayState.View.PlayView
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Globals
import com.mygdx.tdt4240.utils.Globals.soundOn

/**
 * State for the screen shown when the game is paused
 *
 * @param stateManager The state manager
 */
class PauseState(
    stateManager: StateManager
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val background = Texture("samfundet.png")
    private val pixmap = Pixmap((GAME_WIDTH * 0.7).toInt(), (GAME_HEIGHT * 0.75).toInt(), Pixmap.Format.RGBA8888).apply {
        setColor(1F, 1f, 1f, 1f)
        fillRectangle(0, 0, (GAME_WIDTH * 0.7).toInt(), (GAME_HEIGHT * 0.75).toInt())
    }
    private val whiteBackground = Sprite(Texture(pixmap)).apply {
        setPosition((GAME_WIDTH-((GAME_WIDTH /10).toInt()*7))/2, 0f)
    }
    private var label = Label("You have paused the game.", skin).apply {
        color = Color.BLACK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.60f)
        setAlignment(Align.center)
    }
    private val playBtn = TextButton("CONTINUE GAME", skin).apply {
        color = Color.RED
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.45f)
    }
    private val soundBtn = TextButton("SOUND: ON", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.25f)
    }
    private val mainMenuBtn = TextButton("QUIT GAME", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.05f)
    }
    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java).apply {
        font.data.setScale(FONT_SIZE)
    }

    init {
        if(!soundOn){
            soundBtn.setText("SOUND: OFF")
        }
        stage.addActor(label)
        stage.addActor(playBtn)
        stage.addActor(soundBtn)
        stage.addActor(mainMenuBtn)

        handleClick(playBtn, PlayView(stateManager))
        handleClick(mainMenuBtn, MainMenuState(stateManager))
        handleSoundClick(soundBtn)
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    private fun handleClick(button: TextButton, state: State){
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (button == mainMenuBtn) {
                    Globals.newGame = true
                }
                stateManager.push(state)
                Gdx.input.inputProcessor = null
            }
        })
    }

    private fun handleSoundClick(button: TextButton){
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                soundOn = !soundOn
                if(soundOn){
                    soundBtn.setText("SOUND: ON")
                } else {
                    soundBtn.setText("SOUND: OFF")
                }
            }
        })
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(background,0f,0f, GAME_WIDTH, GAME_HEIGHT)
        whiteBackground.draw(sprites)
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
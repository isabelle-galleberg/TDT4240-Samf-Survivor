package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH


class MainMenuState(
    stateManager: StateManager, api: API
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()
    private val background = Texture("samfundet.png")

    private val playBtn = TextButton("PLAY", skin).apply {
        color = Color.RED
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition(GAME_WIDTH / 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.65f)
    }
    private val tutorialBtn = TextButton("TUTORIAL", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition(GAME_WIDTH / 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.45f)
    }
    private val highscoreBtn = TextButton("HIGHSCORE", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition(GAME_WIDTH / 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.25f)
    }
    private val logOutBtn = TextButton("LOG OUT", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition(GAME_WIDTH / 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.05f)
    }

    private val textFieldStyle: TextFieldStyle = skin.get(TextFieldStyle::class.java)

    init {
        textFieldStyle.font.data.setScale(FONT_SIZE)
        handlePlay()
        handleTutorial()
        handleHighScore(api)
        handleLogOut()

        stage.addActor(playBtn)
        stage.addActor(tutorialBtn)
        stage.addActor(highscoreBtn)
        stage.addActor(logOutBtn)
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    private fun handlePlay() {
        playBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(PlayState(stateManager))
                println("play button clicked")
                Gdx.input.inputProcessor = null
            }
        })
    }

    private fun handleTutorial() {
        tutorialBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(TutorialState(stateManager))
                Gdx.input.inputProcessor = null
            }
        })
    }

    private fun handleHighScore(api: API) {
        highscoreBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(HighScoreListState(stateManager,api))
                Gdx.input.inputProcessor = null
            }
        })
    }

    private fun handleLogOut() {
        logOutBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(LogInState(stateManager))
                println("log out button clicked")
                Gdx.input.inputProcessor = null
            }
        })
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(background,0f,0f,GAME_WIDTH,GAME_HEIGHT)
        logo.draw(sprites)
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
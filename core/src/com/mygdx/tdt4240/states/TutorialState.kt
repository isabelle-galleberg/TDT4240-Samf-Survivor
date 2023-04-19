package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.utils.Constants
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH


class TutorialState(
    stateManager: StateManager
) : State(stateManager) {

    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val stage = Stage()
    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java).apply {
        font.data.setScale(Constants.FONT_SIZE)
    }

    private val tutorial1 = Texture("tutorial/Tutorial1.png")
    private val tutorial2 = Texture("tutorial/Tutorial2.png")
    private val tutorial3 = Texture("tutorial/Tutorial3.png")
    private val tutorial4 = Texture("tutorial/Tutorial4.png")
    private val tutorial5 = Texture("tutorial/Tutorial5.png")
    private val tutorial6 = Texture("tutorial/Tutorial6.png")
    private val imgArray= arrayOf(Sprite(tutorial1), Sprite(tutorial2), Sprite(tutorial3), Sprite(tutorial4), Sprite(tutorial5), Sprite(tutorial6))
    private var pointer = 0

    private val backBtn = TextButton("BACK", skin).apply{
        color = Color.GRAY
        isDisabled = true
        setSize(Constants.INPUT_WIDTH / 2, Constants.BUTTON_HEIGHT)
        setPosition(GAME_WIDTH * 0.1f, GAME_HEIGHT * 0f)
    }

    private val nextBtn = TextButton("NEXT", skin).apply{
        setSize(Constants.INPUT_WIDTH / 2, Constants.BUTTON_HEIGHT)
        setPosition(GAME_WIDTH * 0.6f, GAME_HEIGHT * 0f)
        color = Color.GREEN
    }

    init {
        stage.addActor(backBtn)
        stage.addActor(nextBtn)
        handleNextButton()
        handleBackButton()
    }

    private fun handleNextButton() {
        nextBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                backBtn.color = Color.RED
                if (pointer < imgArray.lastIndex) {
                    pointer++
                }
                nextBtn.isDisabled = pointer == imgArray.lastIndex
                nextBtn.color = if (pointer == imgArray.lastIndex) Color.GRAY else Color.GREEN
            }
        })
    }

    private fun handleBackButton() {
        backBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                nextBtn.color = Color.GREEN
                if (pointer > 0) {
                    pointer--
                }
                backBtn.isDisabled = pointer == 0
                backBtn.color = if (pointer == 0) Color.GRAY else Color.RED
            }
        })
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
        if (BackBtn().backBtnPressed()) {
            stateManager.push(MainMenuState(stateManager))
        }
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(imgArray[pointer], (GAME_WIDTH - GAME_WIDTH * 0.75f) * 0.5f,
            GAME_HEIGHT * 0.1f, GAME_WIDTH * 0.75f, GAME_HEIGHT * 0.9f)
        sprites.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        tutorial1.dispose()
        tutorial2.dispose()
        tutorial3.dispose()
        tutorial4.dispose()
        tutorial5.dispose()
        tutorial6.dispose()
    }}
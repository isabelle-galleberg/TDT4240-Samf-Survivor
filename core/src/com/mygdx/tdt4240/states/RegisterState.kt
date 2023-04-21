package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.api.API
import com.mygdx.tdt4240.api.User
import com.mygdx.tdt4240.sprites.Alert
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH
import com.mygdx.tdt4240.utils.Globals.currentUser
import com.mygdx.tdt4240.utils.Globals.api

/**
 * State for the register screen
 *
 * @param stateManager The state manager
 */
class RegisterState(
    stateManager: StateManager
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()
    private val backBtn = BackBtn().createBackBtn()

    private val textFieldStyle: TextFieldStyle = skin.get(TextFieldStyle::class.java).apply {
        font.data.setScale(FONT_SIZE)
        cursor.minHeight = INPUT_HEIGHT
        cursor.minWidth = INPUT_WIDTH / 100
    }

    private val username = TextField("", textFieldStyle).apply {
        color = Color.FIREBRICK
        messageText = "Username"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.6f)
    }

    private val password = TextField("", textFieldStyle).apply {
        color = Color.FIREBRICK
        messageText = "Password"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.4f)
        isPasswordMode = true
        setPasswordCharacter('*')
    }

    private val button = TextButton("REGISTER", skin).apply{
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.2f)
    }

    private var errorLabel = Label("", skin).apply {
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0f)
    }

    init {
        stage.addActor(username)
        stage.addActor(password)
        stage.addActor(button)
        handleRegister(api!!)
        handleKeyboard(username)
        handleKeyboard(password)
    }

    private fun handleRegister(api: API) {
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Alert().checkConnectionLost(stage)
                if (username.text == "" || password.text == "") {
                    errorLabel.setText("Please fill in all fields")
                    stage.addActor(errorLabel)
                }
                else if (api.usernameExists(username.text)){
                    errorLabel.setText("Username already exists")
                    stage.addActor(errorLabel)
                }
                else {
                    api.submitUser(User(username.text, password.text, 0))
                    currentUser = username.text
                    stateManager.push(MainMenuState(stateManager))
                    errorLabel.remove()
                }
            }
        })
    }

    private fun handleKeyboard(textField: TextField) {
        textField.addListener(object : InputListener() {
            // close keyboard when enter is pressed
            override fun keyDown(event: InputEvent, keycode: Int): Boolean {
                if (keycode == Input.Keys.ENTER) {
                    Gdx.input.setOnscreenKeyboardVisible(false)
                }
                return false
            }
        })
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
        if (BackBtn().backBtnPressed()) {
            stateManager.push(LoginState(stateManager))
        }
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        logo.draw(sprites)
        backBtn.draw(sprites)
        sprites.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }

}


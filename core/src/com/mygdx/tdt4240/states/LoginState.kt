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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.api.API
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.BUTTON_HEIGHT
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.mygdx.tdt4240.sprites.Alert
import com.mygdx.tdt4240.utils.Globals.currentUser
import com.mygdx.tdt4240.utils.Globals.api

/**
 * State for the login page
 *
 * @param stateManager The state manager
 */
class LoginState(
    stateManager: StateManager
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()

    private val textFieldStyle: TextFieldStyle = skin.get(TextFieldStyle::class.java).apply {
        font.data.setScale(FONT_SIZE)
        cursor.minHeight = INPUT_HEIGHT
        cursor.minWidth = INPUT_WIDTH / 100
    }

    private val username = TextField("", textFieldStyle).apply {
        color = Color.FIREBRICK
        messageText = "Username"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.65f)
    }

    private val password = TextField("", textFieldStyle).apply {
        color = Color.FIREBRICK
        messageText = "Password"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.45f)
        isPasswordMode = true
        setPasswordCharacter('*')
    }

    private val loginBtn = TextButton("LOG IN", skin).apply{
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, BUTTON_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.3f)
    }

    private val registerBtn = TextButton("CREATE A NEW USER", skin).apply{
        color = Color.GRAY
        setSize(INPUT_WIDTH, BUTTON_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.15f)
    }

    private var errorLabel = Label("", skin).apply {
        color = Color.RED
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0f)
    }


    init {
        handleLogIn(api!!)
        handleRegister()
        handleKeyboard(username)
        handleKeyboard(password)
        stage.addActor(username)
        stage.addActor(password)
        stage.addActor(loginBtn)
        stage.addActor(registerBtn)
    }

    private fun handleLogIn(api: API){
        loginBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (username.text == "" || password.text == "") {
                    errorLabel.setText("Please fill in all fields")
                    stage.addActor(errorLabel)
                }
                else if (!api.userExists(username.text, password.text)) {
                    errorLabel.setText("Incorrect username or password")
                    stage.addActor(errorLabel)
                }
                else {
                    currentUser = username.text
                    stateManager.push(MainMenuState(stateManager))
                    errorLabel.remove()
                }
            }
        })
    }

    private fun handleRegister() {
        registerBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(RegisterState(stateManager))
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
        Alert().checkConnectionLost(stage)
        Gdx.input.inputProcessor = stage
        if (BackBtn().backBtnPressed()) {
            stateManager.push(LoginState(stateManager))
        }
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
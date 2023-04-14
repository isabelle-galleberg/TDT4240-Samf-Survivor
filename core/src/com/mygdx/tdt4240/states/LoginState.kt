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
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.utils.Constants.BUTTON_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH

class LoginState(
    stateManager: StateManager, private val api: API
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()
    private val username = TextField("", skin).apply {
        color = Color.FIREBRICK
        messageText = "Username"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.65f)
    }
    private val password = TextField("", skin).apply {
        color = Color.FIREBRICK
        messageText = "Password"
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.45f)
        isPasswordMode = true
        setPasswordCharacter('*')
    }
    private val loginBtn = TextButton("Log in", skin).apply{
        color = Color.FIREBRICK
        setSize(INPUT_WIDTH, BUTTON_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.3f)

    }
    private val registerBtn = TextButton("Create a new user", skin).apply{
        color = Color.GRAY
        setSize(INPUT_WIDTH, BUTTON_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0.15f)
    }
    private var errorLabel = Label("", skin).apply {
        color = Color.RED
        setSize(INPUT_WIDTH, INPUT_HEIGHT)
        setPosition((GAME_WIDTH - INPUT_WIDTH) * 0.5f, GAME_HEIGHT * 0f)
    }
    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java)


    init {
        textFieldStyle.font.data.setScale(FONT_SIZE)

        handleLogIn(api)
        handleRegister()

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
                    errorLabel.remove()
                    stateManager.push(MainMenuState(stateManager, api, username.text))
                }
            }
        })
    }

    private fun handleRegister() {
        registerBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(RegisterState(stateManager, api))
        }
        })
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
        if (BackBtn().backBtnPressed()) {
            stateManager.push(LoginState(stateManager, api))
        }

    }

    override fun render(sprites: SpriteBatch) {
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()

        sprites.begin()
        logo.draw(sprites)
        sprites.end()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }

}
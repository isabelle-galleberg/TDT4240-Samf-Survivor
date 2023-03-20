package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.firebase.User
import com.mygdx.tdt4240.states.StateManager

/**
 * State for the register form.
 * @param stateManager Manager of all game states.
 * @param api API for communicating with the database.
 */

class RegisterState(
    stateManager: StateManager, api: API
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val username = TextField("", skin)
    private val password = TextField("", skin)
    private val button = TextButton("Register", skin)
    private var usernameLabel = Label("Username", skin)
    private var passwordLabel = Label("Password", skin)
    private var errorLabel = Label("", skin)
    private val textFieldStyle: TextFieldStyle = skin.get(TextFieldStyle::class.java)

    init {
        username.setSize(1000f, 150f)
        password.setSize(1000f, 150f)
        button.setSize(1000f, 150f)
        errorLabel.setSize(1000f, 150f)
        username.setPosition(Gdx.graphics.width.toFloat() / 2 - button.width / 2, 800f)
        password.setPosition(Gdx.graphics.width.toFloat() / 2- button.width / 2, 500f)
        button.setPosition(Gdx.graphics.width.toFloat() / 2 - button.width / 2, 200f)
        usernameLabel.setPosition(1050f, 960f)
        passwordLabel.setPosition(1050f, 660f)
        errorLabel.setPosition(Gdx.graphics.width.toFloat() / 2 - errorLabel.width / 2, 0f)
        textFieldStyle.font.data.setScale(3f)

        password.isPasswordMode = true
        password.setPasswordCharacter('*')

        handleRegister(api)

        stage.addActor(username)
        stage.addActor(password)
        stage.addActor(button)
        stage.addActor(usernameLabel)
        stage.addActor(passwordLabel)
    }

    private fun handleRegister(api: API){
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                
                if (username.text == "" || password.text == "") {
                    errorLabel.setText("Please fill in all fields")
                    stage.addActor(errorLabel)
                }
                else if (api.checkUserExists(username.text)){
                    errorLabel.setText("Username already exists")
                    stage.addActor(errorLabel)
                }
                else {
                    api.submitUser(User(username.text, password.text, 0))
                    errorLabel.remove()
                    stateManager.push(MainMenuState(stateManager))
                }

            }
        })
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
    }

    override fun render(sprites: SpriteBatch) {
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }

}


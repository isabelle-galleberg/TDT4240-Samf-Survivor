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
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.firebase.User
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.utils.Constants.INPUT_HEIGHT
import com.mygdx.tdt4240.utils.Constants.INPUT_WIDTH

/**
 * State for the register form.
 * @param stateManager Manager of all game states.
 * @param api API for communicating with the database.
 */

class RegisterState(
    stateManager: StateManager, private val api: API
) : State(stateManager) {

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val logo = Logo().createLogo()
    private val backBtn = BackBtn().createBackBtn()
    private val username = TextField("", skin).apply {
        messageText = "Username"
    }
    private val password = TextField("", skin).apply {
        messageText = "Password"
    }
    private val button = TextButton("Register", skin)

    private var errorLabel = Label("", skin).apply {
        color = Color.RED
    }
    private val textFieldStyle: TextFieldStyle = skin.get(TextFieldStyle::class.java)


    init {
        username.setSize(INPUT_WIDTH, INPUT_HEIGHT)
        password.setSize(INPUT_WIDTH, INPUT_HEIGHT)
        button.setSize(INPUT_WIDTH, INPUT_HEIGHT)
        errorLabel.setSize(INPUT_WIDTH, INPUT_HEIGHT)
        username.setPosition(GAME_WIDTH / 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.6f)
        password.setPosition(GAME_WIDTH / 2- INPUT_WIDTH / 2, GAME_HEIGHT * 0.4f)
        button.setPosition(GAME_WIDTH/ 2 - INPUT_WIDTH / 2, GAME_HEIGHT * 0.2f)
        errorLabel.setPosition(GAME_WIDTH / 2 - errorLabel.width / 2, 0f)
        textFieldStyle.font.data.setScale(FONT_SIZE)
        password.isPasswordMode = true
        password.setPasswordCharacter('*')

        handleRegister(api)

        stage.addActor(username)
        stage.addActor(password)
        stage.addActor(button)
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
        if (BackBtn().backBtnPressed()) {
            stateManager.push(LoginState(stateManager, api))
        }
    }

    override fun render(sprites: SpriteBatch) {
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()

        sprites.begin()
        logo.draw(sprites)
        backBtn.draw(sprites)
        sprites.end()
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }

}


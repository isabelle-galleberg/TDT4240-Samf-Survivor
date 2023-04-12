package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH


class MainMenuState(
    stateManager: StateManager, api: API
) : State(stateManager){

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))

    private var playBtn: ImageButton? = null
    private var tutorialBtn: ImageButton? = null

    private var highScoreBtn: ImageButton? = null

    private var logOutBtn: ImageButton? = null

    private val logo = Logo().createLogo()
    private val background = Texture("samfundet.png")

    init{
        playBtn = createMenuButton("buttonImages/playBtnImg.png")
        playBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- playBtn!!.width/2,Gdx.graphics.height.toFloat()*3/5)

        tutorialBtn = createMenuButton("buttonImages/tutorialBtnImg.png")
        tutorialBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- tutorialBtn!!.width/2,Gdx.graphics.height.toFloat()*2/5)

        highScoreBtn = createMenuButton("buttonImages/highScoreBtnImg.png")
        highScoreBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- highScoreBtn!!.width/2,Gdx.graphics.height.toFloat()*1/5)

        logOutBtn = createMenuButton("buttonImages/logOutBtnImg.png")
        logOutBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- logOutBtn!!.width/2,Gdx.graphics.height.toFloat()*0/5)

        handlePlay()
        handleTutorial()
        handleHighScore(api)
        handleLogOut()
        stage.addActor(playBtn)
        stage.addActor(tutorialBtn)
        stage.addActor(highScoreBtn)
        stage.addActor(logOutBtn)
    }

    private fun createMenuButton(str: String): ImageButton {

        val mainMenuTexture = Texture(Gdx.files.internal(str))
        val mainMenuTextureRegion = TextureRegion(mainMenuTexture)
        val mainMenuTexRegionDrawable = TextureRegionDrawable(mainMenuTextureRegion)
        val mainMenuBtn = ImageButton(mainMenuTexRegionDrawable)
        mainMenuBtn.setSize(GAME_WIDTH*3/4, GAME_HEIGHT*1/5)
        return mainMenuBtn
    }

    private fun handlePlay(){
        playBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(PlayState(stateManager))
                println("play button clicked")
            }
        })
    }

    private fun handleTutorial(){
        tutorialBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(TutorialState(stateManager))
                println("tutorial button clicked")
            }
        })
    }

    private fun handleHighScore(api: API){
        highScoreBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.push(HighScoreListState(stateManager,api))
                println("high score button clicked")
            }
        })
    }

    private fun handleLogOut(){
        logOutBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(LogInState(stateManager))
                println("log out button clicked")
            }
        })
    }
    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
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
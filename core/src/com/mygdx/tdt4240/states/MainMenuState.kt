package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable


class MainMenuState(
    stateManager: StateManager
) : State(stateManager){

    private val stage = Stage()
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    //private val playBtn = TextButton("Play", skin)
    //private val highScoreBtn = TextButton("Highscore", skin)
    //private val tutorialBtn = TextButton("Tutorial", skin)
    //private val logOutBtn = TextButton("Log Out", skin)
    //private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java)

    private var playTexture: Texture? = null
    private var playTextureRegion: TextureRegion? = null
    private var playTexRegionDrawable: TextureRegionDrawable? = null
    private var playBtn: ImageButton? = null

    private var tutorialTexture: Texture? = null
    private var tutorialTextureRegion: TextureRegion? = null
    private var tutorialTexRegionDrawable: TextureRegionDrawable? = null
    private var tutorialBtn: ImageButton? = null

    private var highScoreTexture: Texture? = null
    private var highScoreTextureRegion: TextureRegion? = null
    private var highScoreTexRegionDrawable: TextureRegionDrawable? = null
    private var highScoreBtn: ImageButton? = null

    private var logOutTexture: Texture? = null
    private var logOutTextureRegion: TextureRegion? = null
    private var logOutTexRegionDrawable: TextureRegionDrawable? = null
    private var logOutBtn: ImageButton? = null

    init{
        //playBtn.setSize(1000f,150f)
        //playBtn.setPosition(Gdx.graphics.width.toFloat()/2-playBtn.width/2,Gdx.graphics.height.toFloat()*3/5)
        //playBtn?.setSize(Gdx.graphics.width.toFloat(),Gdx.graphics.height.toFloat()/10)
        playTexture = Texture(Gdx.files.internal("buttonImages/playBtnImg.png"))
        playTextureRegion = TextureRegion(playTexture)
        playTexRegionDrawable = TextureRegionDrawable(playTextureRegion)
        playBtn = ImageButton(playTexRegionDrawable)
        playBtn!!.setSize(Gdx.graphics.width.toFloat()*3/4,Gdx.graphics.height.toFloat()*1/5)
        playBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- playBtn!!.width/2,Gdx.graphics.height.toFloat()*3/5)

        //tutorialBtn.setSize(1000f,150f)
        //tutorialBtn.setPosition(Gdx.graphics.width.toFloat()/2-tutorialBtn.width/2,Gdx.graphics.height.toFloat()*2/5)
        //tutorialBtn?.setSize(Gdx.graphics.width.toFloat(),Gdx.graphics.height.toFloat()/10)
        tutorialTexture = Texture(Gdx.files.internal("buttonImages/tutorialBtnImg.png"))
        tutorialTextureRegion = TextureRegion(tutorialTexture)
        tutorialTexRegionDrawable = TextureRegionDrawable(tutorialTextureRegion)
        tutorialBtn = ImageButton(tutorialTexRegionDrawable)
        tutorialBtn!!.setSize(Gdx.graphics.width.toFloat()*3/4,Gdx.graphics.height.toFloat()*1/5)
        tutorialBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- tutorialBtn!!.width/2,Gdx.graphics.height.toFloat()*2/5)

        //highScoreBtn.setSize(1000f,150f)
        //highScoreBtn.setPosition(Gdx.graphics.width.toFloat()/2-highScoreBtn.width/2,Gdx.graphics.height.toFloat()*1/5)
        //highScoreBtn?.setSize(Gdx.graphics.width.toFloat(),Gdx.graphics.height.toFloat()/10)
        highScoreTexture = Texture(Gdx.files.internal("buttonImages/highScoreBtnImg.png"))
        highScoreTextureRegion = TextureRegion(highScoreTexture)
        highScoreTexRegionDrawable = TextureRegionDrawable(highScoreTextureRegion)
        highScoreBtn = ImageButton(highScoreTexRegionDrawable)
        highScoreBtn!!.setSize(Gdx.graphics.width.toFloat()*3/4,Gdx.graphics.height.toFloat()*1/5)
        highScoreBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- highScoreBtn!!.width/2,Gdx.graphics.height.toFloat()*1/5)

        //logOutBtn.setSize(1000f,150f)
        //logOutBtn.setPosition(Gdx.graphics.width.toFloat()/2-logOutBtn.width/2,Gdx.graphics.height.toFloat()*0/5)

        //logOutBtn?.setSize(Gdx.graphics.width.toFloat(),Gdx.graphics.height.toFloat()/10)
        logOutTexture = Texture(Gdx.files.internal("buttonImages/logOutBtnImg.png"))
        logOutTextureRegion = TextureRegion(logOutTexture)
        logOutTexRegionDrawable = TextureRegionDrawable(logOutTextureRegion)
        logOutBtn = ImageButton(logOutTexRegionDrawable)
        logOutBtn!!.setSize(Gdx.graphics.width.toFloat()*3/4,Gdx.graphics.height.toFloat()*1/5)
        logOutBtn!!.setPosition(Gdx.graphics.width.toFloat()/2- logOutBtn!!.width/2,Gdx.graphics.height.toFloat()*0/5)

        //textFieldStyle.font.data.setScale(3f)
        handlePlay()
        handleTutorial()
        handleHighScore()
        handleLogOut()
        stage.addActor(playBtn)
        stage.addActor(tutorialBtn)
        stage.addActor(highScoreBtn)
        stage.addActor(logOutBtn)
    }

    private fun handlePlay(){
        playBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(RegisterState(stateManager))
                println("play button clicked")
            }
        })
    }

    private fun handleTutorial(){
        tutorialBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(RegisterState(stateManager))
                println("tutorial button clicked")
            }
        })
    }

    private fun handleHighScore(){
        highScoreBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(RegisterState(stateManager))
                println("high score button clicked")
            }
        })
    }

    private fun handleLogOut(){
        logOutBtn?.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                //stateManager.push(RegisterState(stateManager))
                println("log out button clicked")
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
package com.mygdx.tdt4240.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.mygdx.tdt4240.sprites.Buttons

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.sprites.Window
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

/**
 * State for the tutorial.
 * @param stateManager Manager of all game states.
 */

class TutorialState(stateManager: StateManager) : State(stateManager) {
    private val logo = Logo().createLogo()
    private val background = Texture("samfundet.png")
    private val tutorialWindow=Window().createWindow()

    //#TODO-endre val+array til oppdaterte bilder av spillet
    private val logo2=Texture("logo.png")
    private val imgArray= arrayOf(background,logo2)
    private var pointer=0

    private val stage = Stage()
    private val backBtn = BackBtn().createBackBtn()
    private val nextButton = Buttons().createSmallButton("Next")
    private val backButton = Buttons().createSmallButton("Back")


    init {
        nextButton.setPosition((GAME_WIDTH / 5)*4 - nextButton.width / 2, 20f)
        backButton.setPosition((GAME_WIDTH / 5) - nextButton.width / 2, 20f)
        stage.addActor(nextButton)
        stage.addActor(backButton)
        backButton.isDisabled=true

        handleBackButtonClick()
        handleNextButtonClick()
    }

    private fun handleNextButtonClick(){
        nextButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (pointer==0)
                    backButton.isDisabled = false
                if (pointer<imgArray.size-2) {
                    pointer += 1
                    nextButton.isDisabled = false
                }
                else if (pointer==imgArray.size-2){
                    pointer+=1
                    nextButton.isDisabled = true
                }
                else if (pointer>imgArray.size-2)
                    nextButton.isDisabled = true
            }
        })
    }
    private fun handleBackButtonClick(){
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if(pointer==imgArray.size-1)
                    nextButton.isDisabled = false
                if (pointer>1){
                    pointer-=1
                    backButton.isDisabled = false
                }
                else if (pointer==1){
                    pointer-=1
                    backButton.isDisabled = true
                }
                else if (pointer<1){
                    backButton.isDisabled = true
                }
            } })
    }
    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
        if (BackBtn().backBtnPressed()) {
            stateManager.push(MainMenuState(stateManager))
        }
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(background,0f,0f,GAME_WIDTH,GAME_HEIGHT)
        tutorialWindow.draw(sprites)
        //sprites.draw(tutorialWindow,(GAME_WIDTH- TUTORIAL_WIDTH)/2,0f)
        //sprites.draw(imgArray[pointer], 0f,0f)
        logo.draw(sprites)
        backBtn.draw(sprites)
        sprites.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        background.dispose()
        //tutorialWindow.dispose()
    }

}
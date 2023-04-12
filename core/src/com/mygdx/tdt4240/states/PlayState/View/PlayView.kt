package com.mygdx.tdt4240.states.PlayState.View

import com.mygdx.tdt4240.states.MainMenuState
import com.mygdx.tdt4240.states.State
import com.mygdx.tdt4240.states.StateManager

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
//import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE

import com.mygdx.tdt4240.sprites.PauseBtn
import com.mygdx.tdt4240.sprites.BombBtn
import com.mygdx.tdt4240.sprites.UpBtn
import com.mygdx.tdt4240.sprites.DownBtn
import com.mygdx.tdt4240.sprites.LeftBtn
import com.mygdx.tdt4240.sprites.RightBtn

class PlayView (stateManager: StateManager) : State(stateManager) {

    private var font = BitmapFont()

    private val pauseBtn = PauseBtn().createPauseBtn()
    private val bombBtn = BombBtn().createBombBtn()

    private val upBtn = UpBtn().createUpBtn()
    private val downBtn = DownBtn().createDownBtn()
    private val leftBtn = LeftBtn().createLeftBtn()
    private val rightBtn = RightBtn().createRightBtn()

    private val boardImg = Texture("gameView/board.png")
    private val tileImg = Texture("gameView/tile.png")
    private val wallImg = Texture("gameView/wall.png")

    init {
        font.data.setScale(FONT_SIZE)
    }

    override fun update(deltaTime: Float) {
        if (PauseBtn().pauseBtnPressed()) {
            // need to change to PauseState view
            stateManager.push(MainMenuState(stateManager))
        }
        // add logic for the rest of the buttons
    }
    override fun render(sprites: SpriteBatch) {
        sprites.begin()

        // Pause button
        pauseBtn.draw(sprites)

        // Draw game board
        sprites.draw(boardImg, GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f,  0f, GAME_HEIGHT, GAME_HEIGHT)
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (i % 2 != 0 && j % 2 != 0){
                    sprites.draw(wallImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
                }
                else {
                    sprites.draw(tileImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
                }
            }
        }

        // Button to add bombs on the bord
        bombBtn.draw(sprites)

        // Game controller
        upBtn.draw(sprites)
        downBtn.draw(sprites)
        leftBtn.draw(sprites)
        rightBtn.draw(sprites)

        sprites.flush()
        sprites.end()
    }
    override fun dispose() {
        font.dispose()
    }
}
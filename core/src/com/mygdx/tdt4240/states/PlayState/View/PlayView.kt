package com.mygdx.tdt4240.states.PlayState.View

import com.badlogic.gdx.Gdx.gl
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.mygdx.tdt4240.states.State
import com.mygdx.tdt4240.states.StateManager

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.Game.Companion.sprites
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE

import com.mygdx.tdt4240.sprites.PauseBtn
import com.mygdx.tdt4240.sprites.BombBtn
import com.mygdx.tdt4240.sprites.UpBtn
import com.mygdx.tdt4240.sprites.DownBtn
import com.mygdx.tdt4240.sprites.LeftBtn
import com.mygdx.tdt4240.sprites.RightBtn

import com.mygdx.tdt4240.sprites.LivesDisplay
import com.mygdx.tdt4240.sprites.Player
import com.mygdx.tdt4240.sprites.NPC
import com.mygdx.tdt4240.sprites.Bomb
import com.mygdx.tdt4240.states.MainMenuState
import com.mygdx.tdt4240.states.PauseState
import com.mygdx.tdt4240.states.PlayState.Controller.PlayController
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PlayerSystem
import com.mygdx.tdt4240.states.PlayState.Model.logic.Game

class PlayView (stateManager: StateManager) : State(stateManager) {

    private var font = BitmapFont()

    private val pauseBtn = PauseBtn().createPauseBtn()
    private val bombBtn = BombBtn().createBombBtn()

    private val upBtn = UpBtn().createUpBtn()
    private val downBtn = DownBtn().createDownBtn()
    private val leftBtn = LeftBtn().createLeftBtn()
    private val rightBtn = RightBtn().createRightBtn()


    private val player = Player().createPlayer()
    private val nPC = NPC().createNPC()
    private val bomb = Bomb().createBomb()


    private val boardImg = Texture("gameView/board.png")
    private val tileImg = Texture("gameView/tile.png")
    private val wallImg = Texture("gameView/wall.png")

    private val crateImg = Texture("gameView/crate1.png")

    private val playController = PlayController()
    var uiBoard = playController.drawBoard()

    init {
        font.data.setScale(FONT_SIZE)
    }

    override fun update(deltaTime: Float) {

        playController.updateTime(deltaTime);
        if (PauseBtn().pauseBtnPressed()) {
            // need to change to PauseState view
            //stateManager.push(PauseState(stateManager))
            //stateManager.push(MainMenuState(stateManager))
        } else if (UpBtn().upBtnPressed()) {
            playController.updatePos("UP")
        } else if (DownBtn().downBtnPressed()) {
            playController.updatePos("DOWN")
        } else if (LeftBtn().leftBtnPressed()) {
            playController.updatePos("LEFT")
        } else if (RightBtn().rightBtnPressed()) {
            playController.updatePos("RIGHT")
        }
        else if(BombBtn().bombBtnPressed()) {
            println("BOMB")
            playController.bomb();

        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.begin()

        // Pause button
        pauseBtn.draw(sprites)

        // Draw game board
        uiBoard = playController.drawBoard()

        sprites.draw(boardImg, GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f,  0f, GAME_HEIGHT, GAME_HEIGHT)

        for (i in uiBoard.indices) {
            for (j in 0 until uiBoard[0].size) {
                sprites.draw(tileImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
                if (uiBoard[i][j].equals("wall")) {
                    sprites.draw(wallImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
                } else if (uiBoard[i][j].equals("bomb")) {
                   bomb.setPosition(GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f,GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f)
                    bomb.draw(sprites)
                }
                else if (uiBoard[i][j].equals("crate")) {
                    sprites.draw(crateImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)

                }
            }
        }
      //  Bomb().updatePosition(bomb, PlayerSystem.getPosition().first.toFloat(),PlayerSystem.getPosition().second.toFloat())
        //bomb.draw(sprites)

        //Player and NPC
        Player().updatePosition(player, PlayerSystem.getPosition().first.toFloat(), PlayerSystem.getPosition().second.toFloat())
        player.draw(sprites)

        NPC().updatePosition(nPC, 8.toFloat(), 0.toFloat())
        nPC.draw(sprites)

        // Game controller

        upBtn.draw(sprites)
        downBtn.draw(sprites)
        leftBtn.draw(sprites)
        rightBtn.draw(sprites)
        bombBtn.draw(sprites)

        // Lives display for player and NPC
        LivesDisplay(sprites, PlayerSystem.getLives(),NPCSystem.getLives())

        // Timer - must make it dynamic
        var time = playController.getTime().toString()
        font.setColor(Color.BLACK)
        font.draw(sprites, time, GAME_HEIGHT * 0.4f,  GAME_HEIGHT * 0.92f)

        sprites.flush()
        sprites.end()
    }
    override fun dispose() {
        font.dispose()

    }
}


//for (i in 0 until 9) {
//for (j in 0 until 9) {
//if (i % 2 != 0 && j % 2 != 0){
//sprites.draw(wallImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
//}
//else (i=3){
//sprites.draw(tileImg, GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f, GAME_HEIGHT * 0.1f)
//}
//}
//}
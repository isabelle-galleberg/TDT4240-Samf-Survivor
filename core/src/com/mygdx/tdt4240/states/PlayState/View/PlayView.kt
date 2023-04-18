package com.mygdx.tdt4240.states.PlayState.View

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
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
import com.mygdx.tdt4240.states.State
import com.mygdx.tdt4240.states.StateManager

import com.mygdx.tdt4240.states.PauseState
import com.mygdx.tdt4240.states.PlayState.Controller.PlayController
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.NPCSystem
import com.mygdx.tdt4240.states.PlayState.Model.ecs.systems.PlayerSystem

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

    private val boardFrameImg = Texture("gameView/boardFrame.png")
    private val tileImg = Texture("gameView/tile.png")
    private val wallImg = Texture("gameView/wall.png")
    private val crateImg = Texture("gameView/crate.png")
    private val fireImg = Texture("gameView/fire.png")
    private val bombImg = Texture("gameView/bomb.png")

    private val playController = PlayController()
    private var uiBoard = playController.drawBoard()
    private var gameOver = false

    init {
        font.data.setScale(FONT_SIZE)
    }

    override fun update(deltaTime: Float) {
        playController.updateTime(deltaTime)
        if (PauseBtn().pauseBtnPressed()) {
            stateManager.push(PauseState(stateManager))
        } else if (UpBtn().upBtnPressed()) {
            playController.updatePos("UP")
        } else if (DownBtn().downBtnPressed()) {
            playController.updatePos("DOWN")
        } else if (LeftBtn().leftBtnPressed()) {
            playController.updatePos("LEFT")
        } else if (RightBtn().rightBtnPressed()) {
            playController.updatePos("RIGHT")
        } else if(BombBtn().bombBtnPressed()) {
            playController.bomb()
        }
        playController.updatePosNPC()
    }
    override fun render(sprites: SpriteBatch) {

        // Calculate values to ensure the view to be scalable on different devices
        var screenMiddleWidth = GAME_WIDTH * 0.5f
        if (screenMiddleWidth > GAME_HEIGHT) { screenMiddleWidth = GAME_HEIGHT }

        val boardSize = if (screenMiddleWidth < GAME_HEIGHT){ screenMiddleWidth * 0.9f } else { GAME_HEIGHT * 0.9f }
        val tileSize = boardSize/9
        val boardX = GAME_WIDTH * 0.25f + (screenMiddleWidth-boardSize) * 0.5f
        val boardY = (GAME_HEIGHT-boardSize) * 0.5f

        sprites.begin()
        gameOver = playController.isGameOver()

        if (gameOver) {
            PlayerSystem.resetLives()
            NPCSystem.resetLives()
        }

        pauseBtn.draw(sprites) // Pause button
        uiBoard = playController.drawBoard() // Game board

        sprites.draw(boardFrameImg, GAME_WIDTH * 0.25f,  (GAME_HEIGHT - screenMiddleWidth) * 0.5f, screenMiddleWidth, screenMiddleWidth) // Draw game board frame

        for (i in uiBoard.indices) {
            for (j in 0 until uiBoard[0].size) {
                sprites.draw(tileImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                if (uiBoard[i][j].equals("wall")) {
                    sprites.draw(wallImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                } else if (uiBoard[i][j].equals("crate")) {
                    sprites.draw(crateImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                } else if (uiBoard[i][j].equals("bomb")) {
                    sprites.draw(bombImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                } else if (uiBoard[i][j].equals("fire")) {
                    sprites.draw(fireImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                }/*
                else if (uiBoard[i][j].equals("speed")) {

                }
                else if (uiBoard[i][j].equals("range")) {

                }
                else if (uiBoard[i][j].equals("points")) {

                }*/
            }
        }

        val playerPos = playController.getPlayerPosition()
        Player().updatePosition(player, playerPos.first.toFloat(), playerPos.second.toFloat())
        player.draw(sprites) //Player

        val npcPos = playController.getNPCPosition()
        NPC().updatePosition(nPC, npcPos.first.toFloat(), npcPos.second.toFloat())
        nPC.draw(sprites) //NPC

        upBtn.draw(sprites) // UP button
        downBtn.draw(sprites) // DOWN button
        leftBtn.draw(sprites) // LEFT button
        rightBtn.draw(sprites) // RIGHT button
        bombBtn.draw(sprites) // Bomb button

        LivesDisplay(sprites, PlayerSystem.getLives(), NPCSystem.getLives()) // Lives

        val time = playController.getTime().toString() //Timer
        font.color = Color.BLACK
        font.draw(sprites, time, GAME_WIDTH * 0.05f,  GAME_HEIGHT * 0.92f)

        sprites.flush()
        sprites.end()
    }
    override fun dispose() {
        font.dispose()
    }
}
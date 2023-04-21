package com.mygdx.tdt4240.states.PlayState.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.tdt4240.sprites.*
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.states.*
import com.mygdx.tdt4240.states.PlayState.Controller.PlayController
import com.mygdx.tdt4240.utils.Globals.newGame
import com.mygdx.tdt4240.utils.Globals.api
import com.mygdx.tdt4240.utils.Globals.currentUser
import com.mygdx.tdt4240.utils.Globals.soundOn

class PlayView (stateManager: StateManager) : State(stateManager) {
    private val stage = Stage()
    private var font = BitmapFont()
    private var scoreFont = BitmapFont()

    private val pauseBtn = GameButtons().createPauseBtn()
    private val upBtn = GameButtons().createUpBtn()
    private val downBtn = GameButtons().createDownBtn()
    private val leftBtn = GameButtons().createLeftBtn()
    private val rightBtn = GameButtons().createRightBtn()
    private val bombBtn = GameButtons().createBombBtn()

    private val player = Player().createPlayer()
    private val nPC = NPC().createNPC()

    private val boardFrameImg = Texture("gameView/boardFrame.png")
    private val tileImg = Texture("gameView/tile.png")
    private val wallImg = Texture("gameView/wall.png")
    private val crateImg = Texture("gameView/crate.png")
    private val fireImg = Texture("gameView/fire.png")
    private val bombImg = Texture("gameView/bomb.png")
    private val powerUpSpeedImg = Texture("gameView/powerUpSpeed.png")
    private val powerUpRangeImg = Texture("gameView/powerUpRange.png")
    private val powerUpPointsImg = Texture("gameView/powerUpPoints.png")

    private var playController = PlayController
    private var uiBoard = playController.drawBoard()
    private var gameOver = false

    private var sound: Sound = Gdx.audio.newSound(Gdx.files.internal("data/bombe.mp3"))

    init {
        font.data.setScale(FONT_SIZE)
        scoreFont.data.setScale(FONT_SIZE)
    }

    override fun update(deltaTime: Float) {
        Alert().checkConnectionLost(stage)
        playController.update(deltaTime)
        if (GameButtons().pauseBtnPressed()) {
            stateManager.push(PauseState(stateManager))
        } else if (GameButtons().upBtnPressed()) {
            playController.updatePos("UP")
        } else if (GameButtons().downBtnPressed()) {
            playController.updatePos("DOWN")
        } else if (GameButtons().leftBtnPressed()) {
            playController.updatePos("LEFT")
        } else if (GameButtons().rightBtnPressed()) {
            playController.updatePos("RIGHT")
        } else if(GameButtons().bombBtnPressed()) {
            playController.bomb()
            if(soundOn){
                sound.play(1.0f)
            }
        }

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
            newGame = true
            api!!.updateHighscore(currentUser, playController.finalScore())
            stateManager.push(GameOverState(stateManager,playController.isGameWon(),playController.finalScore()))
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
                }
                else if (uiBoard[i][j].equals("speed")) {
                    sprites.draw(powerUpSpeedImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                }
                else if (uiBoard[i][j].equals("range")) {
                    sprites.draw(powerUpRangeImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                }
                else if (uiBoard[i][j].equals("points")) {
                    sprites.draw(powerUpPointsImg, boardX + i * tileSize, boardY + j * tileSize, tileSize, tileSize)
                }
            }
        }

        val playerPos = playController.getPlayerPosition()
        Player().updatePosition(player, playerPos.first.toFloat(), playerPos.second.toFloat())
        player.draw(sprites) // Player

        val npcPos = playController.getNPCPositions().first()
        NPC().updatePosition(nPC, npcPos.first.toFloat(), npcPos.second.toFloat())
        nPC.draw(sprites) // NPC

        upBtn.draw(sprites) // UP button
        downBtn.draw(sprites) // DOWN button
        leftBtn.draw(sprites) // LEFT button
        rightBtn.draw(sprites) // RIGHT button
        bombBtn.draw(sprites) // Bomb button

        LivesDisplay(sprites, playController.getPlayerLives(), playController.getNPCLives().first()) // Lives


        val time = playController.getTime().toString() //Timer
        font.color = Color.BLACK
        font.draw(sprites, time, GAME_WIDTH * 0.05f,  GAME_HEIGHT * 0.92f)
        scoreFont.color = Color.BLACK
        scoreFont.draw(sprites, "Score: ${playController.currentScore()}", GAME_WIDTH * 0.05f, GAME_HEIGHT * 0.55f)
        sprites.flush()
        sprites.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
    override fun dispose() {
        font.dispose()
        scoreFont.dispose()
        stage.dispose()
    }
}
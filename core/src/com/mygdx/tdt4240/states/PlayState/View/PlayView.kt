package com.mygdx.tdt4240.states.PlayState.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.tdt4240.sprites.*
import com.mygdx.tdt4240.states.*
import com.mygdx.tdt4240.states.PlayState.Controller.PlayController
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Globals.api
import com.mygdx.tdt4240.utils.Globals.currentUser
import com.mygdx.tdt4240.utils.Globals.newGame
import com.mygdx.tdt4240.utils.Globals.soundOn

/**
 * State for the playing the game
 *
 * @param stateManager The state manager
 */
class PlayView (stateManager: StateManager) : State(stateManager) {
    private val stage = Stage()
    private var font = BitmapFont().apply {
       color = Color.BLACK
    }
    private val pauseBtn = GameButtons().createPauseBtn()
    private val upBtn = GameButtons().createUpBtn()
    private val downBtn = GameButtons().createDownBtn()
    private val leftBtn = GameButtons().createLeftBtn()
    private val rightBtn = GameButtons().createRightBtn()
    private val bombBtn = GameButtons().createBombBtn()

    private var playController = PlayController
    private var uiBoard = playController.drawBoard()
    private val nPC = NPC().createNPC()
    private val player = Player().createPlayer()
    private var playerPos = Pair(0,0)
    private var time = "0"
    private var gameOver = false

    private val boardFrameImg = Texture("gameView/boardFrame.png")
    private val tileImg = Texture("gameView/tile.png")
    private val wallImg = Texture("gameView/wall.png")
    private val crateImg = Texture("gameView/crate.png")
    private val fireImg = Texture("gameView/fire.png")
    private val bombImg = Texture("gameView/bomb.png")
    private val powerUpSpeedImg = Texture("gameView/powerUpSpeed.png")
    private val powerUpRangeImg = Texture("gameView/powerUpRange.png")
    private val powerUpPointsImg = Texture("gameView/powerUpPoints.png")

    private var sound: Sound = Gdx.audio.newSound(Gdx.files.internal("data/bombe.mp3"))

    // Calculate values to ensure the view to be scalable on different devices
    private val screenMiddleWidth = if (GAME_WIDTH * 0.5f > GAME_HEIGHT) GAME_HEIGHT else GAME_WIDTH * 0.5f
    private val boardSize = (screenMiddleWidth * 0.9f).coerceAtMost(GAME_HEIGHT * 0.9f)
    private val tileSize = boardSize / 9
    private val boardX = GAME_WIDTH * 0.25f + (screenMiddleWidth-boardSize) * 0.5f
    private val boardY = (GAME_HEIGHT-boardSize) * 0.5f

    init {
        font.data.setScale(FONT_SIZE)
    }

    private fun handleButtons() {
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

    private fun checkGameOver() {
        gameOver = playController.isGameOver()
        if (gameOver) {
            newGame = true
            Alert().checkConnectionLost(stage)
            api!!.updateHighscore(currentUser, playController.finalScore())
            stateManager.push(GameOverState(stateManager,playController.isGameWon(),playController.finalScore()))
        }
    }

    override fun update(deltaTime: Float) {
        Gdx.input.inputProcessor = stage
        playController.update(deltaTime)
        handleButtons()
        checkGameOver()
        time = playController.getTime().toString()
        playerPos = playController.getPlayerPosition()
        Player().updatePosition(player, playerPos.first.toFloat(), playerPos.second.toFloat())
        for (npcPos in playController.getNPCPositions()) {
            NPC().updatePosition(nPC, npcPos.first.toFloat(), npcPos.second.toFloat())
        }
    }
    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        uiBoard = playController.drawBoard()
        sprites.draw(boardFrameImg, GAME_WIDTH * 0.25f,(GAME_HEIGHT - screenMiddleWidth) * 0.5f, screenMiddleWidth, screenMiddleWidth)

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
        player.draw(sprites)
        for (npcPos in playController.getNPCPositions()) {
            nPC.draw(sprites)
        }

        upBtn.draw(sprites)
        downBtn.draw(sprites)
        leftBtn.draw(sprites)
        rightBtn.draw(sprites)
        bombBtn.draw(sprites)
        pauseBtn.draw(sprites)
        LivesDisplay(sprites, playController.getPlayerLives(), playController.getNPCLives())

        font.draw(sprites, time, GAME_WIDTH * 0.05f,  GAME_HEIGHT * 0.92f)
        font.draw(sprites, "Score: ${playController.currentScore()}", GAME_WIDTH * 0.80f, GAME_HEIGHT * 0.80f)

        sprites.flush()
        sprites.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        font.dispose()
        stage.dispose()
        boardFrameImg.dispose()
        tileImg.dispose()
        wallImg.dispose()
        crateImg.dispose()
        fireImg.dispose()
        bombImg.dispose()
        powerUpSpeedImg.dispose()
        powerUpRangeImg.dispose()
        powerUpPointsImg.dispose()
    }
}
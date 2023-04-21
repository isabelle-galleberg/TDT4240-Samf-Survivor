package com.mygdx.tdt4240.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.api.User
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.tdt4240.sprites.Alert
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.FONT_SIZE
import java.util.*
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.sprites.Logo
import com.mygdx.tdt4240.utils.Globals.currentUser
import com.mygdx.tdt4240.utils.Globals.api

/**
 * State for the highscore list
 *
 * @param stateManager The state manager
 */
class HighScoreListState(
    stateManager: StateManager
) : State(stateManager) {

    private val stage = Stage()
    private var highscores = ArrayList<User>()
    private var font = BitmapFont()
    private val logo = Logo().createLogo()
    private val backBtn = BackBtn().createBackBtn()
    private val highscoreImg = Texture("highscoreList/highscore.png")
    private val myscoreImg = Texture("highscoreList/myscore.png")

    init {
        Alert().checkConnectionLost(stage)
        font.data.setScale(FONT_SIZE)
        getHighscores()
    }

    private fun getHighscores() {
        highscores.clear()
        api!!.getHighscores(highscores)
    }

    private fun getHighscoreRanking(): Int {
        for (i in 0 until highscores.size) {
            if (highscores[i].username == currentUser) {
                return i + 1
            }
        }
        return -1
    }

    override fun update(deltaTime: Float) {
         if (BackBtn().backBtnPressed()) {
             stateManager.push(MainMenuState(stateManager))
         }
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        backBtn.draw(sprites)
        logo.draw(sprites)
        sprites.draw(myscoreImg, GAME_WIDTH * 0.15f, GAME_HEIGHT * 0.05f, GAME_WIDTH * 0.7f, GAME_HEIGHT * 0.12f)

        for (i in 0 until highscores.size.coerceAtMost(5)) {
            val highscoreY = GAME_HEIGHT * (0.7f - (i * 0.13f))
            sprites.draw(highscoreImg, GAME_WIDTH * 0.15f, highscoreY, GAME_WIDTH * 0.7f, GAME_HEIGHT * 0.12f)

            val number = "${i + 1}"
            val username = highscores[i].username
            val score = highscores[i].highscore.toString()

            font.draw(sprites, number, GAME_WIDTH * 0.175f, highscoreY + GAME_HEIGHT * 0.09f)
            font.draw(sprites, username, GAME_WIDTH * 0.25f, highscoreY + GAME_HEIGHT * 0.09f)
            font.draw(sprites, score, GAME_WIDTH * 0.75f, highscoreY + GAME_HEIGHT * 0.09f)
        }

        font.draw(sprites, getHighscoreRanking().toString(), GAME_WIDTH * 0.175f, GAME_HEIGHT * 0.13f)
        font.draw(sprites, currentUser, GAME_WIDTH * 0.25f, GAME_HEIGHT * 0.13f)
        font.draw(sprites, api!!.getHighscore(currentUser).toString(), GAME_WIDTH * 0.75f, GAME_HEIGHT * 0.13f)

        sprites.flush()
        sprites.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }



    override fun dispose() {
        font.dispose()
        highscoreImg.dispose()
        myscoreImg.dispose()
        stage.dispose()
    }

}
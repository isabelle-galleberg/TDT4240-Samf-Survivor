package com.mygdx.tdt4240.states

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.firebase.User
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture
import com.mygdx.tdt4240.sprites.BackBtn
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import java.util.*


class HighScoreListState(
    stateManager: StateManager, private val api: API
) : State(stateManager) {

    private var highscores = ArrayList<User>()
    private val font = BitmapFont()
    private val highscoreImg = Texture("highscoreList/highscore.png")
    private val myscoreImg = Texture("highscoreList/myscore.png")
    private val title = Texture("highscoreList/higscoresTitle.png")
    private val backBtn = Texture("backBtn.png")

    init {
        // TODO: ha egen metode for dette?
        fetchHighscores()
    }

    private fun fetchHighscores() {
        highscores.clear()
        api.getHighscores(highscores)
    }

    override fun update(deltaTime: Float) {
        if (Gdx.input.justTouched() && Gdx.input.x < 50f + backBtn.width && Gdx.input.x > 50f && Gdx.input.y > 200f - backBtn.height && Gdx.input.y < 200f){
            println("Back button pressed")
            stateManager.push(MainMenuState(stateManager))
        }
    }

    override fun render(sprites: SpriteBatch) {
        sprites.begin()
        sprites.draw(title, GAME_WIDTH/2 - title.width/2, GAME_HEIGHT - 200f)
        sprites.draw(myscoreImg, GAME_WIDTH/2 - highscoreImg.width/2, 100f)
        sprites.draw(backBtn, 50f, GAME_HEIGHT - 200f)

        for (i in 0 until highscores.size.coerceAtMost(5)) {
            val y = GAME_HEIGHT - 500f - (i * 150f)
            sprites.draw(highscoreImg, GAME_WIDTH / 2 - highscoreImg.width/ 2, y)

            val number = "${i + 1}"
            val username = highscores[i].username
            val score = highscores[i].score.toString()

            font.data.setScale(4f, 4f)
            font.draw(sprites, number, Gdx.graphics.width / 2 - 500f, y + highscoreImg.height - 50f)
            font.draw(sprites, username, Gdx.graphics.width / 2 - 200f, y + highscoreImg.height - 50f)
            font.draw(sprites, score, Gdx.graphics.width - 1000f, y + highscoreImg.height - 50f)
        }
        sprites.end()
    }



    override fun dispose() {
        highscoreImg.dispose()
        myscoreImg.dispose()
        title.dispose()
        backBtn.dispose()
    }

}
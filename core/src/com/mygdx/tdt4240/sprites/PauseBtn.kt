package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class PauseBtn {
    private val pauseBtnSize = Constants.GAME_HEIGHT * 0.1f
    private val pauseBtnX = Constants.GAME_WIDTH * 0.93f
    private val pauseBtnY = Constants.GAME_HEIGHT * 0.85f

    fun createPauseBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/pauseBtn.png"))
        sprite.setSize(pauseBtnSize, pauseBtnSize)
        sprite.setPosition(pauseBtnX, pauseBtnY)
        return sprite
    }

    fun pauseBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > pauseBtnX && Gdx.input.x < pauseBtnX + pauseBtnSize && Gdx.input.y < Constants.GAME_HEIGHT - pauseBtnY && Gdx.input.y > Constants.GAME_HEIGHT - pauseBtnY - pauseBtnSize
    }
}


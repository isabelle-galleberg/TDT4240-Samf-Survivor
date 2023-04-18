package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class BombBtn {

    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val bombBtnSize = screenSideWidth * 0.75f
    private val bombBtnX = GAME_WIDTH - (screenSideWidth + bombBtnSize) * 0.5f
    private val bombBtnY = 0f

    fun createBombBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/bombBtn.png"))
        sprite.setSize(bombBtnSize, bombBtnSize)
        sprite.setPosition(bombBtnX, bombBtnY)
        return sprite
    }

    fun bombBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > bombBtnX && Gdx.input.x < bombBtnX + bombBtnSize && Gdx.input.y < GAME_HEIGHT - bombBtnY && Gdx.input.y > GAME_HEIGHT - bombBtnY - bombBtnSize
    }

}
package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class BackBtn {
    private val backBtnSize = GAME_HEIGHT * 0.1f
    private val backBtnX = GAME_WIDTH * 0.03f
    private val backBtnY = GAME_HEIGHT * 0.85f

    fun createBackBtn(): Sprite {
        val sprite = Sprite(Texture("backBtn.png"))
        sprite.setSize(backBtnSize, backBtnSize)
        sprite.setPosition(backBtnX, backBtnY)
        return sprite
    }

    fun backBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > backBtnX && Gdx.input.x < backBtnX + backBtnSize && Gdx.input.y > backBtnY && Gdx.input.y < backBtnY + backBtnSize
    }
}
package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class DownBtn {
    private val downBtnSize = Constants.GAME_HEIGHT * 0.16f
    private val downBtnX = Constants.GAME_HEIGHT * 0.25f
    private val downBtnY = Constants.GAME_HEIGHT * 0.05f

    fun createDownBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(downBtnSize, downBtnSize)
        sprite.setPosition(downBtnX, downBtnY)
        sprite.setFlip(false, true)
        return sprite
    }

    fun downBtnPressed(): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > downBtnX && Gdx.input.x < downBtnX + downBtnSize && Gdx.input.y < Constants.GAME_HEIGHT - downBtnY && Gdx.input.y > Constants.GAME_HEIGHT - downBtnY - downBtnSize
    }
}
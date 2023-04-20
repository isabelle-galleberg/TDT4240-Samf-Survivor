package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class DownBtn {
    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val downBtnSize = screenSideWidth * 0.3f
    private val downBtnX = (screenSideWidth - downBtnSize) * 0.5f
    private val downBtnY = GAME_HEIGHT * 0.05f

    fun createDownBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(downBtnSize, downBtnSize)
        sprite.setPosition(downBtnX, downBtnY)
        sprite.setFlip(false, true)
        return sprite
    }

    fun downBtnPressed(): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > downBtnX && Gdx.input.x < downBtnX + downBtnSize && Gdx.input.y < GAME_HEIGHT - downBtnY && Gdx.input.y > GAME_HEIGHT - downBtnY - downBtnSize
    }
}
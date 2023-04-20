package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class LeftBtn {

    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val leftBtnSize = screenSideWidth * 0.3f
    private val leftBtnX = (screenSideWidth - 3*leftBtnSize) * 0.5f
    private val leftBtnY = GAME_HEIGHT * 0.05f

    fun createLeftBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowHorizontalBtn.png"))
        sprite.setSize(leftBtnSize, leftBtnSize)
        sprite.setPosition(leftBtnX, leftBtnY)
        sprite.setFlip(true, false)
        return sprite
    }

    fun leftBtnPressed(): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > leftBtnX && Gdx.input.x < leftBtnX + leftBtnSize && Gdx.input.y < GAME_HEIGHT - leftBtnY && Gdx.input.y > GAME_HEIGHT - leftBtnY - leftBtnSize
    }
}
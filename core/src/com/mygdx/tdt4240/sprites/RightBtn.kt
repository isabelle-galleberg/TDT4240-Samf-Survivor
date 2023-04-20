package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class RightBtn {
    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val rightBtnSize = screenSideWidth * 0.3f
    private val rightBtnX = (screenSideWidth + rightBtnSize) * 0.5f
    private val rightBtnY = GAME_HEIGHT *  0.05f

    fun createRightBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowHorizontalBtn.png"))
        sprite.setSize(rightBtnSize, rightBtnSize)
        sprite.setPosition(rightBtnX, rightBtnY)
        return sprite
    }

    fun rightBtnPressed(): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > rightBtnX && Gdx.input.x < rightBtnX + rightBtnSize && Gdx.input.y < GAME_HEIGHT - rightBtnY && Gdx.input.y > GAME_HEIGHT - rightBtnY - rightBtnSize
    }
}
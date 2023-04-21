package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

/**
 * Class for creating and handling the buttons used in the game.
 */
class GameButtons {
    private val screenSideWidth = GAME_WIDTH * 0.25f

    private val directionalBtnSize = screenSideWidth * 0.3f
    private val directionalBtnY = GAME_HEIGHT * 0.05f
    private val verticalBtnX = (screenSideWidth - directionalBtnSize) * 0.5f
    private val leftBtnX = (screenSideWidth - 3 * directionalBtnSize) * 0.5f
    private val rightBtnX = (screenSideWidth + directionalBtnSize) * 0.5f

    private val pauseBtnX = GAME_WIDTH * 0.93f
    private val pauseBtnY = GAME_HEIGHT * 0.85f
    private val pauseBtnSize = GAME_HEIGHT * 0.1f

    private val bombBtnSize = GAME_WIDTH * 0.2f
    private val bombBtnX = GAME_WIDTH * 0.9f - bombBtnSize * 0.5f

    fun createBombBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/bombBtn.png"))
        sprite.setSize(bombBtnSize, bombBtnSize)
        sprite.setPosition(bombBtnX, 0f)
        return sprite
    }
    fun createPauseBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/pauseBtn.png"))
        sprite.setSize(pauseBtnSize, pauseBtnSize)
        sprite.setPosition(pauseBtnX, pauseBtnY)
        return sprite
    }
    fun createDownBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(directionalBtnSize, directionalBtnSize)
        sprite.setPosition(verticalBtnX, directionalBtnY)
        sprite.setFlip(false, true)
        return sprite
    }
    fun createLeftBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowHorizontalBtn.png"))
        sprite.setSize(directionalBtnSize, directionalBtnSize)
        sprite.setPosition(leftBtnX, directionalBtnY)
        sprite.setFlip(true, false)
        return sprite
    }
    fun createRightBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowHorizontalBtn.png"))
        sprite.setSize(directionalBtnSize, directionalBtnSize)
        sprite.setPosition(rightBtnX, directionalBtnY)
        return sprite
    }
    fun createUpBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(directionalBtnSize, directionalBtnSize)
        sprite.setPosition(verticalBtnX, directionalBtnY + directionalBtnSize)
        return sprite
    }

    private fun isBtnPressed(x: Float, y: Float, size: Float): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > x && Gdx.input.x < x + size && Gdx.input.y < GAME_HEIGHT - y && Gdx.input.y > GAME_HEIGHT - y - size
    }

    fun downBtnPressed(): Boolean {
        return isBtnPressed(verticalBtnX, directionalBtnY, directionalBtnSize)
    }

    fun upBtnPressed(): Boolean {
        return isBtnPressed(verticalBtnX, directionalBtnY + directionalBtnSize, directionalBtnSize)
    }

    fun leftBtnPressed(): Boolean {
        return isBtnPressed(leftBtnX, directionalBtnY, directionalBtnSize)
    }

    fun rightBtnPressed(): Boolean {
        return isBtnPressed(rightBtnX, directionalBtnY, directionalBtnSize)
    }

    fun pauseBtnPressed(): Boolean {
        return isBtnPressed(pauseBtnX, pauseBtnY, pauseBtnSize)
    }

    fun bombBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > bombBtnX && Gdx.input.x < bombBtnX + bombBtnSize && Gdx.input.y < GAME_HEIGHT && Gdx.input.y > GAME_HEIGHT - bombBtnSize
    }
}

package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class BombBtn {
    private val bombBtnSize = GAME_HEIGHT * 0.45f
    private val bombBtnX = GAME_WIDTH/2 + (GAME_HEIGHT/2 + bombBtnSize/4)
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
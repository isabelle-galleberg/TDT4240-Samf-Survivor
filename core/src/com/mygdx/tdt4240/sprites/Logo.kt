package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class Logo {
    private val logoWidth = GAME_HEIGHT * 0.8f
    private val logoHeight = GAME_HEIGHT * 0.1f
    private val logoX = (GAME_WIDTH - logoWidth) * 0.5f
    private val logoY = GAME_HEIGHT * 0.85f

    fun createLogo(): Sprite {
        val sprite = Sprite(Texture("logo.png"))
        sprite.setSize(logoWidth, logoHeight)
        sprite.setPosition(logoX, logoY)
        return sprite
    }
}
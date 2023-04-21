package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

/**
 * Class for creating and handling the game logo.
 */
class Logo {
    private val logoWidth = GAME_HEIGHT * 1.3f
    private val logoHeight = GAME_HEIGHT * 0.175f
    private val logoX = (GAME_WIDTH - logoWidth) * 0.5f
    private val logoY = GAME_HEIGHT -logoHeight

    fun createLogo(): Sprite {
        val sprite = Sprite(Texture("logo.png"))
        sprite.setSize(logoWidth, logoHeight)
        sprite.setPosition(logoX, logoY)
        return sprite
    }
}
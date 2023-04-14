package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT


class Window {

    val WIN_WIDTH=(GAME_WIDTH /10).toInt()*7
    val WIN_HEIGHT=(GAME_HEIGHT /8).toInt()*6

    val WIN_PAUSE_HEIGHT=(GAME_HEIGHT /7).toInt()*5

    fun createWindow(): Sprite {
        val pixmap = Pixmap(WIN_WIDTH, WIN_HEIGHT, Pixmap.Format.RGBA8888)
        pixmap.setColor(1F, 1f, 1f, 1f)
        pixmap.fillRectangle(0, 0, WIN_WIDTH, WIN_HEIGHT)
        val winTex=Texture(pixmap)
        val winSprite=Sprite(winTex)
        winSprite.setPosition((GAME_WIDTH-WIN_WIDTH)/2, 0f)
        return winSprite
    }

    fun createPauseWindow(): Sprite {
        val pixmap = Pixmap(WIN_WIDTH, WIN_HEIGHT, Pixmap.Format.RGBA8888)
        pixmap.setColor(1F, 1f, 1f, 1f)
        pixmap.fillRectangle(0, 0, WIN_WIDTH, WIN_PAUSE_HEIGHT)
        val winTex=Texture(pixmap)
        val winSprite=Sprite(winTex)
        winSprite.setPosition((GAME_WIDTH-WIN_WIDTH)/2, 0f)
        return winSprite
    }
}

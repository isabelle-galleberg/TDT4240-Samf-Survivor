package com.mygdx.tdt4240.sprites


import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants


class Common {
    val winWidht=(Constants.GAME_WIDTH/10)*8
    val winHeight=(Constants.GAME_HEIGHT/8)*6
    public val logoTexture=Texture("logo.png")
    public val titleText = BitmapFont()
    val winTex=createWindow()
    val logoSprite = Sprite(logoTexture,Constants.GAME_WIDTH, 80)


    public fun createLogo(){
        val logoSprite = Sprite(logoTexture,Constants.GAME_WIDTH, 100)
        logoSprite.setPosition(0F, 100F)
    }
    public fun createWindow(): Sprite {
        val pixmap = Pixmap(winWidht, winHeight, Pixmap.Format.RGBA8888)
        pixmap.setColor(128F, 0f, 0f, 0.9f)
        pixmap.fillRectangle(0, 0, winWidht, winHeight)
        val windowTexture = Texture(pixmap)
        pixmap.dispose()
        return Sprite(windowTexture)
    }

}
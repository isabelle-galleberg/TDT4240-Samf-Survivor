package com.mygdx.tdt4240.sprites


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.mygdx.tdt4240.utils.Constants


class Common {
    val WIN_WIDTH=(Constants.GAME_WIDTH /10)*7
    val WIN_HEIGHT=(Constants.GAME_HEIGHT /8)*6

    val logoTexture=Texture("logo.png")
    val samfTexture=Texture("samfundet.png")
    val windowSprite=createWindow()
    public val titleText = BitmapFont()


    public fun createWindow(): Sprite {
        val pixmap = Pixmap(WIN_WIDTH, WIN_HEIGHT, Pixmap.Format.RGBA8888)
        pixmap.setColor(128F, 0f, 0f, 1f)
        pixmap.fillRectangle(0, 0, WIN_WIDTH, WIN_HEIGHT)
        val windowTexture = Texture(pixmap)
        pixmap.dispose()
        return Sprite(windowTexture)
    }

    public fun createSmallButton(buttonName: String): TextButton {
        val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
        val button = TextButton(buttonName, skin)
        button.setSize(150f, 50f)
        return button
    }
}
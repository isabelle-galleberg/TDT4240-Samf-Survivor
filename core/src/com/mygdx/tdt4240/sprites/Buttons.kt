package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton


class Buttons {
    fun createSmallButton(name: String): TextButton {
        val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
        val button = TextButton(name, skin)
        button.style.disabledFontColor= Color.PINK
        button.setSize(150f, 50f)
        return button
    }
}
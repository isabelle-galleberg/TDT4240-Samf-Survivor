package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton


class Buttons {
    fun createSmallButton(name: String): TextButton {
        val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
        val button = TextButton(name, skin)
        button.style.disabledFontColor= Color.RED
        button.label.setFontScale(2.toFloat(), 2.toFloat())
        button.setSize(250f, 75f)
        return button
    }
}
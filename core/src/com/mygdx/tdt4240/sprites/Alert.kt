package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.tdt4240.utils.Constants
import com.mygdx.tdt4240.utils.Globals.api
import com.mygdx.tdt4240.utils.Globals.connectionLost

/**
 * Alert class for showing an alert dialog when the connection to the server is lost.
 */
class Alert {
    private val skin = Skin(Gdx.files.internal("skin/uiskin.json"))
    private val textFieldStyle: TextField.TextFieldStyle = skin.get(TextField.TextFieldStyle::class.java).apply {
        font.data.setScale(Constants.FONT_SIZE)
    }
    private val dialog = Dialog("", skin, "dialog").apply {
        text("Connection to server failed. Please restart the game.")
        isMovable = false // disable moving the dialog
        isResizable = false // disable resizing the dialog
    }

    init {
        dialog.button("Close", true).addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                dialog.hide()
            }
        })
    }

    fun checkConnectionLost(stage: Stage) {
        api!!.checkDatabaseConnection()
        if (connectionLost) {
            stage.addActor(dialog)
            dialog.show(stage)
        }
    }

}
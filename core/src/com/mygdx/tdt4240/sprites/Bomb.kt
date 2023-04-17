package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants
import java.util.*

class Bomb {
    private val bombSize = Constants.GAME_HEIGHT * 0.1f
    private var bombX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var bombY = Constants.GAME_HEIGHT * 0.05f

    fun createBomb(): Sprite {
        var sprite = Sprite(Texture("gameView/bomb.png"))
        sprite.setSize(bombSize, bombSize)
        sprite.setPosition(bombX, bombY)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                sprite = Sprite(Texture("gameView/tile.png"))
            } }, (2000))

        return sprite
    }

    fun updatePosition(bomb: Sprite) {
   //     bombX = Constants.GAME_HEIGHT * 0.05f + Constants.GAME_WIDTH * 0.5f - Constants.GAME_HEIGHT * 0.5f + i * Constants.GAME_HEIGHT * 0.1f
     //   bombY = Constants.GAME_HEIGHT * 0.05f + j * Constants.GAME_HEIGHT * 0.1f

        //bomb.setPosition(bombX, bombY)

    }


}
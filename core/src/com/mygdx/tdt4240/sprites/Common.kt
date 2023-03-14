package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.mygdx.tdt4240.utils.Constants
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite

class Common {
    public val logo = Sprite(Texture("logo.png"))
    public val titleText = BitmapFont()


    public fun createLogo(sprite: Batch){
        sprite.draw(logo, (Constants.GAME_WIDTH-logo.width)/2, Constants.GAME_HEIGHT-logo.height.toFloat())

    }

    //titleText.draw(sprite, "This is the tutorial view", 20f, 200f)
}
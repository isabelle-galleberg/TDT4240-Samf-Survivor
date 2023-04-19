package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class LivesDisplay (sprites: SpriteBatch, livesPlayer: Int, livesNPC: Int){

    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val x = screenSideWidth * 0.20f

    private val backgroundHeight = GAME_HEIGHT * 0.1f
    private val backgroundWidth = backgroundHeight * 2.5f
    private val backgroundY = GAME_HEIGHT * 0.75f

    private val iconSize = backgroundHeight * 0.8f
    private val iconPlayerY = GAME_HEIGHT * 0.76f
    private val iconNPCY = GAME_HEIGHT * 0.66f

    private val heartSize = backgroundHeight * 0.6f
    private val heartX = x + backgroundWidth - heartSize
    private val heartFullY = GAME_HEIGHT * 0.77f
    private val heartEmptyY = GAME_HEIGHT * 0.67f

    private val backgroundImg = Texture("gameView/background.png")
    private val heartFullImg = Texture("gameView/heartFull.png")
    private val heartEmptyImg = Texture("gameView/heartEmpty.png")
    private val playerImg = Texture("gameView/player.png")
    private val npcImg = Texture("gameView/npc.png")

    init {
        // Lives display for main player
        sprites.draw(backgroundImg, x,  backgroundY, backgroundWidth, backgroundHeight)

        sprites.draw(playerImg, x,  iconPlayerY, iconSize, iconSize)

        for (i in 0 until 3) {
            if(i < 3-livesPlayer){
                sprites.draw(heartEmptyImg, heartX - i * heartSize,  heartFullY, heartSize, heartSize)
            }
            else {
                sprites.draw(heartFullImg, heartX - i * heartSize,  heartFullY, heartSize, heartSize)
            }
        }

        // Lives display for NPC
        sprites.draw(backgroundImg, x,  backgroundY - backgroundHeight, backgroundWidth, backgroundHeight)

        sprites.draw(npcImg, x,  iconNPCY, iconSize, iconSize)

        for (i in 0 until 3) {
            if(i < 3-livesNPC){
                sprites.draw(heartEmptyImg, heartX - i * heartSize,  heartEmptyY, heartSize, heartSize)
            }
            else {
                sprites.draw(heartFullImg, heartX - i * heartSize,  heartEmptyY, heartSize, heartSize)
            }
        }
    }
}

package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT

class LivesDisplay (sprites: SpriteBatch, livesPlayer: Int, livesNPC: Int){

    private val backgroundImg = Texture("gameView/rect.png")
    private val heartFullImg = Texture("gameView/heartFull.png")
    private val heartEmptyImg = Texture("gameView/heartEmpty.png")
    private val playerImg = Texture("gameView/player.png")
    private val nPCImg = Texture("gameView/NPC.png")

    init {
        // Lives display for main player
        sprites.draw(backgroundImg, GAME_HEIGHT * 0.25f,  GAME_HEIGHT * 0.75f, GAME_HEIGHT * 0.3f, GAME_HEIGHT * 0.1f)

        sprites.draw(playerImg, GAME_HEIGHT * 0.27f,  GAME_HEIGHT * 0.76f, GAME_HEIGHT * 0.07f, GAME_HEIGHT * 0.075f)

        for (i in 0 until 3) {
            if(i < livesPlayer){
                sprites.draw(heartFullImg, GAME_HEIGHT * (0.35f + i * 0.06f),  GAME_HEIGHT * 0.77f, GAME_HEIGHT * 0.06f, GAME_HEIGHT * 0.06f)
            }
            else {
                sprites.draw(heartEmptyImg, GAME_HEIGHT * (0.35f + i * 0.06f),  GAME_HEIGHT * 0.77f, GAME_HEIGHT * 0.06f, GAME_HEIGHT * 0.06f)
            }

        }

        // Lives display for NPC
        sprites.draw(backgroundImg, GAME_HEIGHT * 0.25f,  GAME_HEIGHT * 0.65f, GAME_HEIGHT * 0.3f, GAME_HEIGHT * 0.1f)

        sprites.draw(nPCImg, GAME_HEIGHT * 0.27f,  GAME_HEIGHT * 0.67f, GAME_HEIGHT * 0.06f, GAME_HEIGHT * 0.06f)

        for (i in 0 until 3) {
            if(i < livesNPC){
                sprites.draw(heartFullImg, GAME_HEIGHT * (0.35f + i * 0.06f),  GAME_HEIGHT * 0.67f, GAME_HEIGHT * 0.06f, GAME_HEIGHT * 0.06f)
            }
            else {
                sprites.draw(heartEmptyImg, GAME_HEIGHT * (0.35f + i * 0.06f),  GAME_HEIGHT * 0.67f, GAME_HEIGHT * 0.06f, GAME_HEIGHT * 0.06f)
            }
        }
    }
}

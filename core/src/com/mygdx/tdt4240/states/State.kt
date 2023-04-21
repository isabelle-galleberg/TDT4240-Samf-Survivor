package com.mygdx.tdt4240.states
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

/**
 * Abstract class for most basic of common functionality between game states.
 *
 * @param stateManager Manager of all game states.
 */
abstract class State(
    protected var stateManager: StateManager
) {
    private val camera: OrthographicCamera = OrthographicCamera()

    init {
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT)
    }

    abstract fun update(deltaTime: Float)
    abstract fun render(sprites: SpriteBatch)
    abstract fun dispose()
}
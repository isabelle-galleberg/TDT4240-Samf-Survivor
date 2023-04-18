package com.mygdx.tdt4240

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.tdt4240.api.API
import com.mygdx.tdt4240.states.*
import com.mygdx.tdt4240.utils.Globals.api

class Game(private var gameAPI: API) : ApplicationAdapter() {

	companion object {
		var sprites: SpriteBatch? = null
	}

	private var stateManager: StateManager? = null

	override fun create() {
		sprites = SpriteBatch()
		stateManager = StateManager()
		api = gameAPI
		Gdx.input.inputProcessor = InputMultiplexer()
		Gdx.gl.glClearColor(237F, 232F, 232F, 1F)
		stateManager?.push(LoginState(stateManager!!))
	}

	override fun render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		stateManager?.update(Gdx.graphics.deltaTime)
		stateManager?.render(sprites!!)
	}

	override fun dispose() {
		sprites?.dispose()
	}
}
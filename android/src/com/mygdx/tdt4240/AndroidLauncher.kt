package com.mygdx.tdt4240

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

/**
 * Class for creating the Android application.
 */
class AndroidLauncher : AndroidApplication() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val config = AndroidApplicationConfiguration()
		initialize(Game(AndroidAPI()), config)
	}
}

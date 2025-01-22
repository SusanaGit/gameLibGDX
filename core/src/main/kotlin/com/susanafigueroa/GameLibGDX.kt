package com.susanafigueroa

import com.badlogic.gdx.Game
import com.susanafigueroa.screens.StartScreen

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class GameLibGDX : Game() {

    override fun create() {

        setScreen(StartScreen(this))

    }

    override fun dispose() {

        screen.dispose()

    }

}

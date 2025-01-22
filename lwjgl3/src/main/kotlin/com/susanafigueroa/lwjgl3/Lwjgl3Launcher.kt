@file:JvmName("Lwjgl3Launcher")

package com.susanafigueroa.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.susanafigueroa.GameLibGDX
import com.susanafigueroa.utils.GameInfo

/** Launches the desktop (LWJGL3) application. */
fun main() {
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired())
      return
    Lwjgl3Application(GameLibGDX(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("GameLibGDX")
        setWindowedMode(GameInfo.WIDTH, GameInfo.HEIGHT)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}

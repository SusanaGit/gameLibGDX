package com.susanafigueroa.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport

class StartScreen : Screen {

    // este Stage contiene el viewport por defecto
    private val stage = Stage(ScreenViewport())
    private val font = BitmapFont()

    override fun show() {

        // estilo para la label Welcome
        val labelStyle = Label.LabelStyle()
        labelStyle.font = font

        // creo label Welcome
        val messageWelcome = Label("Welcome", labelStyle)
        messageWelcome.setFontScale(4f)

        // creo tabla para centrar elementos fácilmente
        val table = Table()
        // quiero que la tabla ocupe toda la pantalla
        table.setFillParent(true)
        // centra los elementos en la tabla
        table.center()

        // añado los elementos a la tabla
        table.add(messageWelcome)

        // añado la tabla al Stage
        stage.addActor(table)

    }

    override fun render(delta: Float) {
        // dibujo el Stage
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        // los actores añadidos al Stage se limpian aquí
        stage.dispose()

        font.dispose()
    }
}

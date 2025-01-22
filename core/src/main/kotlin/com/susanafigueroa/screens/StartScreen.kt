package com.susanafigueroa.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.susanafigueroa.GameLibGDX

class StartScreen(private val game: GameLibGDX) : Screen {

    // este Stage contiene el viewport por defecto
    private val stage = Stage(ScreenViewport())
    private val font = BitmapFont()

    override fun show() {

        // aumento tamaño de la fuente
        font.data.setScale(4f)

        // estilo para la label Welcome
        val labelStyle = Label.LabelStyle()
        labelStyle.font = font

        // creo label Welcome
        val messageWelcome = Label("Welcome", labelStyle)

        // estilo para el button
        val buttonStyle = TextButton.TextButtonStyle()
        buttonStyle.font = font

        // creo el button
        val startButton = TextButton("Start", buttonStyle)

        // creo tabla para centrar elementos fácilmente
        val table = Table()
        // quiero que la tabla ocupe toda la pantalla
        table.setFillParent(true)
        // centra los elementos en la tabla
        table.center()

        // añado los elementos a la tabla
        table.add(messageWelcome).padBottom(10f).row()
        table.add(startButton).height(300f).width(300f)

        // ClickListener para el button
        startButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.screen = GameScreen()
            }
        })

        // añado la tabla al Stage
        stage.addActor(table)

        // quiero que el Stage gestione los eventos de entrada
        Gdx.input.inputProcessor = stage

    }

    override fun render(delta: Float) {
        // limpia la pantalla antes de dibujar
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

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

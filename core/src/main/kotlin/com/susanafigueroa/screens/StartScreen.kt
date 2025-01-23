package com.susanafigueroa.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.susanafigueroa.GameLibGDX
import com.susanafigueroa.utils.GameInfo

class StartScreen(private val game: GameLibGDX) : Screen {

    // creación de la camara i del viewport
    private val camera = OrthographicCamera()
    private val viewport = FitViewport(GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat(), camera)

    // este Stage contiene el viewport configurado anterior
    private val stage = Stage(viewport)
    private val font = BitmapFont()

    init {
        // quiero que el Stage gestione los eventos de entrada
        Gdx.input.inputProcessor = stage
    }

    override fun show() {

        // aumento tamaño de la fuente
        font.data.setScale(2f)

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
                game.screen = GameScreen(game)
            }
        })

        // añado la tabla al Stage
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        // limpia la pantalla antes de dibujar
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // dibujo el Stage
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
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

package com.susanafigueroa.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.susanafigueroa.utils.GameInfo

class Player : Actor() {

    companion object {
        const val WIDTH_PLAYER: Float = 80f
        const val HEIGHT_PLAYER: Float = 80f
        const val INITIAL_POSITION_X_PLAYER: Float = 80f
        const val INITIAL_POSITION_Y_PLAYER: Float = 80f
        const val PATH_TEXTURE_PLAYER: String = "player/player.png"
        const val SPEED: Float = 100f
    }

    private val texturePlayer: Texture
    private val positionPlayer: Vector2 = Vector2(INITIAL_POSITION_X_PLAYER, INITIAL_POSITION_Y_PLAYER)
    private val rectanglePlayer: Rectangle

    init {
        // inicializo texturePlayer
        texturePlayer = Texture(PATH_TEXTURE_PLAYER)

        // adjudico tamaño al player
        setSize(WIDTH_PLAYER, HEIGHT_PLAYER)

        // adjudico posición inicial al player
        setPosition(positionPlayer.x, positionPlayer.y)

        // adjudico podición inicial al rectanglePlayer
        rectanglePlayer = Rectangle(positionPlayer.x, positionPlayer.y, WIDTH_PLAYER, HEIGHT_PLAYER)
    }

    override fun act(delta: Float) {
        super.act(delta)

        // Procesar entradas del teclado para mover al jugador
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { // Mover arriba
            moveBy(0f, SPEED * delta)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { // Mover abajo
            moveBy(0f, -SPEED * delta)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { // Mover izquierda
            moveBy(-SPEED * delta, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { // Mover derecha
            moveBy(SPEED * delta, 0f)
        }

        val valueTouchX = Gdx.input.getX()
        val valueTouchY = Gdx.input.getY()

        if (Gdx.input.isTouched) {
            if (valueTouchY > GameInfo.HEIGHT/2) { // Mover arriba
                moveBy(0f, -SPEED * delta)
            }
            if (valueTouchY < GameInfo.HEIGHT/2) { // Mover abajo
                moveBy(0f, SPEED * delta)
            }
            if (valueTouchX < GameInfo.WIDTH/2) { // Mover izquierda
                moveBy(-SPEED * delta, 0f)
            }
            if (valueTouchX > GameInfo.WIDTH/2) { // Mover derecha
                moveBy(SPEED * delta, 0f)
            }


        }

        // Actualizar la posición del rectángulo
        rectanglePlayer.setPosition(x, y)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        // dibuja la textura del jugador en la posición x e y, posición del player
        // dentro del mundo, y con su tamaño
        batch.draw(texturePlayer, x, y, width, height)

        Gdx.app.log("PositionPlayerX", "PositionPlayerX: $x")
        Gdx.app.log("PositionPlayerY", "PositionPlayerY: $y")
    }

}

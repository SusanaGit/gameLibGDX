package com.susanafigueroa.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

class Player : Actor() {

    companion object {
        const val WIDTH_PLAYER: Float = 80f
        const val HEIGHT_PLAYER: Float = 80f
        const val INITIAL_POSITION_X_PLAYER: Float = 80f
        const val INITIAL_POSITION_Y_PLAYER: Float = 80f
        const val PATH_TEXTURE_PLAYER: String = "player/player.png"
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

    override fun draw(batch: Batch, parentAlpha: Float) {
        // dibuja la textura del jugador en la posición x e y, posición del player
        // dentro del mundo, y con su tamaño
        batch.draw(texturePlayer, x, y, width, height)

        Gdx.app.log("PositionPlayerX", "PositionPlayerX: $x")
        Gdx.app.log("PositionPlayerY", "PositionPlayerY: $y")
    }

}

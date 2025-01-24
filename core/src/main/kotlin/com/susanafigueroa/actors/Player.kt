package com.susanafigueroa.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Array
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

    private var playerIsWalking: Boolean
    private lateinit var animationPlayerWalking: Animation<TextureRegion>

    private var elapsedTime: Float

    init {

        elapsedTime = 0f

        // inicializo texturePlayer
        texturePlayer = Texture(PATH_TEXTURE_PLAYER)

        // adjudico tamaño al player
        setSize(WIDTH_PLAYER, HEIGHT_PLAYER)

        // adjudico posición inicial al player
        setPosition(positionPlayer.x, positionPlayer.y)

        // adjudico podición inicial al rectanglePlayer
        rectanglePlayer = Rectangle(positionPlayer.x, positionPlayer.y, WIDTH_PLAYER, HEIGHT_PLAYER)

        // inicializo la animación de caminar
        initAnimationWalking()

        // inicializo que no está caminando
        playerIsWalking = false
    }

    override fun act(delta: Float) {
        super.act(delta)

        playerIsWalking = false

        // Procesar entradas del teclado para mover al jugador
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { // Mover arriba
            moveBy(0f, SPEED * delta)
            playerIsWalking = true
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { // Mover abajo
            moveBy(0f, -SPEED * delta)
            playerIsWalking = true
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { // Mover izquierda
            moveBy(-SPEED * delta, 0f)
            playerIsWalking = true
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { // Mover derecha
            moveBy(SPEED * delta, 0f)
            playerIsWalking = true
        }

        val valueTouchX = Gdx.input.getX()
        val valueTouchY = Gdx.input.getY()

        if (Gdx.input.isTouched) {
            if (valueTouchY > GameInfo.HEIGHT/2) { // Mover arriba
                moveBy(0f, -SPEED * delta)
                playerIsWalking = true
            }
            if (valueTouchY < GameInfo.HEIGHT/2) { // Mover abajo
                moveBy(0f, SPEED * delta)
                playerIsWalking = true
            }
            if (valueTouchX < GameInfo.WIDTH/2) { // Mover izquierda
                moveBy(-SPEED * delta, 0f)
                playerIsWalking = true
            }
            if (valueTouchX > GameInfo.WIDTH/2) { // Mover derecha
                moveBy(SPEED * delta, 0f)
                playerIsWalking = true
            }
        }

        // actualizo la posición del rectángulo
        rectanglePlayer.setPosition(x, y)

        // actualizo el elapsedTime -> al método getKeyFrame de Animation le tengo que pasar el
        // elapsedTime. si no lo actualizo, siempre le pasaría el mismo elapsedTime, lo que ocasionaría
        // que se viese solo el primer frame de la animación.
        elapsedTime += delta

    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (playerIsWalking) {
            // dibuja las texturas de la animación walking
            val textureAnimation: TextureRegion = TextureRegion(animationPlayerWalking.getKeyFrame(elapsedTime, true))
            batch.draw(textureAnimation, x, y, width, height)
        } else {
            // dibuja la textura estática del jugador en la posición x e y, posición del player
            // dentro del mundo, y con su tamaño
            batch.draw(texturePlayer, x, y, width, height)
        }
    }

    private fun initAnimationWalking() {

        // cargo el atlas de las texturas de la animación
        val playerAtlas: TextureAtlas = TextureAtlas("player/playersatlas.atlas")

        // meto las texturas que elijo para el walking en un array de objetos TextureRegion
        val playerWalkingTextures: Array<TextureRegion> = Array<TextureRegion>()
        playerWalkingTextures.add(playerAtlas.findRegion("Walk (1)"))
        playerWalkingTextures.add(playerAtlas.findRegion("Walk (8)"))

        // genero la animación -> cada frame durará 0.2 segundos antes de pasar al siguiente frame
        animationPlayerWalking = Animation<TextureRegion>(1f/5f, playerWalkingTextures)

    }

}

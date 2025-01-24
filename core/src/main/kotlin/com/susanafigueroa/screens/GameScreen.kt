package com.susanafigueroa.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.susanafigueroa.GameLibGDX
import com.susanafigueroa.actors.Player
import com.susanafigueroa.utils.CollisionHandler
import com.susanafigueroa.utils.GameInfo

class GameScreen(private val game: GameLibGDX) : Screen {

    private val stage: Stage

    private val camera: OrthographicCamera
    private val viewport: StretchViewport

    private val mapLoader: TmxMapLoader
    private val tiledMap: TiledMap
    private val mapRenderer: OrthogonalTiledMapRenderer

    private val objectCollisionHandler: CollisionHandler
    private val collisionRectanglesMap: MutableList<Rectangle>

    private val player: Player

    init {

        Gdx.app.log("GameScreen", "Estoy en GameScreen")

        // creo la cámara
        camera = OrthographicCamera()

        // configuro la cámara en un sistema de coordenadas ortográfico, estandar en juegos 2D
        // los píxeles de la pantalla corresponden directamente a unidades del mundo del juego
        camera.setToOrtho(false, GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat())

        // el tamaño que quiero que se muestre
        viewport = StretchViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            camera
        )

        // creo el stage con el viewport que he creado anteriormente
        stage = Stage(viewport)

        // cargo el mapa
        mapLoader = TmxMapLoader()
        tiledMap = mapLoader.load("map/mapa.tmx")
        Gdx.app.log("GameScreen", "Mapa cargado: $tiledMap")
        // para que se vea el mapa en la pantalla (para renderizarlo) uso OrthogonalTiledMapRenderer
        mapRenderer = OrthogonalTiledMapRenderer(tiledMap)

        // meto los rectángulos de colisión de la capa collision_layer en la lista collisionRectanglesMap
        objectCollisionHandler = CollisionHandler()
        collisionRectanglesMap = objectCollisionHandler.loadCollisionRectangles(tiledMap, "collision_layer")

        // creo el player
        player = Player()
        // añado el player al stage
        stage.addActor(player)

    }

    override fun show() {
    }

    override fun render(delta: Float) {
        // limpia la pantalla antes de dibujar
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        updatePositionCamera(camera, tiledMap)

        // asigna la vista de la cámara al renderizador
        mapRenderer.setView(camera)
        mapRenderer.render()

        // verifico colisión entre rectángulo del player y rectángulos de la capa collision_layer del mapa
        if (objectCollisionHandler.checkCollision(player.rectanglePlayer, collisionRectanglesMap)) {
            Gdx.app.log("Collision player with collision_layer rectangle", "Collision player with collision_layer rectangle")
            // si hay colisión con rectángulo de la capa collision_layer, vuelve a la posición anterior
            player.goToPreviousPosition()
        }

        // actualiza todos los elementos del stage
        stage.act()

        // dibuja todos los elementos del stage
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
        mapRenderer.dispose()
        stage.dispose()
    }

    private fun updatePositionCamera(camera: OrthographicCamera, tiledMap: TiledMap) {

        val mapWithPixels = calculateMapWidthInPixels(tiledMap)
        val mapHeightPixels = calculateMapHeightInPixels(tiledMap)

        val cameraX: Float = (camera.viewportWidth / 2).coerceAtLeast(
            (player.x.coerceAtMost(mapWithPixels - camera.viewportWidth / 2))
        )

        val cameraY: Float = (camera.viewportHeight / 2).coerceAtLeast(
            (player.y.coerceAtMost(mapHeightPixels - camera.viewportHeight / 2))
        )

        // defino la posición que quiero que sea el centro de la cámara (punto focal), en este caso
        // donde esté el player
        camera.position.set(cameraX, cameraY, 0f)

        // cada vez que cambio los parámetros de la camera, tengo que hacer update
        camera.update()
    }

    private fun calculateMapWidthInPixels(tiledMap: TiledMap): Float {
        // cada tile ocupa 32 pixels de ancho
        return tiledMap.properties.get("width", Int::class.java).toFloat() * 32
    }

    private fun calculateMapHeightInPixels(tiledMap: TiledMap): Float {
        // cada tile ocupa 32 pixels de alto
        return tiledMap.properties.get("height", Int::class.java).toFloat() * 32
    }
}

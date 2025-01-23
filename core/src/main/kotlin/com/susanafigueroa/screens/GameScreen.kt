package com.susanafigueroa.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.susanafigueroa.GameLibGDX
import com.susanafigueroa.utils.GameInfo

class GameScreen(private val game: GameLibGDX) : Screen {

    private val camera: OrthographicCamera
    private val viewport: StretchViewport

    private val mapLoader: TmxMapLoader
    private val tiledMap: TiledMap
    private val mapRenderer: OrthogonalTiledMapRenderer

    init {

        Gdx.app.log("GameScreen", "Estoy en GameScreen")

        // creo la cámara
        camera = OrthographicCamera()

        // establece el tamaño de la pantalla y centra la cámara en la ventana gráfica
        camera.setToOrtho(false, GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat())

        // el tamaño que quiero que se muestre
        viewport = StretchViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            camera
        )

        // cargo el mapa
        mapLoader = TmxMapLoader()
        tiledMap = mapLoader.load("map/mapa.tmx")
        Gdx.app.log("GameScreen", "Mapa cargado: $tiledMap")
        // renderizo (hago que se vea) el mapa en la pantalla
        mapRenderer = OrthogonalTiledMapRenderer(tiledMap)

    }

    override fun show() {
    }

    override fun render(delta: Float) {
        // limpia la pantalla antes de dibujar
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        updatePositionCamera(camera, tiledMap)

        mapRenderer.setView(camera)
        mapRenderer.render()
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
    }

    private fun updatePositionCamera(camera: OrthographicCamera, tiledMap: TiledMap) {
        val mapWidthPixels = calculateMapWidthInPixels(tiledMap)
        val mapHeightPixels = calculateMapHeightInPixels(tiledMap)
        Gdx.app.log("mapWidthPixels", "mapWidthPixels: ${mapWidthPixels}")
        Gdx.app.log("mapHeightPixels", "mapHeightPixels: ${mapHeightPixels}")

        /*val cameraWidthPixels = camera.viewportWidth
        val cameraHeightPixels = camera.viewportHeight
        Gdx.app.log("cameraWidthPixels", "cameraWidthPixels: ${cameraWidthPixels}")
        Gdx.app.log("cameraHeightPixels", "cameraHeightPixels: ${cameraHeightPixels}")*/

        // defino la posición que quiero que sea el centro de la cámara, en este caso
        // el centro del mapa
        camera.position.set(mapWidthPixels/2, mapHeightPixels/2, 0f)

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

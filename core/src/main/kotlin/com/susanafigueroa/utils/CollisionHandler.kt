package com.susanafigueroa.utils

import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle

class CollisionHandler {

    // método para obtener los rectángulos de colisión de una capa del mapa
    fun loadCollisionRectangles(tiledMap: TiledMap, layerName: String) : MutableList<Rectangle> {

        val collisionRectangles: MutableList<Rectangle> = mutableListOf<Rectangle>()

        // obtengo los objetos de la capa collision_layer del mapa
        val collisionLayer = tiledMap.layers[layerName].objects.getByType(RectangleMapObject::class.java)

        for (obj in collisionLayer) {
            collisionRectangles.add(obj.rectangle)
        }

        return collisionRectangles
    }

    // método para comprovar si hay colisión de un rectángulo de una lista de rectángulos con otro rectángulo
    fun checkCollision(rectangle: Rectangle, collisionRectangles: MutableList<Rectangle>) : Boolean {
        for (collisionRect in collisionRectangles) {
            if (rectangle.overlaps(collisionRect)) {
                return true
            }
        }
        return false
    }

}

package ballgame.models.shapes

import ballgame.physics.shapeSize
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Shape
import kotlin.math.round


interface Draggable {
    fun followMouse(event: MouseEvent)
    fun adjustBox() {
        with(this as Shape) {
            this.layoutX = round(this.layoutX / shapeSize) * shapeSize
            this.layoutY = round(this.layoutY / shapeSize) * shapeSize
        }
    }
}
package ballgame.models

import ballgame.physics.Newton.G
import javafx.geometry.Bounds
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse
import kotlin.math.abs

class Ball(private var vx: Double, private var vy: Double) : Ellipse(10.0, 10.0, 10.0, 10.0) {

    init {
        this.fill = Color.DARKGRAY
    }

    fun move() {
        this.vy += G
        this.centerX = this.centerX + vx
        this.centerY = this.centerY + vy
    }

    fun testCollide(vararg bounds: Bounds) {
        bounds.forEach {
            if (this.centerX + this.vx >= it.maxX - this.radiusX || this.centerX + this.vx <= this.radiusX) {
                this.vx *= -0.75
            }
            if (this.centerY + this.vy >= it.maxY - this.radiusY || this.centerY + this.vy <= this.radiusY) {
                this.vy *= -0.75
            }
        }
    }
}

package ballgame.models

import ballgame.models.shapes.Collisible
import ballgame.physics.Newton
import ballgame.physics.Newton.G
import javafx.geometry.Bounds
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse
import javafx.scene.shape.Shape

class Ball(
        var vx: Double,
        var vy: Double,
        private var px: Double,
        private var py: Double
) : Ellipse(px, py, 10.0, 10.0) {

    private var onGround = false

    init {
        this.fill = Color.DARKGRAY
    }

    fun updatePosition(elapsedSeconds: Long) {
        if (!onGround)
            this.vy += G * elapsedSeconds
        else
            this.vx *= Newton.f
        this.centerX = this.centerX + vx * elapsedSeconds
        this.centerY = this.centerY + vy * elapsedSeconds
    }

    fun checkCollisions(shapes: List<Shape>) {
        shapes.forEach {shape ->
            // 先判断是否碰撞
            // 再根据碰撞的切面改变速度
            if ((shape as Collisible).isCollide(this)) {
                vx *= -1
                vy *= -1
            }
        }
    }

    fun checkBorderCollisions(bounds: Bounds) {
        if (centerX + vx >= bounds.maxX - radiusX || centerX + vx <= radiusX) {
            vx *= -1
        }
        if (centerY + vy >= bounds.maxY - radiusY || centerY + vy < radiusY) {
            vy *= -1
            if (vy in -0.1..0.1) {
                vy = 0.0
                onGround = true
            }
        }
    }
}

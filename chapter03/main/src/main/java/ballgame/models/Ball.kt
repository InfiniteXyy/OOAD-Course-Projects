package ballgame.models

import ballgame.models.shapes.Collisible
import ballgame.physics.Newton
import ballgame.physics.Newton.G
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse
import javafx.scene.shape.Shape

class Ball(px: Double, py: Double) : Ellipse(px, py, 10.0, 10.0) {
    val vxProperty = SimpleDoubleProperty(0.0)
    val vyProperty = SimpleDoubleProperty(0.0)
    var vx: Double
        set(value) {
            vxProperty.set(value)
        }
        get() {
            return vxProperty.get()
        }

    var vy: Double
        set(value) {
            vyProperty.set(value)
        }
        get() {
            return vyProperty.get()
        }

    var onGround = false

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
        shapes.forEach { shape ->
            // 先判断是否碰撞
            // 再根据碰撞的切面改变速
            if (shape is Collisible && shape.isCollide(this)) {
                val data = shape.getAfterCollideSpeed(this)
                vx = data.first * Newton.lost
                vy = data.second * Newton.lost
            }
        }
    }

    fun checkBorderCollisions(maxX: Double, maxY: Double) {
        if (centerX + vx >= maxX - radiusX || centerX + vx <= radiusX) {
            vx *= -Newton.lost
        }
        if (centerY + vy >= maxY - radiusY || centerY + vy < radiusY) {
            vy *= -Newton.lost
            if (vy in -0.1..0.1) {
                vy = 0.0
                onGround = true
            }
        }
    }

    override fun toString(): String {
        return "Ball(x=$centerX, y=$centerY, vx=$vx, vy=$vy)"
    }


}

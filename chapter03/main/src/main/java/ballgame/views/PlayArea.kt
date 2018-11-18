package ballgame.views

import ballgame.controllers.Store
import ballgame.models.Ball
import javafx.animation.Animation
import javafx.animation.AnimationTimer
import tornadofx.*

import javafx.beans.property.SimpleLongProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class PlayArea : View() {
    private val ball = Ball(4.0, 0.1)
    private val rec = Rectangle(60.0, 120.0, 30.0, 30.0)
    val store: Store by inject()

    override val root = pane {
        children.addAll(ball, rec)
        style {
            backgroundColor += Color.WHITE
        }
        val animationTimer = object : AnimationTimer() {
            val lastUpdateTime = SimpleLongProperty(0)

            override fun handle(now: Long) {
                if (lastUpdateTime.get() > 0) {
                    val elapsedTime = now - lastUpdateTime.get()
                    ball.updatePosition(elapsedTime)
                    ball.checkCollisions(boundsInLocal)
                }
                lastUpdateTime.set(now)
            }
        }
        animationTimer.start()
    }

}
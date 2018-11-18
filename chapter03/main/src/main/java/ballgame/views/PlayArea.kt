package ballgame.views

import ballgame.models.Ball
import tornadofx.*

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.geometry.Bounds
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class PlayArea : View() {
    private val ball = Ball(4.0, 0.1)
    private val rec = Rectangle(60.0, 120.0, 30.0, 30.0)

    override val root = pane {
        children.addAll(ball, rec)
        style {
            backgroundColor += Color.WHITE
        }
        val timeline = Timeline(
            KeyFrame(
                20.0.millis,
                EventHandler {
                    ball.move()
                    ball.testCollide(boundsInLocal)
                }
            )
        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
    }

}
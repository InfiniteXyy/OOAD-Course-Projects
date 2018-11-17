package ballgame.views

import javafx.animation.Interpolator
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse
import tornadofx.*

class PlayArea : View() {
    private val myEllipse = Ellipse(50.0, 50.0, 20.0, 20.0)
    var left = false

    init {
        myEllipse.fill = Color.DEEPPINK
    }

    override val root = vbox {
        alignment = Pos.CENTER
        fitToParentSize()
        button("Animate").action {
            left = !left
            sequentialTransition {
                timeline {
                    keyframe(0.75.seconds) {
                        keyvalue(
                            myEllipse.translateXProperty(),
                            if (left) 300.0 else 20.0,
                            interpolator = Interpolator.EASE_BOTH
                        )
                    }
                }
            }
        }
        pane {
            add(myEllipse)
        }
    }
}
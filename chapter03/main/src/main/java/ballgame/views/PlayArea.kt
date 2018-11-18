package ballgame.views


import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.MapConfig
import javafx.animation.AnimationTimer
import javafx.beans.property.BooleanProperty
import javafx.scene.input.KeyEvent
import javafx.scene.shape.Rectangle
import tornadofx.*


class PlayArea : View() {
    private val map = MapConfig()
    private val rec = Rectangle(60.0, 120.0, 30.0, 30.0)
    private val store: Store by inject()

    override val root = pane {
        addClass(Styles.playDesk)
        val ball = map.ball
        children.addAll(ball, rec)

        // Main game
        val animationTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                ball.updatePosition(1)
                ball.checkCollisions(boundsInLocal)
            }
        }

        // Game start listener
        store.gameRunning.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {
                animationTimer.start()
            } else {
                animationTimer.stop()
            }
        }
    }

    init {
        keyboard {
            addEventHandler(KeyEvent.KEY_PRESSED) {
                println(it.code)
            }
        }
    }
}
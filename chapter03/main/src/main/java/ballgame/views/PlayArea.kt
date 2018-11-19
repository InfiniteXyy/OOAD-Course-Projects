package ballgame.views


import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.MapConfig
import javafx.animation.AnimationTimer
import javafx.beans.property.BooleanProperty
import javafx.beans.property.ObjectProperty
import javafx.scene.input.KeyEvent
import javafx.scene.shape.Shape
import tornadofx.*


class PlayArea : View() {
    private val map = MapConfig()
    private val store: Store by inject()
    private var lastShape: Shape? = null


    override val root = pane {
        toggleClass(Styles.editDesk, store.mapEditing)
        toggleClass(Styles.playDesk, store.mapEditing.not())

        // add ball
        val ball = map.ball
        children.add(ball)

        val animationTimer = object : AnimationTimer() {
            // Main game refresh per frame
            override fun handle(now: Long) {
                ball.updatePosition(1)
                ball.checkCollisions(boundsInLocal)
            }
        }

        setOnMouseMoved {
            with(store.editShapeType.value) {
                this?.followMouse(it)
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

        store.editShapeType.addListener { ob ->
            if (ob is ObjectProperty<*> && ob.value != null) {
                children.add(ob.value as Shape)
                children.remove(lastShape)
                lastShape = ob.value as Shape
            }
        }

        store.mapEditing.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {

            } else {
                children.remove(lastShape)
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
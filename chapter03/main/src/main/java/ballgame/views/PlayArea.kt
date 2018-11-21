package ballgame.views


import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.Flipper
import ballgame.models.shapes.Arrow
import ballgame.models.shapes.Draggable
import javafx.animation.AnimationTimer
import javafx.beans.property.BooleanProperty
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import javafx.scene.shape.Shape
import tornadofx.*


class PlayArea : View() {
    private val store: Store by inject()
    private val map = store.map
    private val ballVelocityLine = Arrow()
    private val flipper = Flipper()

    override val root = pane {
        store.mapEditing.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {
                addClass("editDesk")
                removeClass(Styles.playDesk)
            } else {
                addClass(Styles.playDesk)
                removeClass("editDesk")
            }
        }
        // add ball
        val ball = map.ball
        children.add(ball)
        children.add(flipper)
        children.addAll(map.shapes)


        val animationTimer = object : AnimationTimer() {
            // Main game refresh per frame
            override fun handle(now: Long) {
                ball.checkBorderCollisions(boundsInLocal)
                ball.checkCollisions(map.shapes)
                ball.updatePosition(1)
                if (flipper.active) {
                    flipper.updatePosition(1)
                }
            }
        }


        // move new shape
        setOnMouseMoved {
            if (store.mapEditing.value && store.draggingShape != null) {
                with(store.draggingShape as Shape) {
                    if (!children.contains(this))
                        children.add(this)
                    (this as Draggable).followMouse(it)
                }
            }
        }


        // if edit mode
        // put shape on map
        setOnMousePressed {
            if (store.mapEditing.value && store.draggingShape != null) {
                if (it.isPrimaryButtonDown) {
                    map.shapes.add(store.draggingShape as Shape)
                    store.draggingShape!!.adjustBox()
                } else {
                    children.remove(store.draggingShape as Shape)
                }
                store.setEditShape(null)
            }
        }

        // move shape
        setOnMouseDragged {
            if (store.mapEditing.value) {
                if (it.target is Draggable) {
                    val shape: Draggable = it.target as Draggable
                    shape.followMouse(it)
                }
            }
        }


        // Game start listener
        store.gameRunning.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {
                animationTimer.start()
                children.remove(ballVelocityLine)
            } else {
                animationTimer.stop()
                children.add(ballVelocityLine)
                with(ballVelocityLine) {
                    startX = ball.centerX
                    startY = ball.centerY
                    endX = ball.centerX + ball.vx * 10
                    endY = ball.centerY + ball.vy * 10
                }
            }
        }
    }

    val onKeyPressHandler: EventHandler<KeyEvent> = EventHandler {
        flipper.active = true
    }
    val onKeyReleaseHandler: EventHandler<KeyEvent> = EventHandler {
        flipper.active = false
    }

}
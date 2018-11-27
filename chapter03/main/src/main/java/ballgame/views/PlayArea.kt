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
    private val world = store.world
    private val ball = world.ball
    private val shapes = world.shapes
    private val ballVelocityLine = Arrow().apply {
        this.startXProperty().bind(ball.centerXProperty())
        this.startYProperty().bind(ball.centerYProperty())
        this.endXProperty().bind(ball.centerXProperty() + ball.vxProperty * 7)
        this.endYProperty().bind(ball.centerYProperty() + ball.vyProperty * 7)
    }

    var leftFlipperOn = false
    var rightFlipperOn = false

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

        // bind shapes to panel children
        bindChildren(shapes) { it }

        store.needSpeedLine.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {
                children.add(ballVelocityLine)
            } else {
                children.remove(ballVelocityLine)
            }
        }
        val animationTimer = object : AnimationTimer() {
            // Main game refresh per frame
            override fun handle(now: Long) {
                ball.checkBorderCollisions(360.0, 523.0)
                ball.checkCollisions(shapes)
                ball.updatePosition(1)
                shapes.forEach {
                    if (it is Flipper) {
                        if (it.isLeft && leftFlipperOn) it.updatePosition(1)
                        if (!it.isLeft && rightFlipperOn) it.updatePosition(1)
                    }
                }
            }
        }

        // if edit mode
        // move new shape
        setOnMouseMoved {
            if (store.mapEditing.value) {
                val draggedShape = store.draggingShape
                if (draggedShape != null) {
                    if (!shapes.contains(draggedShape as Shape)) shapes.add(draggedShape as Shape)
                    (draggedShape as Draggable).followMouse(it)
                }
            }
        }


        // if edit mode
        // put shape on world
        setOnMousePressed {
            if (store.mapEditing.value) {
                val draggedShape = store.draggingShape
                if (draggedShape != null) {
                    if (it.isPrimaryButtonDown) {
                        draggedShape.adjustBox()
                    } else {
                        shapes.remove(draggedShape as Shape)
                    }
                    store.setEditShape(null)
                }
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
            } else {
                animationTimer.stop()
            }
        }
    }

    val onKeyPressHandler: EventHandler<KeyEvent> = EventHandler {
        if (it.code.getName() == store.leftFlipperKey.value) {
            leftFlipperOn = true
        } else if (it.code.getName() == store.rightFlipperKey.value) {
            rightFlipperOn = true
        }
    }
    val onKeyReleaseHandler: EventHandler<KeyEvent> = EventHandler {
        if (it.code.getName() == store.leftFlipperKey.value) {
            leftFlipperOn = false
        } else if (it.code.getName() == store.rightFlipperKey.value) {
            rightFlipperOn = false
        }
    }

}
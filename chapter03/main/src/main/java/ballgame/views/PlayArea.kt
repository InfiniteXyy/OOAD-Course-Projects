package ballgame.views


import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.shapes.DraggableShape
import javafx.animation.AnimationTimer
import javafx.beans.property.BooleanProperty
import javafx.scene.shape.Shape
import tornadofx.*


class PlayArea : View() {
    private val store: Store by inject()
    private val map = store.map


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
        children.addAll(map.shapes)


        val animationTimer = object : AnimationTimer() {
            // Main game refresh per frame
            override fun handle(now: Long) {
                ball.updatePosition(1)
                ball.checkBorderCollisions(boundsInLocal)
                ball.checkCollisions(map.shapes)
            }
        }


        // move new shape
        setOnMouseMoved {
            if (store.mapEditing.value && store.draggingShape != null) {
                with(store.draggingShape as Shape) {
                    if (!children.contains(this))
                        children.add(this)
                    (this as DraggableShape).followMouse(it)
                }
            }
        }


        // if edit mode
        // put shape on map
        setOnMousePressed {
            if (store.mapEditing.value && store.draggingShape != null) {
                if (it.isPrimaryButtonDown) {
                    map.shapes.add(store.draggingShape as Shape)
                } else {
                    children.remove(store.draggingShape as Shape)
                }
                store.setEditShape(null)
            }
        }

        // move shape
        setOnMouseDragged {
            if (store.mapEditing.value) {
                if (it.target is DraggableShape) {
                    val shape: DraggableShape = it.target as DraggableShape
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
}
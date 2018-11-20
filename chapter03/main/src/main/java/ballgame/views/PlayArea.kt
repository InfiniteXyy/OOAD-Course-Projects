package ballgame.views


import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.DraggableShape
import javafx.animation.AnimationTimer
import javafx.beans.property.BooleanProperty
import javafx.scene.shape.Shape
import tornadofx.*


class PlayArea : View() {
    private val store: Store by inject()
    private val map = store.map


    override val root = pane {
        toggleClass(Styles.editDesk, store.mapEditing)
        toggleClass(Styles.playDesk, store.mapEditing.not())

        // add ball
        val ball = map.ball
        children.add(ball)
        children.addAll(map.shapes)


        val animationTimer = object : AnimationTimer() {
            // Main game refresh per frame
            override fun handle(now: Long) {
                ball.updatePosition(1)
                ball.checkCollisions(boundsInLocal)
            }
        }


        // Edit mouse listener
        setOnMouseMoved {
            if (store.mapEditing.value && store.draggingShape != null) {
                with(store.draggingShape as Shape) {
                    if (!children.contains(this))
                        children.add(this)
                    (this as DraggableShape).followMouse(it)
                }

            }
        }

        // put shape on map
        setOnMousePressed {
            if (store.mapEditing.value && store.draggingShape != null) {
                map.shapes.add(store.draggingShape as Shape)
                store.setEditShape(null)
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
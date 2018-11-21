package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import javafx.beans.property.BooleanProperty
import javafx.stage.StageStyle
import tornadofx.*

class MainView : View("Play the ball") {
    private val store: Store by inject()
    private val toolboxView = ToolboxView()
    private val playArea = PlayArea()
    override val root = borderpane {

        addClass(Styles.root)
        top {
            menubar {
                menu("Game") {
                    item("New...")
                    item("Open...")
                    item("Save")
                }
                menu("Map") {
                    item(stringBinding(store.mapEditing) {
                        if (value) "Start Game" else "Edit map"
                    }).action {
                        store.toggleMapEditing()
                    }
                    item("Import map")
                    item("Map Setting")
                }
                menu("Help") {
                    item("About")
                    item("Check for updates...")
                }
            }
        }
        center {
            onKeyPressed = playArea.onKeyPressHandler
            onKeyReleased = playArea.onKeyReleaseHandler
            add(playArea)
        }
        bottom(Footer::class)
    }

    init {
        store.mapEditing.addListener { ob ->
            if (ob is BooleanProperty && ob.value) {
                toolboxView.openWindow(StageStyle.UTILITY).apply {
                    // 使工具栏紧贴着主要窗口
                    this?.x = primaryStage.x + primaryStage.width
                    this?.y = primaryStage.y + 35
                }
            } else {
                toolboxView.close()
            }
        }
    }


    fun toggleSettingBallWindow() {
        tornadofx.find<BallSettingDialog>().rebind()
        openInternalWindow<BallSettingDialog>()
    }
}
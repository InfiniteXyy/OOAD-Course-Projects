package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import ballgame.models.shapes.Draggable
import javafx.beans.property.BooleanProperty
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.FileChooser
import javafx.stage.StageStyle
import tornadofx.*
import utils.ConfigManager
import java.util.*

class MainView : View("Play the ball") {
    private val store: Store by inject()
    private val toolboxView = ToolboxView()
    private val playArea = PlayArea()
    var extFilter = FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")

    override val root = borderpane {

        addClass(Styles.root)
        top {
            menubar {
                menu("Game") {
                    item("New...").action {
                        confirm("reset", "It will remove all your game data!") {
                            store.world.reset(true)
                        }
                    }
                    item("Key binding").action {
                        KeyBindingDialog().openModal(owner = null)
                    }
                }
                menu("Map") {
                    item(stringBinding(store.mapEditing) {
                        if (value) "Start Game" else "Edit world"
                    }).action {
                        store.toggleMapEditing()
                    }
                    item("Import...").action {
                        val files = chooseFile(filters = arrayOf(extFilter))
                        if (files.isNotEmpty()) {
                            val data = ConfigManager.loadConfig(files[0].absolutePath)
                            store.world.loadShapes(data)
                        }
                    }
                    item("Save...").action {
                        val files = chooseFile(filters = arrayOf(extFilter), mode = FileChooserMode.Save)
                        if (files.isNotEmpty()) {
                            ConfigManager.saveConfig(files[0].absolutePath, store.world.shapeConfig)
                        }
                    }
                }
                menu("Help") {
                    item("About").action {
                        alert(
                            Alert.AlertType.NONE,
                            "About",
                            "Here is ball game by xyy and qzy\nⒸCopyright 2018.",
                            ButtonType("OK")
                        )
                    }
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
                    this?.setOnCloseRequest {
                        store.toggleMapEditing()
                    }
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
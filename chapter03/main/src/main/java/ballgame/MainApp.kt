package ballgame

import javafx.stage.Stage
import tornadofx.*
class MainApp: App(MainView::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    launch<MainApp>(args)
}
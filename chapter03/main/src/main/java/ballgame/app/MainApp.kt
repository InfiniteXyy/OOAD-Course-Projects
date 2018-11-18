package ballgame.app

import ballgame.views.MainView
import javafx.stage.Stage
import tornadofx.*

class MainApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}
package ballgame

import tornadofx.*

class MainView : View() {
    private val desk = GameDesk()
    private val statusBar = StatusBar()
    override val root = vbox {
        setMinSize(800.0, 600.0)
        menubar {
            menu("File") {
                menu("Connect") {
                    item("Facebook")
                    item("Twitter")
                }
                item("Save")
                item("Quit")
            }
            menu("Edit") {
                item("Copy")
                item("Paste")
            }
        }
        pane {
            add(desk)
            add(statusBar)
        }
    }
}
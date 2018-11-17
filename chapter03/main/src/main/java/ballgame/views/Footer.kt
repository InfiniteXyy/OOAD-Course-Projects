package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import tornadofx.*

class Footer : View() {
    val store: Store by inject()
    override val root = hbox {
        addClass(Styles.footer)
        label("准备就绪")
        hbox {
            togglegroup {
                togglebutton("button1")
                togglebutton("button2")
            }
        }
    }
}
package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class BallSettingDialog : View("Ball Setting") {
    private val store: Store by inject()
    private val vx = SimpleStringProperty()
    private val vy = SimpleStringProperty()
    private val isWrong = SimpleBooleanProperty()

    override val root = borderpane {
        addClass(Styles.settingDialog)
        top {
            label("Settings").setId(Styles.title)
        }
        center {
            vbox {
                style {
                    spacing = 20.px
                }
                hbox {
                    label("vx: ")
                    textfield {
                        bind(vx)
                    }
                }
                hbox {
                    label("vy: ")
                    textfield {
                        bind(vy)
                    }
                }
            }
        }
        bottom {
            hbox {
                button("Save").action {
                    val tempVx = vx.value.toDoubleOrNull()
                    val tempVy = vy.value.toDoubleOrNull()
                    if (tempVx != null && tempVy != null) {
                        store.world.ball.vx = tempVx
                        store.world.ball.vy = tempVy
                        isWrong.set(false)
                        close()
                    } else {
                        isWrong.set(true)
                    }
                }
                label(stringBinding(isWrong) { if (value) "Wrong Number" else "" }).setId(Styles.wrongTip)
            }

        }
    }

    fun rebind() {
        vx.set(String.format("%.2f", store.world.ball.vx))
        vy.set(String.format("%.2f", store.world.ball.vy))
    }
}

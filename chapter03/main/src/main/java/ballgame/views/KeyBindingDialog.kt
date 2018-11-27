package ballgame.views

import ballgame.app.Styles
import ballgame.controllers.Store
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class KeyBindingDialog : View("Key binding") {
    private val store: Store by inject()
    private val onSetting = SimpleStringProperty("")

    override val root = borderpane {
        addClass(Styles.settingDialog)
        top {
            label("Keys").setId(Styles.title)
        }
        center {

            setOnKeyPressed {
                if (onSetting.value != "") {
                    if (onSetting.value == "left") {
                        store.leftFlipperKey.set(it.code.getName())
                    } else {
                        store.rightFlipperKey.set(it.code.getName())
                    }
                    onSetting.set("")
                }
            }
            vbox {
                style {
                    spacing = 20.px
                }
                hbox {
                    label("Left flipper: ")
                    button(stringBinding(store.leftFlipperKey) { value }).action {
                        onSetting.set("left")
                    }
                }
                hbox {
                    label("Right flipper: ")
                    button(stringBinding(store.rightFlipperKey) { value }).action {
                        onSetting.set("right")
                    }
                }
            }
        }
        bottom {
            button("close") {
                action { close() }
            }
        }
    }

}

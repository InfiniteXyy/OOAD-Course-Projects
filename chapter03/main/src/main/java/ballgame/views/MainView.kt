package ballgame.views

import ballgame.app.Styles
import tornadofx.*

class MainView : View("玩个球") {
    override val root = borderpane {
        addClass(Styles.root)
        top {
            menubar {
                menu("游戏") {
                    item("新建")
                    item("打开")
                    item("保存")
                }
                menu("地图") {
                    item("新建游戏地图")
                    item("导入地图")
                    item("设置")
                }
                menu("帮助") {
                    item("关于")
                    item("检查更新")
                }
            }
        }
        center(PlayArea::class)
        bottom(Footer::class)
    }
}
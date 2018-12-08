## Ball Game 🏀

## 弹球游戏

### 用例分析

> 写一个2D弹球游戏实现效果

### 实现效果

##### 编辑模式

- gizmo以占据方块的形式，用户只能将gizmo 放置在(0,0), (0,1), (0,2)上，不能放在线的交点
- 用户可拖动gizmo，在拖动结束时，gizmo自动吸附到方块中
- 用户可移除已在playArea中的gizmo
- 用户可加入任何右侧创建栏中的gizmo组件到左侧游戏区域（以点击的方式）
- 用户可设定当前小球的速度

##### 游戏模式

- 小球在碰撞到gizmo后会反弹，并且会损失一定比例的速度
- 用户可随时控制游戏的启动和暂停
- 用户可重置小球的速度和位置
- 用户可实时查看小球的速度
- 用户可通过键盘操控挡板，小球碰撞到挡板会反弹
- 用户可绑定用以控制挡板的按键

##### 其他

- 用户可保存当前所有配置
- 用户可读取之前保存的场景配置
- 用户可通过new新建一个新的空白场景
- 用户可通过Help查看项目信息

### 实现结构

- 前后端均为 `kotlin`实现，其中 `kotlin`使用 `javaFX`+`tornadofx`作为 UI 框架

- 项目开发环境是 `Java 8`，在更高级的版本中（`Java 11`）中由于JavaFX从JDK中移除了，因此需要使用`OpenJFX`作为项目以来，不推荐。

### 快速启动

你需要 `build java` 或者直接通过ide运行java

如果你想用 `gradle` 运行 build

```bash
./gradlew build
# 运行gui
java -jar build/libs/ball_game-1.0-SNAPSHOT.jar
```

如果你想要通过IDE运行java，入口位于 `ballgame.app.MainApp`

### 截图
<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter03/screenshots/screenshot1.jpg">

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter03/screenshots/screenshot2.jpg">

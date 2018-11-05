## Twenty-one Game 💸

## 21 点游戏

### 用例分析
**题目描述**
> 写一个 21 点游戏。

### 实现效果

基本功能

- 设置游戏玩家个数
- 21点游戏核心功能
  - 判断胜负
  - 抽牌、弃牌
  - 双倍押金
  - A自动转化为 1 或 11

附加功能
- 钱包功能


### 实现结构
- 前后端均为 `Java` 实现，其中 `Java` 使用 `Swing` 作为 UI 框架

项目开发环境是 `Java 11`，版本要求是`Java 8`

### 快速启动
你需要 `build java` 或者直接通过ide运行java

项目有两个入口 **gui** 和 **cli** 分别对应`java swing`和命令行游戏，gradle打包的默认入口是 **gui**

如果你想用 `gradle` 运行 build
```bash
./gradlew build
# 运行gui
java -jar build/libs/OOAD-0.1.jar 
```
如果你想要通过IDE运行java，两个入口分别位于 `gui.Main` 和 `cli.GameCli`



### 截图

配置界面

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter02/screenshots/config.png">

游戏界面（A可以自动识别为1或11）

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter02/screenshots/gaming2.png">

结果界面

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter02/screenshots/result.png">

金钱界面

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter02/screenshots/money.png">

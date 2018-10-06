## Ant Simulator 🐜

## 蚂蚁模拟器

### 用例分析

**题目描述**

> - 有一根 300 厘米的细木杆，在第 30 厘米、80 厘米、110 厘米、160 厘米、250 厘米这五个位置上各有一只蚂蚁。木杆很细，不能同时通过两只蚂蚁。
> - 开始时，蚂蚁的头朝左还是朝右是任意的，它们只会朝前走或调头，但不会后退。
> - 当任意两只蚂蚁碰头时，两只蚂蚁会同时调头朝相反方向走。假设蚂蚁们每秒钟可以走 5 厘米的距离。
> - 注：不允许穿越对方身体继续直行

请编写一个程序，计算各种可能情形下所有蚂蚁都离开木杆的最小时间和最大时间。

### 实现效果

基本功能

- 计算蚂蚁所有情况组合，按照步数进行排序
- 模拟蚂蚁爬行动画

附加功能

- 自定义蚂蚁个数、速度和杆子长度
- 操作动画运行

### 实现结构

- 前端页面 `electron` + `React` 实现配置和蚂蚁的可视化监控

- 后段代码 `java` 实现

- 前后端结合 `python` + `http.server`


### 快速启动
你需要
- 打包 java
- 启动 python 服务器
- 启动 React Server

如果你是 mac / linux 系统，可以运行 `install.sh` 脚本
```bash
git clone https://github.com/InfiniteXyy/OOAD-Course.git
cd OOAD-Course/chapter01
./install.sh


# run server
cd server
python3 app.py

# run web server
cd gui
yarn gui run start
```

如果你是 windows 系统，可以手动实现以上操作

### 截图

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter01/screenshots/demo.gif">

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter01/screenshots/main.png">

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter01/screenshots/running.png">

<img src="https://raw.githubusercontent.com/InfiniteXyy/OOAD-Course/master/chapter01/screenshots/stages.png">



#### Design Patterns
- [x] Observer / 观察者模式
- [x] Adapter / 适配器模式
- [x] Singleton / 单例模式
- [x] Facade / 外观模式
  - 与适配器模式的区别在于外观模式定义了新的接口，而不是一对一的适配旧的接口
- [x] Strategy / 策略模式 
  - 基于行为（面向接口）编程，让具体类去使用这些行为
  - 例如 Android 中 RecyclerView 的水平竖直排列方式，就可以传入自带的 Strategy
  `recyclerView.setLayoutManager(new LinearLayoutManager(this));`
  - 好处在于可以在运行时改变行为对象 / 可以新增新的行为
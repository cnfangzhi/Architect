# Architect

## Demo

### demo01: 面向对象六大基本原则 - 网络引擎切换
**2.1 单一职责原则：**

英文：Single Responsibility Principle

定义：就一个类而言，应该仅有一个引起它变化的原因。简单来说，一个类中应该是一组相关性很高的函数、数据的封装。

请求和缓存完全写到了一堆

小插曲：链式调用，调用方式有问题 ，比如 超时重连，超时时间 ，支持cookie 等等，更多的是关注业务逻辑

问题？  xUtils  Okhttp  Retrofit   ......   切换第三方的网络框架

**2.2 开闭原则：**

英文：Open Close Principle

定义：软件中的对象（类、模块、函数等）应该对于扩展是开放的，但是，对于修改是封闭（关闭）的。
           当软件需要变化时，应该尽量通过扩展的方式来实现变化，而不是通过修改已有的代码来实现。

**2.3 里氏替换原则：**

英文：Liskov Substitution Principle

定义：只要父类能出现的地方子类就可以出现，主要体现就是实现和继承

**2.4 依赖倒置原则：**

英文：Dependence Inversion Principle

定义：指代了一种特定的解耦形式，高层模块不依赖低层次模块的细节，说白了高层次就是不依赖细节而是依赖抽象。
高层和底层,HttpUtils 1   IHttpRequest  2 OKHttpRequest  3 XutilsHttpRequest 4   高层 1 2  底层   3 4

**2.5 接口隔离原则**：

英文：Interface Segregation Principle

定义：类间的依赖关系应该建立在最小的接口上。接口隔离原则将非常庞大、臃肿的接口拆分成为更小的和更具体的接口，这样客户将会只需要知道他们感兴趣的方法。接口隔离原则的目的是系统解开耦合，从而容易重构、更改和重新部署，让客户端依赖的接口尽可能地小。（接口拆分，单接口）

上面的这个五个原则  其实都更接口和抽象有关 （面向抽象和面向接口）

**2.6 迪米特原则：**

英文：Law of Demeter

定义：一个对象应该对其他对象有最少的了解，调用者也是比较关注。

### demo02: 基础知识扫盲 - 反射注解和泛型
### demo03: OKHttp
### demo04: 手写EventBus
### demo05: 第三方开源库 OKHttp - 文件断点下载

## 设计模式design pattern
### design01: 单例设计模式 - 强大的 Activity 管理
### design02: Builder设计模式 - 增强版 NavigationBar
### design03: 工厂设计模式 - 数据存储的特有方式
### design04: 装饰设计模式 - RecyclerView添加头部和底部
### design05: 模版设计模式 - 自己动手写 OkHttp 的 Dispatcher
### design06: 策略设计模式 - Log 日志输出策略
### design07: Adapter设计模式 - 打造通用的 IndicatorView
### design08: 观察者设计模式 - 观察数据的插入
### design09: 代理设计模式 - 实现 Retrofit 的 create
### design10: 原型设计模式 - 订单查询拆分
### design11: 迭代器设计模式 - 构建通用 BottomTabNavigationBar
### design12: 责任链设计模式 和 外观设计模式
### design13: 设计模式 - 23种模式总结(前篇)
### design14: 设计模式 - 23种模式总结(后篇)

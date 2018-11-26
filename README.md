# PlaceHolderView
* 统一封装、管理LoadingView，EmptyView，ErrorView等等
* 实现统一注册， 多处调用

## usage
```java
           1.注册 ：
             new PlaceHolderView.Config()
                  .addPlaceHolder(ErrorPlaceHolder.class, EmptyPlaceHolder.class, LoadingPlaceHolder.class)
                  .build().register(this);
           2.展示 ：
             PlaceHolderPool.showPlaceHolder(LoadingPlaceHolder.class);
             PlaceHolderPool.showPlaceHolder(EmptyPlaceHolder.class);
             et...

           todo list :
           1. 占位符，点击事件callback的回调还没有加入。 比如一些retry操作
 ```


## 参考
* https://github.com/KingJA/LoadSir
> 缺点 ：
* 线程使用不规范
* 内存泄漏代码
* PlaceHolderView.onDetach调用时机不准确

## Listener
MIT License
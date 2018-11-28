# PlaceHolderView
* 统一封装、管理LoadingView，EmptyView，ErrorView等等
* 实现统一注册， 多处调用

## usage
```java
  1.注册 ：
       new PlaceHolderView.Config()
               .addPlaceHolder(ErrorPlaceHolder.class, EmptyPlaceHolder.class, LoadingPlaceHolder.class)
               .install();

  2.在具体的View/Activity/fragment上绑定 ：
       PlaceHolderView.getDefault().bind(View)

  3.通过PlaceHolderManager来控制Show/Hide。
       PlaceHolderManager.showPlaceHolder(EmptyPlaceHolder.class);
       et...

  4.可以在View/Activity/fragment生命周期结束时，释放
       PlaceHolderManager.release();
 ```


## 参考
* https://github.com/KingJA/LoadSir
> 缺点 ：
* 线程使用不规范
* 内存泄漏代码
* PlaceHolderView.onDetach调用时机不准确
* PlaceHolderLayout 移除需调用所有callback的detach， 执行释放操作

## Listener
MIT License
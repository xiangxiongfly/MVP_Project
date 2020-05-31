## MVP+RxJava+Retrofit



### 整体结构

```
├── base
│   ├── BaseActivity.java
│   ├── BaseApplication.java
│   ├── BaseMvpActivity.java
│   ├── BaseObserver.java
│   ├── BasePresenter.java
│   ├── BaseResponse.java
│   └── interfaces
│       ├── IPresenter.java
│       └── IView.java
├── bean
│   └── ArticlesBean.java
├── http
│   ├── ApiException.java
│   ├── ExceptionHandler.java
│   └── HttpManager.java
└── mvp
    ├── ApiService.java
    ├── ArticlesActivity.java
    ├── ArticlesContract.java
    └── ArticlesPresenter.java
```




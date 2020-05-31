package com.example.myapplication.base;

import com.example.myapplication.base.interfaces.IPresenter;
import com.example.myapplication.base.interfaces.IView;
import com.example.myapplication.http.HttpManager;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> mReference;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V view) {
        mReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
        unSubscribe();
    }

    @Override
    public boolean isViewAttached() {
        return mReference != null && mReference.get() != null;
    }

    @Override
    public V getView() {
        return mReference.get();
    }

    /**
     * 添加订阅
     */
    protected void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        BaseObserver baseObserver = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        mCompositeDisposable.add(baseObserver);
    }

    /**
     * 取消订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    protected <T> T create(Class<T> clazz) {
        return HttpManager.getInstance().getRetrofit().create(clazz);
    }
}

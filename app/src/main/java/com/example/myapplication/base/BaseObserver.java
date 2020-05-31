package com.example.myapplication.base;

import com.example.myapplication.base.interfaces.IView;
import com.example.myapplication.http.ApiException;
import com.example.myapplication.http.ExceptionHandler;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {

    private IView baseView;

    public BaseObserver() {

    }

    public BaseObserver(IView baseCView) {
        this.baseView = baseCView;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (baseView != null) {
//            baseView.showLoading();
//        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
//        if (baseView != null) {
//            baseView.hideLoading();
//        }

        int errorCode = baseResponse.getErrorCode();
        String errorMsg = baseResponse.getErrorMsg();
        if (errorCode == 0 || errorCode == 200) {
            onSuccess(baseResponse);
        } else {
            onError(new ApiException(errorCode, errorMsg));
        }
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = ExceptionHandler.handleException(e);
        onError(errorMsg);
    }

    protected abstract void onSuccess(BaseResponse<T> data);

    protected abstract void onError(String errorMsg);

    @Override
    public void onComplete() {
//        if (baseView != null) {
//            baseView.hideLoading();
//        }
    }
}

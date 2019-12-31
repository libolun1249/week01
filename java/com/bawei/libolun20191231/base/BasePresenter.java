package com.bawei.libolun20191231.base;

import java.lang.ref.WeakReference;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:01
 * @description P层基类
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    public M model;
    private WeakReference<V> weakReference;

    public BasePresenter() {
        model = initModel();
    }

    protected abstract M initModel();

    protected void onAttch(V v) {
        //绑定
        weakReference = new WeakReference<>(v);
    }

    protected void ondeach() {
        //解绑
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected V getview() {
        return weakReference.get();
    }

}

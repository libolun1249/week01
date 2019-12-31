package com.bawei.libolun20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bawei.libolun20191231.presenter.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:01
 * @description Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    Unbinder unbinder;
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layout());
        //绑定
        unbinder = ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter!=null){
            presenter.onAttch(this);
        }
        initData();
        initView();

    }

    protected abstract int Layout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
        if (presenter!=null){
            presenter.ondeach();
        }


    }
}

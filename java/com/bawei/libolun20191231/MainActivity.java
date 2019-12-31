package com.bawei.libolun20191231;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.libolun20191231.adapter.MyAdapter;
import com.bawei.libolun20191231.api.Api;
import com.bawei.libolun20191231.base.BaseActivity;
import com.bawei.libolun20191231.bean.HomeBean;
import com.bawei.libolun20191231.contract.IContract;
import com.bawei.libolun20191231.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IContract.IView {


    @BindView(R.id.tv_fx)
    TextView tvFx;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int Layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        tvFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CodeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getData(Api.BASEURL);

    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    public void onsuccess(HomeBean bean) {
        MyAdapter myAdapter = new MyAdapter(this, bean.result);
        rv.setAdapter(myAdapter);
    }

    @Override
    public void onfailuer(Throwable throwable) {

    }

}

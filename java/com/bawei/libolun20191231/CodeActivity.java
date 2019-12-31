package com.bawei.libolun20191231;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.libolun20191231.base.BaseActivity;
import com.bawei.libolun20191231.presenter.Presenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends BaseActivity {


    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.bt_code)
    Button btCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.wx_code)
    Button wxCode;
    @BindView(R.id.qq_code)
    Button qqCode;

    @Override
    protected int Layout() {
        return R.layout.activity_code;
    }

    @Override
    protected void initView() {
        ivCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeBitmap(ivCode, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(CodeActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(CodeActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);

    }

    @Override
    protected Presenter initPresenter() {
        return null;
    }


    @OnClick({R.id.wx_code, R.id.qq_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wx_code:
                EventBus.getDefault().register("微信");
                break;
            case R.id.qq_code:
                EventBus.getDefault().register("QQ");
                break;
        }
    }

    @OnClick(R.id.bt_code)
    public void onViewClick(){

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getString(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEdittext(EditText editText) {
        Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

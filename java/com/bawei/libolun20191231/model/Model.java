package com.bawei.libolun20191231.model;

import com.bawei.libolun20191231.bean.HomeBean;
import com.bawei.libolun20191231.contract.IContract;
import com.bawei.libolun20191231.utils.OkhttpUtil;
import com.google.gson.Gson;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:03
 * @description M层
 */
public class Model implements IContract.IModel {
    @Override
    public void getData(String url, IModelCallback callback) {
        OkhttpUtil.getInstance().doget(url, new OkhttpUtil.OkhttpCallback() {
            @Override
            public void onsuccess(String result) {
                HomeBean homeBean = new Gson().fromJson(result, HomeBean.class);
                callback.onsuccess(homeBean);
            }

            @Override
            public void onfailuer(Throwable throwable) {
                callback.onfailuer(throwable);
            }
        });
    }
}

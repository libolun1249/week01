package com.bawei.libolun20191231.presenter;

import com.bawei.libolun20191231.base.BasePresenter;
import com.bawei.libolun20191231.base.IBaseModel;
import com.bawei.libolun20191231.base.IBaseView;
import com.bawei.libolun20191231.bean.HomeBean;
import com.bawei.libolun20191231.contract.IContract;
import com.bawei.libolun20191231.model.Model;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:03
 * @description P层
 */
public class Presenter extends BasePresenter<Model, IContract.IView> implements IContract.IPresenter {

    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void doget(String url) {
        model.getData(url, new IContract.IModel.IModelCallback() {
            @Override
            public void onsuccess(HomeBean bean) {
                getview().onsuccess(bean);
            }

            @Override
            public void onfailuer(Throwable throwable) {
                getview().onfailuer(throwable);
            }
        });
    }


}

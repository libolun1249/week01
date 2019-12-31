package com.bawei.libolun20191231.contract;

import com.bawei.libolun20191231.base.IBaseModel;
import com.bawei.libolun20191231.base.IBaseView;
import com.bawei.libolun20191231.bean.HomeBean;

/**
 * @author 李博伦
 * @createTime 2019/12/31 9:04
 * @description 契约类
 */
public interface IContract {

    interface IModel extends IBaseModel {
        void getData(String url, IModelCallback callback);

        interface IModelCallback {
            void onsuccess(HomeBean bean);

            void onfailuer(Throwable throwable);
        }
    }

    interface IPresenter {
        void getData(String url);

    }

    interface IView extends IBaseView {
        void onsuccess(HomeBean bean);

        void onfailuer(Throwable throwable);
    }
}

package com.dashang.education.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dashang.education.mvp.base.BaseView;
import com.dashang.education.mvp.presenter.BasePresenter;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
        //activity未销毁才能关闭
        if (getBaseAct() != null) {

        }
    }

    protected abstract T initPresenter();
}

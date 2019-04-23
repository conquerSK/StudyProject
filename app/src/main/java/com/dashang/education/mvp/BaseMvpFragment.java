package com.dashang.education.mvp;

import android.widget.Toast;

import com.dashang.education.mvp.base.BaseView;
import com.dashang.education.mvp.presenter.BasePresenter;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected T mPresenter;

    @Override
    protected void initData() {
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    protected abstract T initPresenter();

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
        //activity未销毁才能关闭
        if (isVisible()) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}

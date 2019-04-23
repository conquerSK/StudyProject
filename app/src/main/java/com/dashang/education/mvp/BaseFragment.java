package com.dashang.education.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected View mRootView;
    private boolean isInit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit) {
            initView(view, savedInstanceState);
            initData();
            initListener();
            isInit = true;
        } else {
            updateView();
        }
    }

    //更新视图
    protected void updateView() {
// Do nothing
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化UI
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view,@Nullable Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initListener();
}

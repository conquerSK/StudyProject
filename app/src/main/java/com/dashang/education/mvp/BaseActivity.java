package com.dashang.education.mvp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dashang.education.R;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseActivity baseAct;
    private Toolbar mToolbar;
    private TextView tvTitle;

    public BaseActivity getBaseAct() {
        return baseAct;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        baseAct = this;
        initContentView();
        initView(savedInstanceState);
        initData();
    }

    protected abstract void initContentView();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initToolbar();
    }

    /**
     * 适配状态栏和标题栏颜色一致
     */
    protected void setStatusBarColor() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = (ViewGroup) findViewById(android.R.id.content);

            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
            statusBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            systemContent.getChildAt(0).setFitsSystemWindows(false);

            systemContent.addView(statusBarView, 0, lp);
        }
    }

    public int getStatusBarHeight() {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    /**
     * 全屏保留状态栏文字
     */
    protected void fullScreenHaveText() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseAct = null;
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.nav_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        tvTitle = findViewById(R.id.tv_title);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 隐藏返回按钮
     */
    protected void hideBackBtn() {
        if (mToolbar == null) {
            return;
        }
        mToolbar.setNavigationIcon(null);
    }

    protected void setTvTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /**
     * //设置状态栏白底黑字 目前只有android原生6.0以上支持修改状态栏字体
     */
    protected void setStatusBarColorDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

package com.dashang.education.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dashang.education.R;
import com.dashang.education.util.DevicesUtils;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mPromptTitleTV;//对话框标题
    private TextView mPromptContentTV;
    private TextView mLeftButton, mRightButton;
    protected OnButtonClickListener mClickListener;
    private CharSequence mPromptContent = "提示内容";
    private CharSequence mPromptTitle = "提示";
    private static final double widthScale = 0.7;

    public CustomDialog(Context context, CharSequence title, CharSequence PromptContent) {
        this(context);
        mPromptTitle = title;
        mPromptContent = PromptContent;
        mPromptContentTV.setText(mPromptContent);
        mPromptTitleTV.setText(mPromptTitle);
    }

    public CustomDialog(Context context, CharSequence PromptContent) {
        this(context);
        mPromptContentTV.setText(PromptContent);
    }

    public CustomDialog(@NonNull Context context) {
        super(context, R.style.CustomDialogStyle);
        initView(context);
    }

    private void initView(Context context) {
        View mLayoutView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null);
        setContentView(mLayoutView);
        mPromptContentTV = (TextView) mLayoutView.findViewById(R.id.dialog_double_button_message);
        mPromptTitleTV = (TextView) mLayoutView.findViewById(R.id.dialog_double_button_title);
        mLeftButton = (TextView) mLayoutView.findViewById(R.id.dialog_double_button_cancel);
        mRightButton = (TextView) mLayoutView.findViewById(R.id.dialog_double_button_determine);
        mPromptTitle = context.getResources().getString(R.string.prompt);

        mPromptTitleTV.setText(mPromptTitle);
        mPromptContentTV.setText(mPromptContent);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);

        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (DevicesUtils.getDeviceWidth() * widthScale);
        window.setAttributes(layoutParams);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    public void setLeftButtonText(CharSequence text) {
        mLeftButton.setText(text);
    }

    public void setRightButtonText(CharSequence text) {
        mRightButton.setText(text);
    }

    /**
     * 隐藏标题栏名称布局
     */
    public void hidePromptTitle() {
        mPromptTitleTV.setVisibility(View.GONE);
    }

    /**
     * 隐藏对话框左侧按钮
     */
    public void hideLeftBtn() {
        mLeftButton.setVisibility(View.GONE);
    }

    /**
     * 隐藏内容布局
     */
    public void hidePromptContent() {
        mPromptContentTV.setVisibility(View.GONE);
    }

    /**
     * 提示信息文字较长时，需要设置左对齐
     */
    public void setMsgGravityLeft() {
        mPromptContentTV.setGravity(Gravity.LEFT);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        mClickListener = listener;
    }

    public interface OnButtonClickListener {
        public void onLeftButtonClick(View view);

        public void onRightButtonClick(View view);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (mClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.dialog_double_button_cancel://左侧按钮
                mClickListener.onLeftButtonClick(v);
                break;
            case R.id.dialog_double_button_determine://右侧按钮
                mClickListener.onRightButtonClick(v);
                break;
            default:
                break;
        }
    }
}

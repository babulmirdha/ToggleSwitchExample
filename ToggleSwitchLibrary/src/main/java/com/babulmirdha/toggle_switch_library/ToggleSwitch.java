package com.babulmirdha.toggle_switch_library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ToggleSwitch extends LinearLayout implements View.OnClickListener {


    private String inactiveColor;
    private String activeColor;
    private String mToggled;
    private LinearLayout layout = null;
    private TextView leftTextView = null;
    private TextView rightTextView = null;
    private OnClickListener mClickListener = null;


    public ToggleSwitch(Context context) {
        super(context);
    }

    public ToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ToggleSwitchText);

        String leftText = a.getString(R.styleable.ToggleSwitchText_leftText);
        String rightText = a.getString(R.styleable.ToggleSwitchText_rightText);

        mToggled = a.getString(R.styleable.ToggleSwitchText_toggled);

        activeColor = a.getString(R.styleable.ToggleSwitchText_activeColor);
        inactiveColor = a.getString(R.styleable.ToggleSwitchText_inactiveColor);

        mToggled = (mToggled == null) ? "left" : mToggled;

        activeColor = (activeColor == null) ? "#000000" : activeColor;
        inactiveColor = (inactiveColor == null) ? "#808080" : inactiveColor;

        leftText = (leftText == null) ? "" : leftText;
        rightText = (rightText == null) ? "" : rightText;

        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(service);

        layout = (LinearLayout) li.inflate(R.layout.toggle_switch, this, true);
        layout.setOnClickListener(this);

        leftTextView = layout.findViewById(R.id.leftTextView);

        rightTextView = layout.findViewById(R.id.rightTextView);

        if (mToggled.equals("left")) {
            leftTextView.setTextColor(Color.parseColor(activeColor));
            rightTextView.setTextColor(Color.parseColor(inactiveColor));
        } else {
            leftTextView.setTextColor(Color.parseColor(inactiveColor));
            rightTextView.setTextColor(Color.parseColor(activeColor));
        }

        leftTextView.setText(leftText);
        rightTextView.setText(rightText);

        a.recycle();

    }

    public ToggleSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressWarnings("unused")
    public String getLeftText() {
        return leftTextView.getText().toString();
    }

    @SuppressWarnings("unused")
    public void setLeftText(String text) {
        leftTextView.setText(text);
    }

    @SuppressWarnings("unused")
    public String getRightText() {
        return rightTextView.getText().toString();
    }

    @SuppressWarnings("unused")
    public void setRightText(String text) {
        rightTextView.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (mToggled.equals("left")) {
            mToggled = "right";
            leftTextView.setTextColor(Color.parseColor(inactiveColor));
            rightTextView.setTextColor(Color.parseColor(activeColor));

        } else {
            mToggled = "left";
            leftTextView.setTextColor(Color.parseColor(activeColor));
            rightTextView.setTextColor(Color.parseColor(inactiveColor));
        }

        if (mClickListener != null) {
            mClickListener.onClick(mToggled);
        }

    }

    @SuppressWarnings("unused")
    public void setOnClickListener(OnClickListener listener) {
        mClickListener = listener;
    }

    @SuppressWarnings("unused")
    public void setToggled(String toggled) {

        if (!mToggled.equals(toggled)) {
            mToggled = toggled;
            if (mToggled.equals("left")) {
                leftTextView.setTextColor(Color.parseColor(activeColor));
                rightTextView.setTextColor(Color.parseColor(inactiveColor));
            } else {
                leftTextView.setTextColor(Color.parseColor(inactiveColor));
                rightTextView.setTextColor(Color.parseColor(activeColor));
            }

            if (mClickListener != null) {
                mClickListener.onClick(mToggled);
            }

        }

    }

    public void setDefaultLanguage(String language) {

        if (language.equals("en")) {
            this.setToggled("left");
        } else {
            this.setToggled("right");
        }
    }

    public interface OnClickListener {
        void onClick(String activated);
    }


}

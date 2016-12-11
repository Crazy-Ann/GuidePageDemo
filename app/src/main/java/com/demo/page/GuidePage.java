package com.demo.page;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Tye on 11/12/2016.
 */

public class GuidePage {

    private int     mLayoutId;
    private int     mViewId;
    private String  mPageTag;
    private boolean isCloseOnTouchOutside;

    private Activity    mActivity;
    private FrameLayout mRootLayout;
    private View        mLayoutView;

    private GuidePage() {

    }


    public static class Builder {

        GuidePage page = new GuidePage();

        public Builder(Activity activity) {
            page.mActivity = activity;
        }

        public Builder setLayoutId(@LayoutRes int layoutId) {
            page.mLayoutId = layoutId;
            return this;
        }

        public Builder setViewId(@LayoutRes int viewId) {
            page.mViewId = viewId;
            return this;
        }

        public Builder setPageTag(String pageTag) {
            page.mPageTag = pageTag;
            return this;
        }

        public Builder setCloseOnTouchOutside(boolean isCloseOnTochOutside) {
            page.isCloseOnTouchOutside = isCloseOnTochOutside;
            return this;
        }

        public GuidePage builder() {
            if (!TextUtils.isEmpty(page.mPageTag)) {
                page.setLayoutView();
                page.setViewEvent();
                page.setCloseOnTouchOutside();
            } else {
                // throw exception or other operation...
            }
            return page;
        }
    }

    public void setLayoutView() {
        mRootLayout = (FrameLayout) mActivity.findViewById(android.R.id.content);
        mLayoutView = View.inflate(mActivity, mLayoutId, null);
    }

    public void setViewEvent() {
        if (mLayoutView != null) {
            mLayoutView.findViewById(mViewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
    }

    public void setCloseOnTouchOutside() {
        if (mLayoutView != null) {
            mLayoutView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (isCloseOnTouchOutside) {
                        dismiss();
                    }
                    return true;
                }
            });
        }
    }

    public void show() {
        if (!GuidePageManager.getInstantce().hasShowed(mActivity, mPageTag)) {
            mRootLayout.addView(mLayoutView);
        }
    }

    public void dismiss() {
        if (mRootLayout != null && mLayoutView != null) {
            mRootLayout.removeView(mLayoutView);
            GuidePageManager.getInstantce().setShowed(mActivity, mPageTag, true);
        }
    }

}

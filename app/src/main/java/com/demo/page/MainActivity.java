package com.demo.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!GuidePageManager.getInstantce().hasShowed(this, getClass().getSimpleName())) {
            new GuidePage.Builder(this)
                    .setLayoutId(R.layout.view_guide_page)
                    .setViewId(R.id.tvDismiss)
                    .setPageTag(getClass().getSimpleName())
                    .setCloseOnTouchOutside(true)
                    .builder()
                    .show();
        }
    }
}

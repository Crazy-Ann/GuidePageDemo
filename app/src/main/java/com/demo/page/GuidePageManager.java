package com.demo.page;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tye on 11/12/2016.
 */

public class GuidePageManager {

    private static GuidePageManager mGuidePageManager;

    private GuidePageManager() {

    }

    public static synchronized GuidePageManager getInstantce() {
        if (mGuidePageManager == null) {
            mGuidePageManager = new GuidePageManager();
        }
        return mGuidePageManager;
    }

    public static void releaseInstantce() {
        if (mGuidePageManager != null) {
            mGuidePageManager = null;
        }
    }

    public boolean hasShowed(Context ctx, String key) {
        return ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public void setShowed(Context ctx, String key, boolean hasShowed) {
        SharedPreferences        preferences = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor      = preferences.edit();
        editor.putBoolean(key, hasShowed);
        editor.commit();
    }
}

package com.tjm.crushr;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cymak on 9/23/14.
 */
public class crushrProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds) {
            //update widgets here
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null) {
            if(Intent.ACTION_BOOT_COMPLETED.equalsIgnoreCase(intent.getAction())) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName name = new ComponentName(context, crushrProvider.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(name);
                onUpdate(context, appWidgetManager, appWidgetIds);
            }
        }
        super.onReceive(context, intent);
    }
}

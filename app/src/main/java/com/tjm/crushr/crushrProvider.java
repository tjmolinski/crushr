package com.tjm.crushr;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by cymak on 9/23/14.
 */
public class crushrProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.crushr_widget);

        Intent listIntent = new Intent(context, crushrWidgetService.class);
        listIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        listIntent.setData(Uri.parse(listIntent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.crushr_listview, listIntent);

        Intent addIntent = new Intent(context, crushrInputDialog.class);
        addIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent addPendingIntent = PendingIntent.getActivity(context, 0, addIntent, 0);
        views.setOnClickPendingIntent(R.id.add_crushr_button, addPendingIntent);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.crushr_listview);
        appWidgetManager.updateAppWidget(appWidgetId, views);
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

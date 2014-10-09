package com.tjm.crushr;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cymak on 9/23/14.
 */
public class crushrProvider extends AppWidgetProvider {

    public static final String SHARED_PREF_TAG = "crushr_shared_pref";
    public static final String SHARED_PREF_LIST = "crushr_task_list_";
    public static final String EXTRA_WORD = "crushr_word";

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
        PendingIntent addPendingIntent = PendingIntent.getActivity(context, appWidgetId, addIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.add_crushr_button, addPendingIntent);

        Intent clickIntent = new Intent(context, crushrDeleteDialog.class);
        PendingIntent clickPI = PendingIntent.getActivity(context, appWidgetId, clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setPendingIntentTemplate(R.id.crushr_listview, clickPI);

        SharedPreferences prefs = context.getSharedPreferences(crushrProvider.SHARED_PREF_TAG, context.MODE_PRIVATE);
        Set set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST+appWidgetId, new HashSet<String>());
        if(set.isEmpty()) {
            views.setViewVisibility(R.id.empty, View.VISIBLE);
            views.setViewVisibility(R.id.crushr_listview, View.GONE);
        } else {
            views.setViewVisibility(R.id.empty, View.GONE);
            views.setViewVisibility(R.id.crushr_listview, View.VISIBLE);
        }

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

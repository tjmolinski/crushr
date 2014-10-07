package com.tjm.crushr;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cymak on 9/24/14.
 */
public class crushrRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context context;
    private final int appWidgetId;
    private List<String> itemList = new ArrayList<String>();

    public crushrRemoteViewFactory(Context ctx, Intent intent) {
        context = ctx;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        Log.d("AppWidgetId", String.valueOf(appWidgetId));
    }

    @Override
    public void onCreate() {
        onDataSetChanged();
    }

    @Override
    public void onDataSetChanged() {
        itemList.clear();
        SharedPreferences prefs = context.getSharedPreferences(crushrProvider.SHARED_PREF_TAG, context.MODE_PRIVATE);
        Set set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST+appWidgetId, new HashSet<String>());

        Object[] list = set.toArray();
        for(Object item : list) {
            itemList.add(item.toString());
        }
    }

    @Override
    public void onDestroy() {
        itemList.clear();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.crushr_item);
        remoteView.setTextViewText(R.id.todo, itemList.get(position));

        Intent i = new Intent();
        Bundle extras = new Bundle();
        extras.putString(crushrProvider.EXTRA_WORD, itemList.get(position));
        extras.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        i.putExtras(extras);
        remoteView.setOnClickFillInIntent(R.id.container, i);

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

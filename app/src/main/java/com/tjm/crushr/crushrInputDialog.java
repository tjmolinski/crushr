package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by cymak on 9/30/14.
 */
public class crushrInputDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crushr_input_dialog);
        findViewById(R.id.input_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = ((EditText)findViewById(R.id.crushr_task)).getText().toString().trim();
                SharedPreferences prefs = getSharedPreferences("crushR", MODE_PRIVATE);
                Set<String> set = prefs.getStringSet("crushR", new HashSet<String>());
                set.add(task);
                prefs.edit().putStringSet("crushR", set).apply();

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), crushrProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.crushr_listview);

                finish();
            }
        });
    }
}

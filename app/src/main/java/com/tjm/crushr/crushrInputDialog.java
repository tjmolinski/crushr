package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

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
                SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
                set.add(task);
                editor.remove(crushrProvider.SHARED_PREF_LIST);
                editor.putStringSet(crushrProvider.SHARED_PREF_LIST, set);
                editor.commit();

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), crushrProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.crushr_listview);

                finish();
            }
        });
    }
}

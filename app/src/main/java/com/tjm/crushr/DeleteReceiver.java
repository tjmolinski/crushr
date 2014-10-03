package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cymak on 10/2/14.
 */
public class DeleteReceiver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String word=getIntent().getStringExtra(crushrProvider.EXTRA_WORD);
        if (word == null) {
            word = "We did not get a word!";
        } else {
            SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
            set.remove(word);
            editor.remove(crushrProvider.SHARED_PREF_LIST);
            editor.putStringSet(crushrProvider.SHARED_PREF_LIST, set);
            editor.commit();


            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
            int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), crushrProvider.class));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.crushr_listview);
        }

        Toast.makeText(this, word, Toast.LENGTH_LONG).show();
        finish();
    }
}

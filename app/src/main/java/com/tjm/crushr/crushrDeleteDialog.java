package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cymak on 9/30/14.
 */
public class crushrDeleteDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.crushr_delete_dialog);

        final String task = getIntent().getExtras().getString(crushrProvider.EXTRA_WORD);

        ((TextView)findViewById(R.id.message)).setText(Html.fromHtml(getString(R.string.delete_task, task)));

        findViewById(R.id.input_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.input_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
                set.remove(task);
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

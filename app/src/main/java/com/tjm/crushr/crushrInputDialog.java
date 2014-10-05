package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
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
public class crushrInputDialog extends Activity {

    EditText newTask;
    ArrayList<String> tasks;
    LinearLayout mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.crushr_input_dialog);

        mContainerView = (LinearLayout)findViewById(R.id.container);
        newTask = (EditText)findViewById(R.id.new_task);
        tasks = new ArrayList<String>();

        //TODO:TJM FIX THE ISSUE IF WE HIT BACK BUTTON AND NOT CLEARING THE LIST

        newTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event == null || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    String task = newTask.getText().toString().trim();
                    SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
                    set.add(task);
                    editor.remove(crushrProvider.SHARED_PREF_LIST);
                    editor.putStringSet(crushrProvider.SHARED_PREF_LIST, set);
                    editor.commit();
                    addItem(task);
                    return true;
                }
                return false;
            }
        });

        SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
        Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
        for(String item : set) {
            addItem(item);
        }

        findViewById(R.id.input_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), crushrProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.crushr_listview);

                finish();
            }
        });

        findViewById(R.id.input_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = newTask.getText().toString().trim();
                SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
                set.add(task);
                editor.remove(crushrProvider.SHARED_PREF_LIST);
                editor.putStringSet(crushrProvider.SHARED_PREF_LIST, set);
                editor.commit();
                addItem(task);
            }
        });
    }

    private void addItem(final String text) {
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.crushr_add_item, mContainerView, false);

        ((TextView) newView.findViewById(R.id.crushr_task)).setText(text);

        newView.findViewById(R.id.crushr_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContainerView.removeView(newView);
                tasks.remove(text);

                SharedPreferences prefs = getSharedPreferences(crushrProvider.SHARED_PREF_TAG, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Set<String> set = prefs.getStringSet(crushrProvider.SHARED_PREF_LIST, new HashSet<String>());
                set.remove(text);
                editor.remove(crushrProvider.SHARED_PREF_LIST);
                editor.putStringSet(crushrProvider.SHARED_PREF_LIST, set);
                editor.commit();

//                // If there are no rows remaining, show the empty view.
//                if (mContainerView.getChildCount() == 0) {
//                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
//                }
            }
        });

        tasks.add(text);
        newTask.setText("");

        mContainerView.addView(newView, 0);
    }
}

package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by tedmolinski on 8/9/15.
 */
public class crushrConfigActivity extends Activity {

    private RadioGroup mFirstGroup;
    private RadioGroup mSecondGroup;
    private boolean isChecking = true;
    private int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.crushr_config);
        appWidgetId = getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);

        mFirstGroup = (RadioGroup) findViewById(R.id.primary_color_selector_first_row);
        mSecondGroup = (RadioGroup) findViewById(R.id.primary_color_selector_second_row);

        mFirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mSecondGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        mSecondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mFirstGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        findViewById(R.id.input_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CLOSE", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.input_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_LONG).show();
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });
    }

    public void onPrimaryButtonClicked(View view) {
        switch(view.getId()) {
        case R.id.primary_color_1:
            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
            PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_1), appWidgetId);
            break;
        case R.id.primary_color_2:
            Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
            PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_2), appWidgetId);
            break;
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        crushrProvider.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by tedmolinski on 8/9/15.
 */
public class crushrConfigActivity extends Activity {

    private RadioGroup mFirstPrimaryGroup;
    private RadioGroup mSecondPrimaryGroup;
    private RadioGroup mFirstSecondaryGroup;
    private RadioGroup mSecondSecondaryGroup;
    private boolean isChecking = true;
    private int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.crushr_config);
        appWidgetId = getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);

        mFirstPrimaryGroup = (RadioGroup) findViewById(R.id.primary_color_selector_first_row);
        mSecondPrimaryGroup = (RadioGroup) findViewById(R.id.primary_color_selector_second_row);

        mFirstSecondaryGroup = (RadioGroup) findViewById(R.id.secondary_color_selector_first_row);
        mSecondSecondaryGroup = (RadioGroup) findViewById(R.id.secondary_color_selector_second_row);

        mFirstPrimaryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mSecondPrimaryGroup.clearCheck();
                }
                isChecking = true;
            }
        });
        mSecondPrimaryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mFirstPrimaryGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        mFirstSecondaryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mSecondSecondaryGroup.clearCheck();
                }
                isChecking = true;
            }
        });
        mSecondSecondaryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mFirstSecondaryGroup.clearCheck();
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
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_1), appWidgetId);
                break;
            case R.id.primary_color_2:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_2), appWidgetId);
                break;
            case R.id.primary_color_3:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_3), appWidgetId);
                break;
            case R.id.primary_color_4:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_4), appWidgetId);
                break;
            case R.id.primary_color_5:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_5), appWidgetId);
                break;
            case R.id.primary_color_6:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_6), appWidgetId);
                break;
            case R.id.primary_color_7:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_7), appWidgetId);
                break;
            case R.id.primary_color_8:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_8), appWidgetId);
                break;
            case R.id.primary_color_9:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_9), appWidgetId);
                break;
            case R.id.primary_color_10:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_10), appWidgetId);
                break;
            case R.id.primary_color_11:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_11), appWidgetId);
                break;
            case R.id.primary_color_12:
                PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.primary_color_12), appWidgetId);
                break;
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        crushrProvider.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);
    }

    public void onSecondaryButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.secondary_color_1:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_1), appWidgetId);
                break;
            case R.id.secondary_color_2:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_2), appWidgetId);
                break;
            case R.id.secondary_color_3:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_3), appWidgetId);
                break;
            case R.id.secondary_color_4:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_4), appWidgetId);
                break;
            case R.id.secondary_color_5:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_5), appWidgetId);
                break;
            case R.id.secondary_color_6:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_6), appWidgetId);
                break;
            case R.id.secondary_color_7:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_7), appWidgetId);
                break;
            case R.id.secondary_color_8:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_8), appWidgetId);
                break;
            case R.id.secondary_color_9:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_9), appWidgetId);
                break;
            case R.id.secondary_color_10:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_10), appWidgetId);
                break;
            case R.id.secondary_color_11:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_11), appWidgetId);
                break;
            case R.id.secondary_color_12:
                PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(R.color.secondary_color_12), appWidgetId);
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

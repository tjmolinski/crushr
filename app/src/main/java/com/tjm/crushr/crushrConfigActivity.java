package com.tjm.crushr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private int primaryColorId = -1;
    private int secondaryColorId = -1;

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

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(crushrProvider.SHARED_PREF_TAG, getApplicationContext().MODE_PRIVATE);
        int primaryColor = prefs.getInt(crushrProvider.SHARED_PREF_PRIMARY_COLOR + appWidgetId, getApplicationContext().getResources().getColor(R.color.primary_color_1));
        int secondaryColor = prefs.getInt(crushrProvider.SHARED_PREF_SECONDARY_COLOR+appWidgetId, getApplicationContext().getResources().getColor(R.color.secondary_color_1));
        loadColorSelections(primaryColor, secondaryColor);

        findViewById(R.id.input_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });

        findViewById(R.id.input_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(primaryColorId >= 0) {
                    PrefUtils.setPrimaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(primaryColorId), appWidgetId);
                }
                if(secondaryColorId >= 0) {
                    PrefUtils.setSecondaryColor(getApplicationContext(), getApplicationContext().getResources().getColor(secondaryColorId), appWidgetId);
                }

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                crushrProvider.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });
    }

    private void loadColorSelections(int pColor, int sColor) {
        Resources res = getApplicationContext().getResources();
        if(pColor == res.getColor(R.color.primary_color_1)) {
            ((RadioButton)findViewById(R.id.primary_color_1)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_2)) {
            ((RadioButton)findViewById(R.id.primary_color_2)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_3)) {
            ((RadioButton)findViewById(R.id.primary_color_3)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_4)) {
            ((RadioButton)findViewById(R.id.primary_color_4)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_5)) {
            ((RadioButton)findViewById(R.id.primary_color_5)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_6)) {
            ((RadioButton)findViewById(R.id.primary_color_6)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_7)) {
            ((RadioButton)findViewById(R.id.primary_color_7)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_8)) {
            ((RadioButton)findViewById(R.id.primary_color_8)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_9)) {
            ((RadioButton)findViewById(R.id.primary_color_9)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_10)) {
            ((RadioButton)findViewById(R.id.primary_color_10)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_11)) {
            ((RadioButton)findViewById(R.id.primary_color_11)).setChecked(true);
        } else if(pColor == res.getColor(R.color.primary_color_12)) {
            ((RadioButton)findViewById(R.id.primary_color_12)).setChecked(true);
        }
        if(sColor == res.getColor(R.color.secondary_color_1)) {
            ((RadioButton)findViewById(R.id.secondary_color_1)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_2)) {
            ((RadioButton)findViewById(R.id.secondary_color_2)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_3)) {
            ((RadioButton)findViewById(R.id.secondary_color_3)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_4)) {
            ((RadioButton)findViewById(R.id.secondary_color_4)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_5)) {
            ((RadioButton)findViewById(R.id.secondary_color_5)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_6)) {
            ((RadioButton)findViewById(R.id.secondary_color_6)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_7)) {
            ((RadioButton)findViewById(R.id.secondary_color_7)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_8)) {
            ((RadioButton)findViewById(R.id.secondary_color_8)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_9)) {
            ((RadioButton)findViewById(R.id.secondary_color_9)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_10)) {
            ((RadioButton)findViewById(R.id.secondary_color_10)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_11)) {
            ((RadioButton)findViewById(R.id.secondary_color_11)).setChecked(true);
        } else if(sColor == res.getColor(R.color.secondary_color_12)) {
            ((RadioButton)findViewById(R.id.secondary_color_12)).setChecked(true);
        }
    }

    public void onPrimaryButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.primary_color_1:
                primaryColorId = R.color.primary_color_1;
                break;
            case R.id.primary_color_2:
                primaryColorId = R.color.primary_color_2;
                break;
            case R.id.primary_color_3:
                primaryColorId = R.color.primary_color_3;
                break;
            case R.id.primary_color_4:
                primaryColorId = R.color.primary_color_4;
                break;
            case R.id.primary_color_5:
                primaryColorId = R.color.primary_color_5;
                break;
            case R.id.primary_color_6:
                primaryColorId = R.color.primary_color_6;
                break;
            case R.id.primary_color_7:
                primaryColorId = R.color.primary_color_7;
                break;
            case R.id.primary_color_8:
                primaryColorId = R.color.primary_color_8;
                break;
            case R.id.primary_color_9:
                primaryColorId = R.color.primary_color_9;
                break;
            case R.id.primary_color_10:
                primaryColorId = R.color.primary_color_10;
                break;
            case R.id.primary_color_11:
                primaryColorId = R.color.primary_color_11;
                break;
            case R.id.primary_color_12:
                primaryColorId = R.color.primary_color_12;
                break;
        }
    }

    public void onSecondaryButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.secondary_color_1:
                secondaryColorId = R.color.secondary_color_1;
                break;
            case R.id.secondary_color_2:
                secondaryColorId = R.color.secondary_color_2;
                break;
            case R.id.secondary_color_3:
                secondaryColorId = R.color.secondary_color_3;
                break;
            case R.id.secondary_color_4:
                secondaryColorId = R.color.secondary_color_4;
                break;
            case R.id.secondary_color_5:
                secondaryColorId = R.color.secondary_color_5;
                break;
            case R.id.secondary_color_6:
                secondaryColorId = R.color.secondary_color_6;
                break;
            case R.id.secondary_color_7:
                secondaryColorId = R.color.secondary_color_7;
                break;
            case R.id.secondary_color_8:
                secondaryColorId = R.color.secondary_color_8;
                break;
            case R.id.secondary_color_9:
                secondaryColorId = R.color.secondary_color_9;
                break;
            case R.id.secondary_color_10:
                secondaryColorId = R.color.secondary_color_10;
                break;
            case R.id.secondary_color_11:
                secondaryColorId = R.color.secondary_color_11;
                break;
            case R.id.secondary_color_12:
                secondaryColorId = R.color.secondary_color_12;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

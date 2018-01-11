package com.xuhan.datetimepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xuhan.datetimepicker.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomDatePicker mDatePicker;
    private CustomDatePicker mTimePicker;
    private CustomDatePicker mDateTimePicker;
    private SimpleDateFormat mDateFormat;
    private String mNowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_date_time).setOnClickListener(this);
        initDataTimePicker();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                mDatePicker.show(mNowTime);
                break;
            case R.id.btn_time:
                mTimePicker.show(mNowTime);
                break;
            case R.id.btn_date_time:
                mDateTimePicker.show(mNowTime);
                break;
            default:
                break;
        }
    }

    private void initDataTimePicker() {
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        mNowTime = mDateFormat.format(new Date());
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                showToast(time);
            }
        }, mNowTime, "2037-12-31 00:00");
        mDatePicker.showSpecificDate(true);  // 显示日期
        mDatePicker.showSpecificTime(false); // 不显示时和分
        mDatePicker.setIsLoop(true); // 允许循环滚动

        mTimePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                showToast(time);
            }
        }, "2018-01-01 00:00", "2037-01-01 00:00");
        mTimePicker.showSpecificDate(false);  // 不显示日期
        mTimePicker.showSpecificTime(true);   // 显示时和分
        mTimePicker.setIsLoop(true);

        mDateTimePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                showToast(time);
            }
        }, "2018-01-01 00:00", "2037-01-01 00:00");
        mDateTimePicker.showSpecificDate(true);  // 显示日期
        mDateTimePicker.showSpecificTime(true);  // 显示时和分
        mDateTimePicker.setIsLoop(true);
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}

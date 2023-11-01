package com.example.needcalendar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class list_schedule extends AppCompatActivity {

    private ImageButton imageButton;
    TextView today;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_schedule);

        calendarView = findViewById(R.id.calendarView);

        // 각 화면에 맞는 intent 설정 코드

        imageButton = findViewById(R.id.monthbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent1);
            }
        });

        // 주별 화면 연결 -> 일정 리스트로 수정 부탁.
        imageButton = findViewById(R.id.weekbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent2);
            }
        });


        // 일별 화면 연결 -> 체크리스트로 수정 부탁.
        imageButton = findViewById(R.id.todaybutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(getApplicationContext(), TodayCalendar.class);

                startActivity(intent3);
            }
        });

        // 일정추가 화면 연결
        imageButton = findViewById(R.id.addbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent5 = new Intent(getApplicationContext(), add_schedule.class);

                startActivity(intent5);
            }
        });

        // 월별 날짜 나오게 하기.
        today = findViewById(R.id.yearMonth);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        DateFormat formatter = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
        Date date = new Date(calendarView.getDate());
        today.setText(formatter.format(date));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day = year + "년 " + (month + 1) + "월 ";
                today.setText(day);
            }
        });

    }
}

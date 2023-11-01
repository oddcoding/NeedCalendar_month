package com.example.needcalendar;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodayCalendar extends AppCompatActivity {

    Context context;
    Button button;
    private RecyclerView mPostRecyclerView;
    private checklist mAdapter;
    private List<list> mDatas;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private ChecklistAdapter adapter1;
    private ChecklistAdapter adapter2;
    private ChecklistAdapter adapter;
    private List<ListItem> checklistItems;
    private DatabaseHelper dbHelper;
    private TextView textView;
    private TextView titleTextView;
    private TextView placeTextView;
    private TextView memoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily);
//        context = this;
//        mPostRecyclerView = findViewById(R.id.list_rv);
//
//        mDatas = new ArrayList<>();
//
//        mDatas.add(new list("title1", "place1", "Memo1"));
//        mDatas.add(new list("title2", "place2", "Memo2"));
//        mDatas.add(new list("title3", "place3", "Memo3"));;
//
//
//        mAdapter = new checklist(mDatas);
//        mPostRecyclerView.setAdapter(mAdapter);
//        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView2 = findViewById(R.id.list_rv);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        checklistItems = dbHelper.getAllItems();

        adapter = new ChecklistAdapter(checklistItems, context);
        recyclerView2.setAdapter(adapter);



    }
}







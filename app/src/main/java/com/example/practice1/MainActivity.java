package com.dkhiruntsev.practice1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String CHANGES_KEY = "changes_list";
    private static final String COUNT_KEY = "count";
    private int count = 0;
    private ArrayList<String> changes;
    private ArrayAdapter<String> adapter;
    private TextView textView;
    private SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textDisplay);

        ListView listView = findViewById(R.id.myList);

        if (savedInstanceState != null) {
            changes = savedInstanceState.getStringArrayList(CHANGES_KEY);
            count = savedInstanceState.getInt(COUNT_KEY);
        } else {
            changes = new ArrayList<>();
            count = 0;
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                changes
        );

        listView.setAdapter(adapter);
    }

    public void reset(android.view.View view) {
        count = 0;
        textView.setText(String.valueOf(count));
        changes.add(fmt.format(Calendar.getInstance().getTime()));
        adapter.notifyDataSetChanged();

    }

    public void increaseByOne(android.view.View view) {
        count++;
        textView.setText(String.valueOf(count));
        changes.add((fmt.format(Calendar.getInstance().getTime())));
        adapter.notifyDataSetChanged();
    }

    public void decreaseByOne(android.view.View view) {
        count--;
        textView.setText(String.valueOf(count));
        changes.add(fmt.format(Calendar.getInstance().getTime()));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(CHANGES_KEY, changes);
        outState.putInt(COUNT_KEY, count);
    }
}
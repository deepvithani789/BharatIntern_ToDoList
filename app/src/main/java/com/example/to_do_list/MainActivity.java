package com.example.to_do_list;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ListView list;
    private AppCompatButton button;
    private ArrayAdapter<String> taskadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), this::onApplyWindowInsets);
    }

    private boolean remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(context, "Task Removed", Toast.LENGTH_LONG).show();
        tasks.remove(position);
        taskadapter.notifyDataSetChanged();
        return true;
    }

    private void additem(View view) {
        EditText editText = findViewById(R.id.edit_text);
        String taskText = editText.getText().toString();
        if (!(taskText.equals(""))) {
            taskadapter.add(taskText);
            editText.setText("");
            Toast.makeText(this, "Task with Name : " + taskText + " has been added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Task", Toast.LENGTH_LONG).show();
        }
    }


    private WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

        list = findViewById(R.id.list_view);
        button = findViewById(R.id.addbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);

            }
        });

        tasks = new ArrayList<>();
        taskadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        list.setAdapter(taskadapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return remove(position);
            }
        });
        return insets;
    }
}
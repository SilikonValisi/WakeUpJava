package com.example.wakeupjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    List<String> itemlist = new ArrayList<String>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing the array lists and the adapter
        Button add = findViewById(R.id.add);
        EditText editText = findViewById (R.id.editText);
        ListView listView = (ListView) findViewById (R.id.listView);
        Button delete = findViewById (R.id.delete);
        Button clear = findViewById(R.id.clear);


        adapter = new ArrayAdapter (MainActivity.this, android.
        R.layout.simple_list_item_multiple_choice, itemlist);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   TimePickerFragment timePicker =  new TimePickerFragment();
                   timePicker.show(getSupportFragmentManager(),"time picker");
                   //If i add items like this, everyting works fine
//                   itemlist.add(editText.getText().toString());
//                   listView.setAdapter(adapter);
//                   adapter.notifyDataSetChanged();
//                   // This is because every time when you add the item the input      space or the eidt text space will be cleared
//                   editText.getText().clear();
               }
           }
        );


        // Selecting and Deleting the items from the list when the delete button is pressed

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray position = listView.getCheckedItemPositions();


                Integer count = listView.getCount();
                int item = count - 1;
                while (item >= 0) {
                if (position.get(item)) {
                    itemlist.remove(item);
//                    adapter.remove(itemlist.get(item));
                }
                item--;
                }
                position.clear();
//                ArrayAdapter<String>  adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, itemlist);
//                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        // Clearing all the items in the list when the clear button is pressed
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemlist.clear();
//                ArrayAdapter<String>  adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, itemlist);
//                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        itemlist.add("The time selected is "+i+":"+i1);

        ListView listView=findViewById(R.id.listView);

        adapter.notifyDataSetChanged();
    }
}
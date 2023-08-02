package com.example.wakeupjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.wakeupjava.model.AlarmItem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    List<AlarmItem> itemlist = new ArrayList<AlarmItem>();
    AlarmAdapter adapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.add);
        editText = findViewById (R.id.editText);
        ListView listView = (ListView) findViewById (R.id.listView);
        Button delete = findViewById (R.id.delete);
        Button clear = findViewById(R.id.clear);


        adapter = new AlarmAdapter (MainActivity.this, android.
        R.layout.simple_list_item_multiple_choice, itemlist);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   TimePickerFragment timePicker =  new TimePickerFragment();
                   timePicker.show(getSupportFragmentManager(),"time picker");
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

                //todo: delete cancel selected alarms
                Integer count = listView.getCount();
                int item = count - 1;
                while (item >= 0) {
                if (position.get(item)) {
//                    itemlist.remove(item);
                    adapter.remove(itemlist.get(item));
                }
                item--;
                }
                position.clear();

                adapter.notifyDataSetChanged();
            }
        });

        // Clearing all the items in the list when the clear button is pressed
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(),AlertReceiver.class);
                //todo: change the flag
                for(int i=0;i<itemlist.size();i++){
                    Integer alarmId = itemlist.get(i).alarmId;
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),alarmId , intent, PendingIntent.FLAG_MUTABLE);
                    alarmManager.cancel(pendingIntent);

                }
                itemlist.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        startAlarm(c);

    }

    public void updateListView(Calendar calendar){

    }
    public void startAlarm(Calendar calendar){
        AtomicInteger atomicInteger = new AtomicInteger();
        Integer alarmId=atomicInteger.incrementAndGet();

        AlarmItem newAlarmItem = new AlarmItem(alarmId,editText.getText().toString()+" : "+ DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));
        itemlist.add(newAlarmItem);
        adapter.notifyDataSetChanged();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        //todo: change the flag
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,alarmId , intent, PendingIntent.FLAG_MUTABLE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }
}
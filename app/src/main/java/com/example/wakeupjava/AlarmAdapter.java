package com.example.wakeupjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wakeupjava.model.AlarmItem;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends ArrayAdapter<AlarmItem> {
    public AlarmAdapter(@NonNull Context context, int resource, @NonNull List<AlarmItem> objects) {
        super(context, resource, objects);
    }

//    public AlarmAdapter(Context context, ArrayList<AlarmItem> alarms) {
//        super(context, 0, alarms);
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AlarmItem alarmItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alarm, parent, false);
        }

        // Lookup view for data population
        TextView description = (TextView) convertView.findViewById(R.id.description);
        ImageView checkBox = (ImageView) convertView.findViewById(R.id.iv_check_box);
        description.setText(alarmItem.description);
        if(alarmItem.isSelected){
            checkBox.setBackgroundResource(R.drawable.checked);
        }else{
            checkBox.setBackgroundResource(R.drawable.check);
        }
        return convertView;
    }


}

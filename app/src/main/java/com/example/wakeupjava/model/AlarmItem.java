package com.example.wakeupjava.model;

public class AlarmItem {
    public Integer alarmId;
    public String description;

    public Boolean isSelected;

    public AlarmItem(Integer alarmId,String description,Boolean isSelected){
        this.alarmId=alarmId;
        this.description=description;
        this.isSelected=isSelected;
    }

}

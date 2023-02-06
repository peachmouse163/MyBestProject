package com.example.mybestproject.tools;

import java.util.Calendar;

public class GetNowTimeTool {

    public static String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        String mMonth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String mDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String mHours = "";
        if (calendar.get(Calendar.HOUR) < 10){
            mHours = "0"+calendar.get(Calendar.HOUR);
        }else{
            mHours = String.valueOf(calendar.get(Calendar.HOUR));
        }

        String mMinute = "";
        if (calendar.get(Calendar.MINUTE) < 10){
            mMinute = "0"+calendar.get(Calendar.MINUTE);
        }else{
            mMinute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        return  mMonth+"月"+mDay+"日"+mHours+":"+mMinute;
    }

}

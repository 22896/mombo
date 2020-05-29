package com.example.mombo.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeConverter {
    public static String timeConverterMinute(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.KOREAN);
        return sdf.format(time);
    }

    public static String timeConverterDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN);
        return sdf.format(time);
    }
}

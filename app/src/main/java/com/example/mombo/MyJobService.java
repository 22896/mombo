package com.example.mombo;


import android.util.Log;


public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG,"Performing long running task in scheduled job");
        return false;
    }
}

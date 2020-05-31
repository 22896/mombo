package com.example.mombo;


import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


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

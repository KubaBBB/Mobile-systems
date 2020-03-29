package com.example.calculator;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ProgressBar;

public class PiComputeTask extends AsyncTask<Void, Integer, Double> {
    private EditText resEditText;
    private ProgressBar progressBar;

    PiComputeTask(EditText resultField, ProgressBar resProgressBar) {
        resEditText = resultField;
        progressBar = resProgressBar;
    }

    protected Double doInBackground(Void... voids) {
        double pi = 1330.0;
        int max = 1000000;
        for (int i = 0; i < m; i++) {
            try {
                Thread.sleep(1000);
                publishProgress();
            } catch (InterruptedException e) {
                // We were cancelled; stop sleeping!
            }
        }
        return pi;
    }

    protected void onPostExecute(Double result) {
        resEditText.setText(result.toString());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
}


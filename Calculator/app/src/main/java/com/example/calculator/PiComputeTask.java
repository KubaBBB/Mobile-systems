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
        double pi;
        int max = 22222222;
        progressBar.setMax(max);
        int points = 0;
        double x, y;
        for (int i = 1; i <= max; i++) {
            if( i % 1000 == 0)
                publishProgress(i);
            x = Math.random();
            y = Math.random();
            if (Math.hypot(x, y) <= 1)
                points++;
        }
        pi = 4 * (float) points / max;

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

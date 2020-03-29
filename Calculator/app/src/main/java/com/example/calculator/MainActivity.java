package com.example.calculator;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

enum Operation {
    Add, Substract, Divide, Multiplication
}

public class MainActivity extends AppCompatActivity {
    LogicService logicService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button addBtn = (Button) findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound)
                    performCalculation(Operation.Add);
            }
        });

        Button subBtn = (Button) findViewById(R.id.subButton);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound)
                    performCalculation(Operation.Substract);
            }
        });

        Button mulBtn = (Button) findViewById(R.id.mulButton);
        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound)
                    performCalculation(Operation.Multiplication);
            }
        });

        Button divBtn = (Button) findViewById(R.id.divButton);
        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound)
                    performCalculation(Operation.Divide);
            }
        });

        Button piBtn = (Button) findViewById(R.id.piButton);
        piBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(0);
                new PiComputeTask((EditText) findViewById(R.id.n1EditText),
                        (ProgressBar) findViewById(R.id.progressBar))
                        .execute();
            }
        });
    }

    private void performCalculation(Operation op) {
        EditText field1 = (EditText) findViewById(R.id.n1EditText);
        EditText field2 = (EditText) findViewById(R.id.n2EditText);
        String val1 = field1.getText().toString();
        String val2 = field2.getText().toString();

        if (!val1.isEmpty() && !val2.isEmpty()) {
            try {
                double val1Parsed = Double.parseDouble(val1);
                double val2Parsed = Double.parseDouble(val2);

                EditText result = (EditText) findViewById(R.id.resEditText);
                double serviceResult;
                switch (op) {
                    case Add:
                        serviceResult = logicService.add(val1Parsed, val2Parsed);
                        result.setText(Double.toString(serviceResult), TextView.BufferType.EDITABLE);
                        break;
                    case Substract:
                        serviceResult = logicService.sub(val1Parsed, val2Parsed);
                        result.setText(Double.toString(serviceResult), TextView.BufferType.EDITABLE);
                        break;
                    case Multiplication:
                        serviceResult = logicService.mul(val1Parsed, val2Parsed);
                        result.setText(Double.toString(serviceResult), TextView.BufferType.EDITABLE);
                        break;
                    case Divide:
                        if (val2Parsed == 0.0)
                            throw new NumberFormatException("Do not divide by 0");
                        serviceResult = logicService.div(val1Parsed, val2Parsed);
                        result.setText(Double.toString(serviceResult), TextView.BufferType.EDITABLE);
                        break;
                }

            } catch (NumberFormatException ex) {
                System.out.println("Exception, number format invalid...");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            this.bindService(new Intent(MainActivity.this, LogicService.class),
                    logicConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            mBound = false;
            this.unbindService(logicConnection);
        }
    }

    private ServiceConnection logicConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            LogicService.LocalBinder binder = (LogicService.LocalBinder) service;
            logicService = binder.getService();
            mBound = true;
            Toast.makeText(MainActivity.this, "Logic Service Connected!",
                    Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
            logicService = null;
            mBound = false;
            Toast.makeText(MainActivity.this, "Logic Service Disconnected!",
                    Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.mqttchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static String BROKER = "IP";
    public static String NICK = "nick";

    private Button startBtn;
    private EditText serverEditText;
    private EditText nickNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startButton);
        serverEditText = findViewById(R.id.serverEditText);
        nickNameEditText = findViewById(R.id.nicknameEditText);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ChatRoomActivity.class);
                intent.putExtra(BROKER, serverEditText.getText().toString());
                intent.putExtra(NICK, nickNameEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}

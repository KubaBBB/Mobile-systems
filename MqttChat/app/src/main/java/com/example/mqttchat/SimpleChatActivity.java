package com.example.mqttchat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SimpleChatActivity extends Activity {

    String nick = getIntent().getStringExtra(MainActivity.NICK);
    String ip = getIntent().getStringExtra(MainActivity.IP);

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ListView chatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_items);
        chatListView = findViewById(R.id.chatListView);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listItems);
        chatListView.setAdapter(adapter);

    }
}

package com.example.mqttchat;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {
    private MqttService mqttService;
    private boolean mBound = false;

    private String BROKER = "default";
    private String NICK = "default_nick";
    private String TOPIC = "testTopic";

    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView chatListView;

    private Button sendButton;
    private TextView messageEditText;

    private Handler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);

        chatListView = findViewById(R.id.chatListView);
        messageEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postOnClick(v);
            }
        });
        chatListView.setAdapter(adapter);

        NICK = getIntent().getStringExtra(MainActivity.NICK);
        BROKER = getIntent().getStringExtra(MainActivity.BROKER);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!mBound) {
            Intent intent = new Intent(this, MqttService.class);
            intent.putExtra("broker", BROKER);
            intent.putExtra("clientId", NICK);
            bindService(intent, logicConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            mBound = false;
            mqttService.stopMqtt();
            this.unbindService(logicConnection);
        }
    }


    public void postOnClick(View view) {
        Message msg = myHandler.obtainMessage();
        Bundle b = new Bundle();
        b.putString("TOPIC", TOPIC);
        b.putString("MSG", this.NICK.concat(messageEditText.getText().toString()));
        msg.setData(b);

        byte[] binaryMsg = messageEditText.getText().toString().getBytes();
        MqttMessage mqttMessage = new MqttMessage(binaryMsg);
        mqttService.sendMessage(this.TOPIC, mqttMessage);

        messageEditText.setText("");
    }

    public MqttCallBack mqttCallBack = new MqttCallBack() {
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("Catched mqttMessage");
            byte[] payload = message.getPayload();
            String decodedPayload = new String(payload, "UTF-8");
            System.out.println(decodedPayload);
            Bundle b = new Bundle();
            b.putString("TOPIC", topic);
            b.putString("MSG", decodedPayload);
            Message customMsg = myHandler.obtainMessage();
            customMsg.setData(b);

            myHandler.handleMessage(customMsg);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {
        }

        @Override
        public void connectionLost(Throwable arg0) {
            System.out.println("Lost connection: " + arg0.getMessage());
        }

    };

    private class MyHandler extends Handler {
        private final WeakReference<ChatRoomActivity> sActivity;

        MyHandler(ChatRoomActivity activity) {
            sActivity = new WeakReference<ChatRoomActivity>(activity);
        }

        public void handleMessage(final Message msg) {

            runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  ChatRoomActivity activity = sActivity.get();
                                  activity.listItems.add("[" + msg.getData().getString("TOPIC") + "] " +
                                          msg.getData().getString("MSG"));
                                  activity.adapter.notifyDataSetChanged();
                                  activity.chatListView.setSelection(activity.listItems.size() - 1);
                                  //kiir.setText(ki_adat);
                              }
                          });

        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection logicConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MqttService.LocalBinder binder = (MqttService.LocalBinder) service;
            mqttService = binder.getService();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mqttService.startMQTT();
                    mqttService.setupCallback(mqttCallBack);
                    mqttService.connect();
                }
            }).start();

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}

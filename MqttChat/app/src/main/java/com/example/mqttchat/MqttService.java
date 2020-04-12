package com.example.mqttchat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttService extends Service {

    private MqttClient sampleClient = null;
    private String clientId;
    private String broker;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MqttService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MqttService.this;
        }
    }

    public MqttService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        this.broker = intent.getStringExtra("broker");
        this.clientId = intent.getStringExtra("clientId");

        return mBinder;
    }

    public void startMQTT() {

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            String url = "tcp://192.168.221.33:1883";
            //String url = "tcp://test.mosquitto.org:1883";
            sampleClient = new MqttClient(url, this.clientId, persistence);
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void connect() {
        try {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: " + broker);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
        sampleClient.subscribe("#");
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMessage(String topic, MqttMessage mqttMessage) {
        try {
            sampleClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void setupCallback(MqttCallback callback) {
        sampleClient.setCallback(callback);
    }

    public void stopMqtt() {
        if (sampleClient != null) {
            try {
                sampleClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}

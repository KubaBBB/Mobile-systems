package com.example.mqttchat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallBack implements MqttCallback {

    @Override
    public void connectionLost(Throwable arg0) {
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("[CallBack->" + topic + "]" + message.toString());
    }
}

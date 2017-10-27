package com.example.anuj.iris_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import static android.R.attr;

public class Arm_Disarm extends AppCompatActivity {

    String host = "tcp://m11.cloudmqtt.com:16201";
    // String clientId = "ExampleAndroidClient";
    String topic = "sensor/snd";

    String username = "rcduaeoh";
    String password = "hm3O7P_0KiXi";

    MqttAndroidClient client;
    IMqttToken token = null;
    MqttConnectOptions options;

    Switch ArmDisarm, LockUnlck;
    Button livestream, outhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm__disarm);

        ArmDisarm = (Switch)findViewById(R.id.switch1);
        LockUnlck = (Switch) findViewById(R.id.switch2);

        livestream = (Button) findViewById(R.id.button);
        outhome = (Button) findViewById(R.id.button2);

       ArmDisarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ArmDisarm.isChecked())
                {
                    Toast.makeText(Arm_Disarm.this, "Switch is on", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Arm_Disarm.this, "Switch is Off", Toast.LENGTH_LONG).show();
                }

            }
        });

        LockUnlck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(LockUnlck.isChecked())
                {
                    Toast.makeText(Arm_Disarm.this, "Switch is on", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Arm_Disarm.this, "Switch is Off", Toast.LENGTH_LONG).show();
                }

            }
        });
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), host, clientId);

        options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        try {
            token = client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(getApplicationContext(),"Connection successful",Toast.LENGTH_SHORT).show();
                    subscribtion();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(getApplicationContext(),"Connection failed",Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //textView.setText(new String(message.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }


    private void subscribtion(){
        try {
            client.subscribe(topic,0);
        } catch (MqttSecurityException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}

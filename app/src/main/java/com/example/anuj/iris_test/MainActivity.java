package com.example.anuj.iris_test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

    EditText addrField;
    Button btnConnect;
    VideoView streamView;
    MediaController mediaController;
    SeekBar seek2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seek2 = (SeekBar) findViewById(R.id.seekBar2);



        Button btn = (Button) findViewById(R.id.video);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Arm_Disarm.class);
                startActivity(i);
            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        addrField = (EditText)findViewById(R.id.addr);
        btnConnect = (Button)findViewById(R.id.connect);
        streamView = (VideoView)findViewById(R.id.streamview);

        btnConnect.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                String s = addrField.getEditableText().toString();
                playStream(s);
            }});

    }

    private void playStream(String src){
        Uri UriSrc = Uri.parse(src);
        if(UriSrc == null){
            Toast.makeText(MainActivity.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        }else{
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.setMediaController(mediaController);
            streamView.start();

            Toast.makeText(MainActivity.this,
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        streamView.stopPlayback();
    }

}

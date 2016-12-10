package com.cebess.qsosender3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import java.io.IOException;
import android.content.IntentFilter;


public class MainActivity extends AppCompatActivity {
    IntentFilter intentFilter;

    public static final String ProjectName = "qsosender3";
    public static Boolean DEBUG = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    // Declare the User Interface elements
    EditText generatedQSOEditText;
    EditText XmitSpeededitText;
    Button generateButton;
    Button sendButton;
    Button stopButton;
    Intent serviceIntent;

    int XmitSpeed = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define the graphic elements
        generatedQSOEditText = (EditText)findViewById(R.id.generatedQSOEditText);
        XmitSpeededitText = (EditText)findViewById(R.id.XmitSpeededitText);
        generateButton = (Button)findViewById(R.id.generateButton);
        sendButton = (Button)findViewById(R.id.sendButton);
        stopButton = (Button) findViewById(R.id.stopButton);

        //install listeners
        generateButton.setOnClickListener(btnGenerateListener);
        sendButton.setOnClickListener(btnSendListener);
        stopButton.setOnClickListener(btnGStopListener);
        generateMessage();
        // Intent used for starting the MusicService
        final Intent musicServiceIntent = new Intent(getApplicationContext(),
                MusicService.class);

        final CheckBox noiseBox = (CheckBox) findViewById(R.id.noiseBox);
        noiseBox.setOnClickListener(new OnClickListener() {
            public void onClick(View src) {
                if (noiseBox.isChecked()) {

                    // Start the MusicService using the Intent
                    startService(musicServiceIntent);
                }
                else
                {
                    stopService(musicServiceIntent);
                }
            }
        });
        
    }


    private void generateMessage() {
        try {
            XmitSpeed = Integer.parseInt(XmitSpeededitText.getText().toString());
            if (XmitSpeed < 5 || XmitSpeed > 50) {
                //display in short period of time
                Toast.makeText(getApplicationContext(), "The transmit speed must be between 5 and 50, inclusive.", Toast.LENGTH_SHORT).show();
                XmitSpeed = 13;
                XmitSpeededitText.setText("13");
            } else {
                RandomQSO myQSO = new RandomQSO();
                String QSOString = myQSO.getQSO(XmitSpeed);
                generatedQSOEditText.setText(QSOString);
            }
        } catch (IOException e) {
            Log.e(MainActivity.ProjectName,"IO exception: " + e.getMessage());
            finish();
        } catch (java.text.ParseException e) {
            Log.e(MainActivity.ProjectName,"Parse exception: " + e.getMessage());
            finish();
        }
    }
    // action for the Generate button
    private OnClickListener btnGenerateListener = new OnClickListener() {
        public void onClick(View v){
            generateMessage();
        }
    };

    // action for the stop button
    private OnClickListener btnGStopListener = new OnClickListener() {
        public void onClick(View v){
            if (serviceIntent != null) {
                stopService(serviceIntent);
                serviceIntent = null;
            }
        }
    };
    // action for the send button
    private OnClickListener btnSendListener = new OnClickListener() {
        public void onClick(View v) {
            XmitSpeed = Integer.parseInt(XmitSpeededitText.getText().toString());
            if (XmitSpeed < 5 || XmitSpeed > 50) {
                //display in short period of time
                Toast.makeText(getApplicationContext(), "The transmit speed must be between 5 and 50, inclusive.", Toast.LENGTH_SHORT).show();
                XmitSpeed = 13;
                XmitSpeededitText.setText("13");
            } else {


                serviceIntent = new Intent(getBaseContext(), MorsePlayerService.class);
                serviceIntent.putExtra("message",generatedQSOEditText.getText().toString());
                serviceIntent.putExtra("speed",XmitSpeed);
                serviceIntent.putExtra("tone",1000);
                startService(serviceIntent);
//                startService(new Intent(getBaseContext(), MorsePlayerService.class));
            }
        }
    };

}

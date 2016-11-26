package com.cebess.qsosender2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String ProjectName = "qsosender";
    public static Boolean DEBUG = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    // Declare the User Interface elements
    EditText generatedQSOEditText;
    EditText XmitSpeededitText;
    Button generateButton;
    Button sendButton;

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

        //install listeners
        generateButton.setOnClickListener(btnGenerateListener);
        sendButton.setOnClickListener(btnSendListener);
        generateMessage();

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
    // action for the send button
    private OnClickListener btnSendListener = new OnClickListener() {
        public void onClick(View v){
            XmitSpeed = Integer.parseInt(XmitSpeededitText.getText().toString());
            if (XmitSpeed < 5 || XmitSpeed > 50) {
                //display in short period of time
                Toast.makeText(getApplicationContext(), "The transmit speed must be between 5 and 50, inclusive.", Toast.LENGTH_SHORT).show();
                XmitSpeed = 13;
                XmitSpeededitText.setText("13");
            } else {
                MorsePlayer myMorsePlayer = new MorsePlayer(1000,XmitSpeed);
                myMorsePlayer.setMessage(generatedQSOEditText.getText().toString());
                myMorsePlayer.playMorse();
            }
        }
    };

}

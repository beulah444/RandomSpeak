package com.preethi.randomspeak;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import java.util.Locale;


public class MainActivity extends Activity {
    String text;
    TextView et;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textOne = (TextView) findViewById(R.id.textView);
        Button pushMe = (Button) findViewById(R.id.button);
        final String[] RandomText = {"Please Push Top Left Button","Please Push Top Right Button","Please Push Bottom Right Button","Please Push Bottom Left Button","Please Push Center Button"};

        pushMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int random = (int) (Math.random()*5);
                textOne.setText(RandomText[random]);
                et=(TextView)findViewById(R.id.textView);
                tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.US);
                            if(result==TextToSpeech.LANG_MISSING_DATA ||
                                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                            }
                            else{
                                ConvertTextToSpeech();
                            }
                        }
                        else
                            Log.e("error", "Initilization Failed!");
                    }
                });
            }
        });

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    public void onClick(View v){

        ConvertTextToSpeech();

    }

    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        text = et.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}




package modular.cic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class InitialLoadingActivity extends Activity{

    private String LOG_TAG = "InitialLoadingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_loading);
        final TextView textView = (TextView)findViewById(R.id.textView);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    //Loading stuff happens here

                    updateText(textView, "Loading...");
                    Thread.sleep(2000);
                    Log.i(LOG_TAG, "First sleep done");
                    updateText(textView, "Gathering Profile information...");
                    Log.i(LOG_TAG, "Set text second time");
                    Thread.sleep(5000);
                    Log.i(LOG_TAG,"Second sleep finished");
                    updateText(textView, "Finishing up...");
                    Thread.sleep(9000);

                } catch (Exception e) {
                    Log.e(LOG_TAG,"Error here!!");
                    e.printStackTrace();
                } finally {
                    //Bundle important information to send over here
                    Intent i = new Intent(InitialLoadingActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
    private void updateText(final TextView textView, final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}

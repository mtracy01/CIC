package modular.cic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import modular.cic.HelperComponents.FacebookHelper;
import modular.cic.MainComponents.DeviceSnooper;
import modular.cic.Objects.Device;


public class InitialLoadingActivity extends Activity{

    private String LOG_TAG = "InitialLoadingActivity";
    private Context context = this;

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
                    //TODO: Query for the userid, it does not exist, we need to go to the new user page, otheriwise, get other info
                    updateText(textView, "Gathering Profile information...");
                    Log.i(LOG_TAG, "Set text second time");
                    //TODO: Query the hardware id. If hardware id is new, if it is, then we should prompt user if they want to add it (or not?)
                    Device currDevice = DeviceSnooper.gatherDeviceInfo(context);
                   /* if(currDevice==null){
                        //Prompt user for if they would like to add this device to their account
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Add Device?");
                        builder.setMessage("Would you like to add this device to your account?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: Store this device into parse
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FacebookHelper.logout(context);
                            }
                        });
                        builder.show();
                    }*/
                    //TODO: Implement DeviceSnooper and Parse before uncommenting above code
                    Thread.sleep(5000);
                    Log.i(LOG_TAG,"Second sleep finished");
                    updateText(textView, "Finishing up...");
                    //TODO: Set this device as having highest priority currently.  Tell the server to push other devices to turn notifications off
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
    private void updateText(final TextView textView, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}

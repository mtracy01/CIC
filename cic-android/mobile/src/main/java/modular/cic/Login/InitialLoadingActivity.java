package modular.cic.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import modular.cic.HelperComponents.ParseHelper;
import modular.cic.MainMobileActivity;
import modular.cic.R;


public class InitialLoadingActivity extends Activity {

    private String LOG_TAG = "InitialLoadingActivity";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_loading);
        final TextView textView = (TextView) findViewById(R.id.textView);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    //Loading stuff happens here

                    updateText(textView, "Loading...");
                    //Thread.sleep(2000);
                    Log.i(LOG_TAG, "First sleep done");
                    //TODO: Query for the userid, it does not exist, we need to go to the new user page, otheriwise, get other info
                    updateText(textView, "Gathering Profile information...");
                    Log.i(LOG_TAG, "Set text second time");
                    //TODO: Query the hardware id. If hardware id is new, if it is, then we should prompt user if they want to add it (or not?)
                    //Device currDevice = DeviceSnooper.gatherDeviceInfo(context);
                    final String hid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                    //TODO: Check parse for id
                    ParseQuery query = new ParseQuery("Device");
                    String oid = ParseUser.getCurrentUser().getObjectId();
                    query.whereContains("ownerId", oid);
                    query.whereContains("deviceId", hid);
                    List devices = query.find();
                    Log.i(LOG_TAG, "Devices size: " + devices.size());
                    if (devices.size() == 0) {
                        //Prompt user for if they would like to add this device to their account
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Add Device?");
                        builder.setMessage("Would you like to add this device to your account?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: Store this device into parse
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                View v = getLayoutInflater().inflate(R.layout.add_device, null);  //(context, R.layout.add_device);
                                builder.setView(v);

                                final EditText deviceNameText = (EditText) v.findViewById(R.id.devNameText);
                                final Spinner deviceType = (Spinner) v.findViewById(R.id.spinner1);
                                final Spinner pDevice = (Spinner) v.findViewById(R.id.spinner2);

                                ArrayList<String> deviceTypes = new ArrayList<>();
                                deviceTypes.add("Phone");
                                deviceTypes.add("Tablet");
                                deviceTypes.add("Android PC");
                                ArrayList<String> yNo = new ArrayList<>();
                                yNo.add("No");
                                yNo.add("Yes");
                                ArrayAdapter adapter1 = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, deviceTypes);
                                ArrayAdapter adapter2 = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, yNo);
                                deviceType.setAdapter(adapter1);
                                pDevice.setAdapter(adapter2);
                                builder.setPositiveButton("Add Device", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ParseQuery query = new ParseQuery("User");
                                        query.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
                                        query.include("deviceCount");
                                        Integer deviceCount = 0;
                                        try {
                                            List l = query.find();
                                            Log.i(LOG_TAG, "List size: " + l.size());
                                            if (l.size() > 0)
                                                deviceCount = (Integer) l.get(0);

                                        } catch (ParseException e) {
                                            //TODO: Print error
                                        }
                                        //TODO: Add device to Parse under this owner
                                        ParseObject device = new ParseObject("Device");
                                        String ownerId = ParseUser.getCurrentUser().getObjectId();
                                        String deviceId = hid;
                                        Log.i(LOG_TAG, "Owner Id: " + ownerId + "DeviceId: " + deviceId);
                                        device.put("ownerId", ParseUser.getCurrentUser().getObjectId());
                                        device.put("deviceType", deviceType.getSelectedItem());
                                        device.put("deviceName", deviceNameText.getText().toString());
                                        device.put("deviceId", hid);
                                        device.put("deviceModel", Build.PRODUCT);
                                        if (deviceType.getSelectedItemPosition() == 1)
                                            device.put("notificationPriority", 0);
                                        else
                                            device.put("notificationPriority", deviceCount);
                                        deviceCount++;
                                        ParseUser.getCurrentUser().put("deviceCount", deviceCount);
                                        try {
                                            ParseUser.getCurrentUser().save();
                                            device.save();
                                            Thread.sleep(1000);
                                            startActivity(new Intent(InitialLoadingActivity.this, MainMobileActivity.class));
                                        } catch (Exception e) {
                                            Log.e(LOG_TAG, e.getMessage());
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                builder.setNegativeButton("Don't Add", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //TODO: Do nothing
                                    }
                                });
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        builder.show();
                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ParseHelper.logout(context);
                            }
                        });
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                builder.show();
                            }
                        });

                    } else {
                        //TODO: Implement Changing device priority

                        updateText(textView, "Finishing up...");
                        ParseObject device = (ParseObject) devices.get(0);
                        device.put("notificationStatus", 2);
                        device.put("notificationPriority", 0);
                        device.save();
                        //TODO: Server side: Tell the server to push other devices to turn notifications off
                        startActivity(new Intent(InitialLoadingActivity.this, MainMobileActivity.class));
                    }

                } catch (Exception e) {
                    Log.e(LOG_TAG, "Error here!!");
                    e.printStackTrace();
                } finally {
                    //Bundle important information to send over here

                    //finish();
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

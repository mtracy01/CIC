package modular.cic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import modular.cic.HelperComponents.App;
import modular.cic.HelperComponents.DialogHelper;
import modular.cic.HelperComponents.ParseHelper;
import modular.cic.MainComponents.DeviceListAdapter;


public class MainMobileActivity extends Activity {

    private final Context context = this;
    private final Activity activity = this;
    private String LOG_TAG = "MainMobileActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        updateDeviceList();
        //TODO: Create progress dialog, create setup menu for first time user
    }

    @OnClick(R.id.imageButton)
    void createLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout?");
        builder.setMessage("Would you like to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ParseHelper.logout(context);
            }
        });
        builder.setNegativeButton("No", DialogHelper.cancel);
        builder.show();
    }

    @OnClick(R.id.refreshButton)
    protected void updateDeviceList() {
        final ListView listView = (ListView) findViewById(R.id.expandableListView);
        Runnable updateTask = new Runnable() {
            @Override
            public void run() {
                List queryResults = null;
                App.devices.clear();
                ArrayList<String> deviceNames = new ArrayList<>();
                ParseQuery parseQuery = new ParseQuery("Device");
                parseQuery.whereContains("ownerId", ParseUser.getCurrentUser().getObjectId());
                try {
                    queryResults = parseQuery.find();
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                    e.printStackTrace();
                }
                if (queryResults == null) return;
                for (Object o : queryResults) {
                    ParseObject device = (ParseObject) o;
                    App.devices.add(device);
                    deviceNames.add((String) device.get("deviceName"));
                }

                //Create adapter and update UI
                String[] deviceNamesArray = new String[deviceNames.size()];
                deviceNamesArray = deviceNames.toArray(deviceNamesArray);
                DeviceListAdapter adapter = new DeviceListAdapter(activity, deviceNamesArray);
                listView.setAdapter(adapter);
            }
        };
        updateTask.run();
    }


    @Override
    public void onBackPressed() {
        createLogoutDialog();
    }

}

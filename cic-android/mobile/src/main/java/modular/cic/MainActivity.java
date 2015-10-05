package modular.cic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import butterknife.OnClick;
import modular.cic.HelperComponents.ParseHelper;


public class  MainActivity extends Activity{

    private final Context context = this;
    private String LOG_TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO: Get information from database and set up list stuff here
        updateDeviceList();


        //TODO: Create progress dialog, create setup menu for first time user
    }


    protected void updateDeviceList(){
        ListView listView = (ListView)findViewById(R.id.expandableListView);
        //TODO: Get device information

        ParseQuery query = new ParseQuery("Device");
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                if(list.size()==0)
                    Log.e(LOG_TAG,"Empty device list!");
                Iterator iterator = list.iterator();
                do{
                    ParseObject device = (ParseObject)iterator.next();

                }while(iterator.hasNext());
            }

            @Override
            public void done(Object o, Throwable throwable) {

            }
        });
    }
    @OnClick(R.id.imageButton) void createLogoutDialogt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout Menu");
        builder.setMessage("Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ParseHelper.logout(context);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed(){
        //Logout the user
        ParseHelper.logout(this);
    }
    //TODO: Create main menu, create fragments for the following:
    //          My Devices
    //          Find pictures for devices
    //          Settings

}

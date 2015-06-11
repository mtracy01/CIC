package modular.cic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.parse.Parse;

import modular.cic.HelperComponents.FacebookHelper;


public class  MainActivity extends Activity{

    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "dfxawm7UMzEWbPPRObtn73GRLUHwdQTZybnNnrZw", "fdCWMSD5OXw1z3KCFuW73kLxDr8iRvWmJ0KWiKTs");
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Logout Menu");
                builder.setMessage("Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FacebookHelper.logout(context);
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
        });

        //TODO: Create progress dialog, create setup menu for first time user
    }

    @Override
    public void onBackPressed(){
        //Logout the user
        FacebookHelper.logout(this);
    }
    //TODO: Create main menu, create fragments for the following:
    //          My Devices
    //          Find pictures for devices
    //          Settings

}

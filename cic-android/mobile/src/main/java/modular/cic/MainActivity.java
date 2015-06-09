package modular.cic;

import android.app.Activity;
import android.os.Bundle;

import com.parse.Parse;


public class  MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "dfxawm7UMzEWbPPRObtn73GRLUHwdQTZybnNnrZw", "fdCWMSD5OXw1z3KCFuW73kLxDr8iRvWmJ0KWiKTs");

        //TODO: Create progress dialog, create setup menu for first time user
    }

    //TODO: Create main menu, create fragments for the following:
    //          My Devices
    //          Find pictures for devices
    //          Settings
}

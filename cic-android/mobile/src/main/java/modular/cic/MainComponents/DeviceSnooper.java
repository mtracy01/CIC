package modular.cic.MainComponents;

import android.content.Context;
import android.provider.Settings;

import modular.cic.Objects.Device;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Gather device info, add it to the database if necessary
 */
public class DeviceSnooper {
    //Return non-null device if we should prompt user to add this device to their devices
    public static Device gatherDeviceInfo(Context context) {
        //Get device id
        String hid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        return null;
    }

}

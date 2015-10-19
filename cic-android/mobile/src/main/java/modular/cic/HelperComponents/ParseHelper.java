package modular.cic.HelperComponents;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.Settings;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import modular.cic.Login.SignInActivity;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Simple wrapper for quick access to parse requests used in the app
 */
public class ParseHelper {
    public static void logout(Context context) {
        //TODO: Set device as offline/unavailable when user logs out
        ParseQuery parseQuery = new ParseQuery("Device");
        parseQuery.whereContains("deviceId", Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        parseQuery.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                ParseObject parseObject = (ParseObject) list.get(0);
                parseObject.add("notificationStatus", 0);
            }

            @Override
            public void done(Object o, Throwable throwable) {

            }
        });
        ParseUser.logOut();
        Intent i = new Intent(context, SignInActivity.class);
        context.startActivity(i);
    }

    public static ArrayList<Bitmap> getDeviceImages(Resources resources) {
        ArrayList<Bitmap> results = new ArrayList<>();
        for (ParseObject parseObject : App.devices) {
            String deviceModel = (String) parseObject.get("deviceModel");
            results.add(ImageSelector.selectImage(deviceModel, resources));
        }
        return results;
    }

    public static ArrayList<Integer> getDeviceStatuses() {
        ArrayList<Integer> results = new ArrayList<>();
        for (ParseObject parseObject : App.devices)
            results.add(parseObject.getInt("notificationStatus"));
        return results;
    }
}

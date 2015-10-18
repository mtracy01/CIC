package modular.cic.HelperComponents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

import modular.cic.Login.SignInActivity;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Simple wrapper for quick access to parse requests used in the app
 */
public class ParseHelper {
    public static void logout(Context context) {
        ParseUser.logOut();
        Intent i = new Intent(context, SignInActivity.class);
        context.startActivity(i);
    }

    public static ArrayList<Bitmap> getDeviceImages(){
        ArrayList<Bitmap> results = new ArrayList<>();
        for(ParseObject parseObject: App.devices){
            String deviceModel = (String)parseObject.get("deviceModel");
            results.add(ImageSelector.selectImage(deviceModel));
        }
        return results;
    }
    public static ArrayList<Integer> getDeviceStatuses(){
        ArrayList<Integer> results = new ArrayList<>();
        for(ParseObject parseObject: App.devices){
            results.add(parseObject.getInt("notificationStatus"));
        }
        return results;
    }
}

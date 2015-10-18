package modular.cic.HelperComponents;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;

import modular.cic.R;

/**
 * Created by matthew on 10/18/2015.
 * Purpose: Assign image to device based on phone model
 */
public class ImageSelector {
    public static Bitmap selectImage(String deviceModel){
        Bitmap result;
        switch(deviceModel){
            /*case "Nexus 6":

                break;
            case "Nexus 7":

                break;*/
            default:
                result = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nexus_6_140x196);
                break;
        }
        return result;
    }
}

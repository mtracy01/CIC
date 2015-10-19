package modular.cic.HelperComponents;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import modular.cic.R;

/**
 * Created by matthew on 10/18/2015.
 * Purpose: Assign image to device based on phone model
 */
public class ImageSelector {
    public static Bitmap selectImage(String deviceModel, Resources resources) {
        Bitmap result;
        switch (deviceModel) {
            case "shamu":
                result = BitmapFactory.decodeResource(resources, R.drawable.nexus_6_140x196);
                break;
            case "razor":
                result = BitmapFactory.decodeResource(resources, R.drawable.nexus_7_140x196);
                break;
            default:
                result = BitmapFactory.decodeResource(resources, R.drawable.nexus_6_140x196);
                break;
        }
        return result;
    }
}

package modular.cic.MainComponents;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import modular.cic.R;


/**
 * Created by matthew on 5/27/15.
 * This will be the list adapter used for displaying devices in the main activity.
 */
public class DeviceListAdapter extends ArrayAdapter<String>{
    private Image[] image;      //Image for device
    private String[] name;      //Name of device
    private int[] status;       //Status of device

    public DeviceListAdapter(Activity context, String[] names, Image[] images, int[] statuss){
        super(context,R.layout.devicelistadapter,names);
        this.image = images;
        this.name = names;
        this.status = statuss;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        //TODO: Finish this
        return null;
    }
}

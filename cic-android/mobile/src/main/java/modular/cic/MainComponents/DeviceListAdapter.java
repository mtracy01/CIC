package modular.cic.MainComponents;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import modular.cic.R;


/**
 * Created by matthew on 5/27/15.
 * This will be the list adapter used for displaying devices in the main activity.
 */
public class DeviceListAdapter extends ArrayAdapter<String>{
    private Activity context;
    private Bitmap[] image;      //Image for device
    private String[] name;      //Name of device
    private int[] status;       //Status of device

    public DeviceListAdapter(Activity context, String[] names, Bitmap[] images, int[] statuss){
        super(context,R.layout.devicelistadapter,names);
        this.context=context;
        this.image = images;
        this.name = names;
        this.status = statuss;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();//TODO: Finish this
        View rowView = inflater.inflate(R.layout.devicelistadapter, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        RadioButton bttn = (RadioButton) rowView.findViewById(R.id.bttn);

        //Set values for row
        textView.setText(name[position]);
        imageView.setImageBitmap(image[position]);
        bttn.setChecked(true);

        switch(status[position]) {
            case 0:
                //Device offline / unavailable
                bttn.setTextColor(Color.RED);
                break;
            case 1:
                //Device sleeping
                bttn.setTextColor(Color.YELLOW);
                break;
            case 2:
                //Device online / is primary receiver
                bttn.setTextColor(Color.GREEN);
                break;
            default:
                //Status unknown
                bttn.setTextColor(Color.BLACK);
                break;
        }
        return rowView;
    }
}

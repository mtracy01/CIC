package modular.cic.MainComponents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import modular.cic.DeviceInformationActivity;
import modular.cic.HelperComponents.ParseHelper;
import modular.cic.R;


/**
 * Created by matthew on 5/27/15.
 * This will be the list adapter used for displaying devices in the main activity.
 */
public class DeviceListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] name;      //Name of devices

    public DeviceListAdapter(Activity context, String[] names) {
        super(context, R.layout.devicelistadapter, names);
        this.context = context;
        this.name = names;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.devicelistadapter, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        RadioButton bttn = (RadioButton) rowView.findViewById(R.id.bttn);
        final int position2 = position;
        //Get device info from parse objects
        ArrayList<Bitmap> image = ParseHelper.getDeviceImages(getContext().getResources());
        ArrayList<Integer> status = ParseHelper.getDeviceStatuses();
        //Set values for row
        textView.setText(name[position]);
        if (image.size() == 0) {
            Log.e("DeviceListAdapter", "Bug condition");
            return rowView;
        }
        imageView.setImageBitmap(image.get(position));
        bttn.setChecked(true);

        switch (status.get(position)) {
            case 0:
                //Device offline / unavailable
                bttn.setBackgroundColor(Color.RED);
                break;
            case 1:
                //Device sleeping
                bttn.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                //Device online / is primary receiver
                bttn.setBackgroundColor(Color.GREEN);
                break;
            default:
                //Status unknown
                bttn.setBackgroundColor(Color.BLACK);
                break;
        }
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DeviceInformationActivity.class);
                i.putExtra("devicePosition", position2);
                context.startActivity(i);
            }
        });
        return rowView;
    }
}

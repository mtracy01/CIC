package modular.cic.Objects;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by matthew on 6/1/2015.
 * Purpose: Details of a person's device
 */
public class Device {
    public String name = "";
    public Integer type = -1;    //-1 = unassigned
    public int notification_priority = -1;
    public boolean notification_status = true;
    public LatLng location = new LatLng(0, 0);
}

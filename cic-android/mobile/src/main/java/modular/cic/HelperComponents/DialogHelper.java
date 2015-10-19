package modular.cic.HelperComponents;

import android.content.DialogInterface;

/**
 * Created by matthew on 10/18/2015.
 * Purpose: Holds common functions for dialogs
 */
public class DialogHelper {
    public static DialogInterface.OnClickListener cancel = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };
}

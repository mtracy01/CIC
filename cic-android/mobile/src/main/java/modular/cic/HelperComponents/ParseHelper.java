package modular.cic.HelperComponents;

import android.content.Context;
import android.content.Intent;

import com.parse.ParseUser;

import modular.cic.Login.SignInActivity;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Simple wrapper for quick access to facebook requests used in the app
 */
public class ParseHelper {
    public static void logout(Context context) {
      /*  LoginManager manager = LoginManager.getInstance();
        manager.logOut();
        */
        ParseUser.logOut();
        Intent i = new Intent(context, SignInActivity.class);
        context.startActivity(i);
    }
}

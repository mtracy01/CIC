package modular.cic.MainComponents;

import android.content.Context;
import android.content.Intent;

import com.facebook.login.LoginManager;

import modular.cic.Login;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Simple wrapper for quick access to facebook requests used in the app
 */
public class FacebookHelper {
    public static void logout(Context context){
        LoginManager manager = LoginManager.getInstance();
        manager.logOut();
        Intent i = new Intent (context, Login.class);
        context.startActivity(i);
    }
}

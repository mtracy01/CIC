package modular.cic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import modular.cic.Objects.User;

public class Login extends Activity {

    private CallbackManager callbackManager;
    private String LOG_TAG = "Login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        final Context context = this;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.i(LOG_TAG, "Success");
                        //Advance to MainActivity
                        final ProgressDialog progDialog = ProgressDialog.show(context, "Setting up...",
                                "Loading personal information and device info.", true);
                        new Thread() {
                            public void run() {
                                try {
                                    // sleep the thread, whatever time you want.
                                    GraphRequest userInfoRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                            //Once we get the info we need, store it in our user object
                                            String rawResponse = jsonObject.toString();
                                            Log.v(LOG_TAG, "Raw Response from Request:" + rawResponse);
                                            try {
                                                User.userid = jsonObject.getString("id");
                                                User.first_name = jsonObject.getString("first_name");
                                                User.last_name = jsonObject.getString("last_name");
                                            } catch (JSONException e) {
                                                Log.e(LOG_TAG, e.getMessage());
                                            }
                                        }
                                    });
                                    userInfoRequest.executeAndWait();
                                    //If we need to get information from the web service, we will do it in MainActivity
                                    //TODO: Handle issues with graph requests
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                } catch (Exception e) {
                                    //Do something maybe.
                                }
                                progDialog.dismiss();
                            }
                        }.start();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.i(LOG_TAG, "Cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.i(LOG_TAG, "Error");
                    }
                });
        //Set up 3d view
        //ParallaxImageView mLogo = findViewById(R.id.imageView);
        //mLogo.registerSensorManager();
    }


    @Override
    protected void onResume(){
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

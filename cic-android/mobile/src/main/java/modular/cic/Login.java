package modular.cic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import modular.cic.HelperComponents.App;
import modular.cic.Objects.User;

public class Login extends Activity {
    private CallbackManager callbackManager;
    private String LOG_TAG = "Login";
    private boolean first = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!first){
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "dfxawm7UMzEWbPPRObtn73GRLUHwdQTZybnNnrZw", "fdCWMSD5OXw1z3KCFuW73kLxDr8iRvWmJ0KWiKTs");
            first=true;
        }

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        //Temporary button for killing the app
        Button killButton = (Button) findViewById(R.id.button);
        killButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.kill();
            }
        });


        final Context context = this;
        callbackManager = CallbackManager.Factory.create();

        /*LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.i(LOG_TAG, "Success");
                        final Bundle bundle = new Bundle();

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
                                                //TODO: Find parse user.  If one does not exist, make one
                                                ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("user");
                                                try {
                                                    ParseObject result = userQuery.get(User.userid);
                                                    //If the user is new
                                                    if (result == null) {
                                                        bundle.putBoolean("new", true);
                                                    }
                                                    //User is not new
                                                    else {
                                                        bundle.putBoolean("new", false);
                                                        result.put("updatedAt", new Date());
                                                    }
                                                } catch (ParseException e) {
                                                    Log.e(LOG_TAG, "Error fetching parse data");
                                                }
                                            } catch (JSONException e) {
                                                //TODO: Handle error here
                                                Toast.makeText(context, "Error fetching user data!", Toast.LENGTH_SHORT).show();
                                                //Logout
                                                Log.e(LOG_TAG, e.getMessage());
                                            }
                                        }
                                    });
                                    userInfoRequest.executeAndWait();
                                    //If we need to get information from the web service, we will do it in MainActivity
                                    //TODO: Handle issues with graph requests & do initial queries.

                                } catch (Exception e) {
                                    //Do something maybe.
                                }
                                progDialog.dismiss();
                            }
                        }.start();
                        Intent i = new Intent(Login.this, InitialLoadingActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
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
                });*/

        //TODO: Add parse login stuff here

    }

    @Override
    protected void onResume() {
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

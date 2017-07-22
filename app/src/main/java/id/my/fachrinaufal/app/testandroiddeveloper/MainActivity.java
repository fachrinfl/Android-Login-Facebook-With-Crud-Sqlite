package id.my.fachrinaufal.app.testandroiddeveloper;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import id.my.fachrinaufal.app.bottomdialoglibrary.BottomDialog;

public class MainActivity extends AppCompatActivity {

    private Button btn_fblogin;
    private AccessToken accessToken;
    private CallbackManager callbackManager;
    private TextView tv_id,tv_name,tv_email, tv_gender, tv_or, tv_menu;
    private ImageView iv_profile;
    private LinearLayout ll_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_login.setBackground(getResources().getDrawable(R.drawable.background));

        String hashkey = getHashKey();
        Log.d("hashKey::", hashkey);
        // ga0RGNYHvNM5d0SLGQfpQWAPGJ8=
        btn_fblogin = (Button) findViewById(R.id.btn_fblogin);
        btn_fblogin.setText("Login with Facebook");
        btn_fblogin.setTextColor(Color.WHITE);
        btn_fblogin.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        btn_fblogin.setTextSize(18);
        btn_fblogin.setAllCaps(false);
        btn_fblogin.setBackground(getResources().getDrawable(R.drawable.background_button));
        callbackManager = CallbackManager.Factory.create();
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_or = (TextView) findViewById(R.id.tv_or);
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        processFacebookLogin();

        tv_or.setText("or");
        tv_or.setTextColor(Color.WHITE);
        tv_or.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        tv_or.setTextSize(18);

        tv_menu.setText("Go to next page");
        tv_menu.setTextColor(Color.WHITE);
        tv_menu.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        tv_menu.setTextSize(18);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Activity_Crud.class));
            }
        });

    }

    private void processFacebookLogin() {
        if(accessToken !=null){
            accessToken = com.facebook.AccessToken.getCurrentAccessToken();
            LoginManager.getInstance().logOut();
        }
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Granted permission::"+loginResult.getRecentlyGrantedPermissions());

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String userDetail = response.getRawResponse();
                        try {
                            JSONObject jsonObject = new JSONObject(userDetail);
                            System.out.println("jsonObject::"+jsonObject);

                            String facebookId = jsonObject.getString("id");
                            tv_id.setText("Facebook ID : "+facebookId);
                            tv_id.setTextColor(Color.WHITE);
                            tv_id.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                            tv_id.setTextSize(18);

                            String namefb = jsonObject.getString("name");
                            tv_name.setText("Name : "+namefb);
                            tv_name.setTextColor(Color.WHITE);
                            tv_name.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                            tv_name.setTextSize(18);

                            String genderfb = jsonObject.getString("gender");
                            tv_gender.setText("Gender : "+genderfb);
                            tv_gender.setTextColor(Color.WHITE);
                            tv_gender.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                            tv_gender.setTextSize(18);

                            String emailfb = jsonObject.getString("email");
                            tv_email.setText("Email : "+emailfb);
                            tv_email.setTextColor(Color.WHITE);
                            tv_email.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                            tv_email.setTextSize(18);

                            String facebookImage = "https://graph.facebook.com/"+facebookId+"/picture?type=large";
                            Glide.with(getApplicationContext()).load(facebookImage).into(iv_profile);


                            btn_fblogin.setText("Logout");
                            btn_fblogin.setTextColor(Color.WHITE);
                            btn_fblogin.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                            btn_fblogin.setTextSize(18);
                            btn_fblogin.setAllCaps(false);
                            btn_fblogin.setBackground(getResources().getDrawable(R.drawable.background_button));
                            btn_fblogin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new BottomDialog.Builder(MainActivity.this)
                                            .setTitle("Logout")
                                            .setContent("Do you want to close your account and exit this app ?")
                                            .setPositiveText("Yes")
                                            .onPositive(new BottomDialog.ButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull BottomDialog dialog) {
                                                    accessToken = com.facebook.AccessToken.getCurrentAccessToken();
                                                    LoginManager.getInstance().logOut();
                                                    processFacebookLogin();
                                                    finish();
                                                }
                                            })
                                            .setNegativeText("No")
                                            .onNegative(new BottomDialog.ButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull BottomDialog dialog) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();
                                }
                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email,gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                System.out.println("LOGIN CANCEL");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("NETWORK ERROR");

            }
        });
        btn_fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile, email, user_videos"));
            }
        });
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    public String getHashKey(){
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures){
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return Base64.encodeToString(md.digest(), Base64.DEFAULT);
                }
        }catch (PackageManager.NameNotFoundException e){
            return "SHA-1 generation; the key count not be generated: NameNotFoundException thrown";
        }catch (NoSuchAlgorithmException e){
            return "SHA-1 generation; the key count not be generated: NameNotFoundException thrown";
        }
        return "SHA-1 generation: epic failed";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new BottomDialog.Builder(MainActivity.this)
                    .setTitle("Exit")
                    .setContent("Do you want to close this app ?")
                    .setPositiveText("Yes")
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(@NonNull BottomDialog dialog) {
                            accessToken = com.facebook.AccessToken.getCurrentAccessToken();
                            LoginManager.getInstance().logOut();
                            processFacebookLogin();
                            finish();
                        }
                    })
                    .setNegativeText("No")
                    .onNegative(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(@NonNull BottomDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .show();

            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

}

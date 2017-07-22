package id.my.fachrinaufal.app.testandroiddeveloper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_Welcome extends AppCompatActivity {

    private ImageView iv_icon;
    private TextView tv_title;
    private RelativeLayout rl_welcome;
    private Thread splashTread;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        rl_welcome.setBackground(getResources().getDrawable(R.drawable.background));

        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_icon.setImageDrawable(getResources().getDrawable(R.drawable.sml));

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Test Android Developer");
        tv_title.setTextColor(Color.WHITE);
        tv_title.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        tv_title.setTextSize(28);

        StartAnimations();

    }

    private void StartAnimations(){

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        tv_title.clearAnimation();
        tv_title.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Activity_Welcome.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Activity_Welcome.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Activity_Welcome.this.finish();
                }

            }
        };
        splashTread.start();
    }
}

package com.example.sneakerlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Hammad Akram on 5/24/2017.
 */

public class Sneaker implements View.OnClickListener {

    private static final int DEFAULT_VALUE = -100000;
    private static int mIcon = DEFAULT_VALUE;
    private static int mBackgroundColor = DEFAULT_VALUE;
    private static int mHeight = DEFAULT_VALUE;
    private static int mIconColorFilterColor = DEFAULT_VALUE;
    private static String mTitle = "";
    private static String mMessage = "";
    private static int mTitleColor = DEFAULT_VALUE;
    private static int mMessageColor = DEFAULT_VALUE;
    private static boolean mAutoHide = true;
    private static int mDuration = 3000;
    private static WeakReference<LinearLayout> layoutWeakReference;
    private static WeakReference<Activity> contextWeakReference;
    private static boolean mIsCircular = false;
    private static OnSneakerClickListener mListener = null;

    /**
     * Constructor
     *
     * @param activity
     */
    private Sneaker(Activity activity) {
        contextWeakReference = new WeakReference<>(activity);
    }

    /**
     * Create Sneaker with activity reference
     *
     * @param activity
     * @return
     */
    public static Sneaker with(Activity activity) {
        Sneaker sneaker = new Sneaker(activity);
        setDefault();
        return sneaker;
    }

    /**
     * Hides the sneaker
     */
    public static void hide() {
        if (getLayout() != null) {
            getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
            getActivityDecorView().removeView(getLayout());
        }
    }

    /**
     * Return activity parent view
     *
     * @return
     */
    private static ViewGroup getActivityDecorView() {
        ViewGroup decorView = null;

        decorView = (ViewGroup) ((Activity) getContext()).getWindow().getDecorView();

        return decorView;
    }

    /**
     * Sets the default values to the sneaker
     */
    private static void setDefault() {
        mTitle = "";
        mIcon = DEFAULT_VALUE;
        mIconColorFilterColor = DEFAULT_VALUE;
        mBackgroundColor = DEFAULT_VALUE;
        mAutoHide = true;
        mTitleColor = DEFAULT_VALUE;
        mMessageColor = DEFAULT_VALUE;
        mHeight = DEFAULT_VALUE;
        mIsCircular = false;
        mListener = null;
    }

    /**
     * Return activity weak reference
     *
     * @return
     */
    private static Context getContext() {
        return contextWeakReference.get();
    }

    /**
     * Returns sneaker main layout weak reference
     *
     * @return
     */
    private static View getLayout() {
        return layoutWeakReference.get();
    }

    /**
     * Sets the title of the sneaker
     *
     * @param title string value of title
     * @return
     */
    public Sneaker setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * Sets the title of the sneaker with color
     *
     * @param title string value of title
     * @param color Color resource for title text
     * @return
     */
    public Sneaker setTitle(String title, int color) {
        mTitle = title;
        if (getContext() != null) {
            try {
                mTitleColor = ContextCompat.getColor(getContext(), color);
            } catch (Exception e) {
                mTitleColor = color;
            }
        }
        return this;
    }

    /**
     * Sets the message to sneaker
     *
     * @param message String value of message
     * @return
     */
    public Sneaker setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * Sets the message to sneaker with color
     *
     * @param message String value of message
     * @param color   Color resource for message text
     * @return
     */
    public Sneaker setMessage(String message, int color) {
        mMessage = message;
        if (getContext() != null) {
            try {
                mMessageColor = ContextCompat.getColor(getContext(), color);
            } catch (Exception e) {
                mMessageColor = color;
            }
        }
        return this;
    }

    /**
     * Sets the icon to sneaker
     *
     * @param icon Icon resource for sneaker
     * @return
     */
    public Sneaker setIcon(int icon) {
        mIcon = icon;
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option
     *
     * @param icon
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(int icon, boolean isCircular) {
        mIcon = icon;
        mIsCircular = isCircular;
        return this;
    }

    public Sneaker setIcon(int icon, int tintColor) {
        mIcon = icon;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    /**
     * Sets the icon to sneaker with circular option and icon tint
     *
     * @param icon
     * @param tintColor  Icon tint color
     * @param isCircular If icon is round or not
     * @return
     */
    public Sneaker setIcon(int icon, int tintColor, boolean isCircular) {
        mIcon = icon;
        mIsCircular = isCircular;
        if (getContext() != null) {
            try {
                mIconColorFilterColor = ContextCompat.getColor(getContext(), tintColor);
            } catch (Exception e) {
                mIconColorFilterColor = tintColor;
            }
        }
        return this;
    }

    /**
     * Disable/Enable auto hiding sneaker
     *
     * @param autoHide
     * @return
     */
    public Sneaker autoHide(boolean autoHide) {
        mAutoHide = autoHide;
        return this;
    }

    /**
     * Sets the height to sneaker
     *
     * @param height Height value for sneaker
     * @return
     */
    public Sneaker setHeight(int height) {
        mHeight = height;
        return this;
    }

    /**
     * Sets the duration for sneaker.
     * After this duration sneaker will disappear
     *
     * @param duration
     * @return
     */
    public Sneaker setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    /**
     * Sets the click listener to sneaker
     *
     * @param listener
     * @return
     */
    public Sneaker setOnSneakerClickListener(OnSneakerClickListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * Shows sneaker with custom color
     *
     * @param backgroundColor Color resource for sneaker background color
     */
    public void sneak(int backgroundColor) {
        if (getContext() != null) {
            try {
                mBackgroundColor = ContextCompat.getColor(getContext(), backgroundColor);
            } catch (Exception e) {
                mBackgroundColor = backgroundColor;
            }
            sneakView();
        }
    }

    /**
     * Shows warning sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    public void sneakWarning() {
        mBackgroundColor = Color.parseColor("#F7971E");
        mTitleColor = Color.parseColor("#FFFFFF");
        mMessageColor = Color.parseColor("#FFFFFF");
        mIconColorFilterColor = Color.parseColor("#FFFFFF");


        if (getContext() != null)
            sneakView();
    }

    /**
     * Shows error sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    public Sneaker sneakError() {
        mBackgroundColor = Color.parseColor("#BD3F32");
        mTitleColor = Color.parseColor("#FFFFFF");
        mMessageColor = Color.parseColor("#FFFFFF");
        mIconColorFilterColor = Color.parseColor("#FFFFFF");


        if (getContext() != null)
            sneakView();
        return null;
    }

    /**
     * Shows success sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    public Sneaker sneakSuccess() {
        mBackgroundColor = Color.parseColor("#45a247");
        mTitleColor = Color.parseColor("#FFFFFF");
        mMessageColor = Color.parseColor("#FFFFFF");
        mIconColorFilterColor = Color.parseColor("#FFFFFF");


        if (getContext() != null)
            sneakView();
        return null;
    }
    /**
     * Creates the view and sneaks in
     */
    private void sneakView() {

        // Main layout
        LinearLayout layout = new LinearLayout(getContext());
        layoutWeakReference = new WeakReference<>(layout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight == DEFAULT_VALUE ? (getStatusBarHeight() + convertToDp(100)) : convertToDp(mHeight));
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setPadding(46, getStatusBarHeight(), 46, 0);
        layout.setElevation(6);

        // Background color
        layout.setBackgroundColor(mBackgroundColor);

        // Icon
        // If icon is set
        if (mIcon != DEFAULT_VALUE) {
            if (!mIsCircular) {
                AppCompatImageView ivIcon = new AppCompatImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertToDp(24), convertToDp(24));
                ivIcon.setLayoutParams(lp);

                ivIcon.setImageResource(mIcon);
                ivIcon.setClickable(false);
                if (mIconColorFilterColor != DEFAULT_VALUE) {
                    ivIcon.setColorFilter(mIconColorFilterColor);
                }
                layout.addView(ivIcon);
            } else {
                RoundedImageView ivIcon = new RoundedImageView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertToDp(24), convertToDp(24));
                ivIcon.setLayoutParams(lp);

                ivIcon.setImageResource(mIcon);
                ivIcon.setClickable(false);
                if (mIconColorFilterColor != DEFAULT_VALUE) {
                    ivIcon.setColorFilter(mIconColorFilterColor);
                }
                layout.addView(ivIcon);
            }
        }

        // Title and description
        LinearLayout textLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayout.setLayoutParams(textLayoutParams);
        textLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lpTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!mTitle.isEmpty()) {
            TextView tvTitle = new TextView(getContext());
            tvTitle.setLayoutParams(lpTv);
            tvTitle.setGravity(Gravity.CENTER_VERTICAL);
            if (!mMessage.isEmpty())
                tvTitle.setPadding(46, 50, 26, 0); // Top padding if there is message
            else
                tvTitle.setPadding(46, 0, 26, 0); // No top padding if there is no message
            if (mTitleColor != DEFAULT_VALUE)
                tvTitle.setTextColor(mTitleColor);
            tvTitle.setTextSize(16);
            tvTitle.setText(mTitle);
            tvTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/palanquin/Palanquin-Light.ttf"));
            tvTitle.setClickable(false);
            textLayout.addView(tvTitle);
        }

        if (!mMessage.isEmpty()) {
            TextView tvMessage = new TextView(getContext());
            tvMessage.setLayoutParams(lpTv);
            tvMessage.setGravity(Gravity.CENTER_VERTICAL);
            if (!mTitle.isEmpty())
                tvMessage.setPadding(46, 0, 26, 26); // Bottom padding if there is title
            else
                tvMessage.setPadding(46, 0, 26, 0); // No bottom padding if there is no title
            if (mMessageColor != DEFAULT_VALUE)
                tvMessage.setTextColor(mMessageColor);
            tvMessage.setTextSize(14);
            tvMessage.setText(mMessage);
            tvMessage.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/palanquin/Palanquin-Light.ttf"));
            tvMessage.setClickable(false);
            textLayout.addView(tvMessage);
        }
        layout.addView(textLayout);
        layout.setId(R.id.mainLayout);


        final ViewGroup viewGroup = getActivityDecorView();
        getExistingOverlayInViewAndRemove(viewGroup);

        layout.setOnClickListener(this);
        viewGroup.addView(layout);

        layout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_show));
        if (mAutoHide) {
            Handler handler = new Handler();
            handler.removeCallbacks(null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
                    viewGroup.removeView(getLayout());
                }
            }, mDuration);
        }
    }

    /**
     * Gets the existing sneaker and removes before adding new one
     *
     * @param parent
     */
    public void getExistingOverlayInViewAndRemove(ViewGroup parent) {

        for (int i = 0; i < parent.getChildCount(); i++) {

            View child = parent.getChildAt(i);
            if (child.getId() == R.id.mainLayout) {
                parent.removeView(child);
            }
            if (child instanceof ViewGroup) {
                getExistingOverlayInViewAndRemove((ViewGroup) child);
            }
        }
    }

    /**
     * Returns status bar height.
     *
     * @return
     */
    private int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = ((Activity) getContext()).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;

        return contentViewTop;
    }

    private int convertToDp(float sizeInDp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (sizeInDp * scale + 0.5f);
    }

    /**
     * Sneaker on click
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onSneakerClick(view);
        }
        getLayout().startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.popup_hide));
        getActivityDecorView().removeView(getLayout());
    }

    public interface OnSneakerClickListener {
        void onSneakerClick(View view);
    }
}

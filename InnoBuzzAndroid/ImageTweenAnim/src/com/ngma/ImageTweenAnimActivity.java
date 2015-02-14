package com.ngma;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class ImageTweenAnimActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.my_layout1);
        final Animation myAnim =AnimationUtils.loadAnimation(this, R.anim.my_tween_anim);
        layout.startAnimation(myAnim);
        		}
    }

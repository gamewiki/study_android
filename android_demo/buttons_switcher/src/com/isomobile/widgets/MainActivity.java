
package com.isomobile.widgets;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends ActivityGroup implements View.OnClickListener {
    private final static Class<?>[] sActivityClasses = {
            Activity1.class, Activity2.class, Activity3.class, Activity4.class, Activity5.class
    };

    private final static int[] sResIds = {
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5
    };

    private final static String[] sActivityIds = {
            "Activity1", "Activity2", "Activity3", "Activity4", "Activity5"
    };

    private RelativeLayout mViewContainer;

    private Button[] mBtns = new Button[sResIds.length];

    private View mPreView;

    private View[] mCurView = new View[sResIds.length];

    private int mCurId = 0;

    private int mPreBtnPos, mCurBtnPos = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViews();
    }

    private void setupViews() {
        mViewContainer = (RelativeLayout) findViewById(R.id.container);
        final Button[] btns = mBtns;
        for (int i = 0; i < btns.length; i++) {
            btns[i] = (Button) findViewById(sResIds[i]);
            btns[i].setOnClickListener(this);
        }

        //第一次启动时，默认跳转到第一个activity
        mCurView[0] = getLocalActivityManager().startActivity(
                sActivityIds[0],
                new Intent(MainActivity.this, sActivityClasses[0])
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        mViewContainer.addView(mCurView[0]);
        mPreView = mCurView[0];
        mPreBtnPos = 0;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (mCurId == id) {
            return;
        }
        mCurId = id;
        processViews(id);
        onRotateAnimation(getCurButtonIndex(id));
    }

    private void processViews(int rid) {
        mViewContainer.removeAllViews();
        mCurBtnPos = getCurButtonIndex(rid);
        mCurView[mCurBtnPos] = getLocalActivityManager().startActivity(
                sActivityIds[mCurBtnPos],
                new Intent(this, sActivityClasses[mCurBtnPos])
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        mViewContainer.addView(mCurView[mCurBtnPos]);
    }

    private int getCurButtonIndex(int rid) {
        final int length = sResIds.length;
        for (int i = 0; i < length; i++) {
            if (rid == sResIds[i]) {
                return i;
            }
        }
        return 0;
    }

    public void onRotateAnimation(int index) {
        if (mPreBtnPos > mCurBtnPos) {
            Rotate3d.rightRotate(mPreView, mCurView[index], 240, 0, 300, new AnimListener());
        } else {
            Rotate3d.leftRotate(mPreView, mCurView[index], 240, 0, 300, new AnimListener());
        }

        mPreView = mCurView[index];
        mViewContainer.removeAllViews();
        mViewContainer.addView(mCurView[index]);
        mPreBtnPos = mCurBtnPos;
    }

    private class AnimListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
          //可以设置buttons的背景或者状态(是否可点击等)
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {
          //可以设置buttons的背景或者状态(是否可点击等)
        }
    }
}

package com.marliao.youkumenu.Utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtil {

    public static int runningAnimation = 0;

    public static void rotateOutAnima(RelativeLayout layout, long delay) {
        //找到隐藏布局里的所有子控件，让他们不响应点击事件
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(false);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        rotateAnimation.setFillAfter(true);//是否停留在旋转后的位置
        rotateAnimation.setDuration(500);
        rotateAnimation.setStartOffset(delay);
        layout.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new MyAnimationListener());
    }

    public static void rotateInAnima(RelativeLayout layout, long delay) {
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(true);
        }
        RotateAnimation rotateAnimation = new RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        rotateAnimation.setFillAfter(true);//是否停留在旋转后的位置
        rotateAnimation.setDuration(500);
        rotateAnimation.setStartOffset(delay);
        layout.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new MyAnimationListener());
    }

    public static class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimation++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimation--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}

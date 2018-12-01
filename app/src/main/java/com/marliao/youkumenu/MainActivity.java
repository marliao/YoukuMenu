package com.marliao.youkumenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.marliao.youkumenu.Application.MyApplication;
import com.marliao.youkumenu.Utils.AnimationUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initUI();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (AnimationUtil.runningAnimation > 0) {
            return true;
        }
        //判断是否点击数量menu键
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            long delay=0;
            //判断一级菜单是否显示
            if (MyApplication.islevel1diaplay) {
                //判断三级菜单是否显示
                if (MyApplication.islevel3diaplay) {
                    AnimationUtil.rotateOutAnima(rl_level3,0);
                    MyApplication.islevel3diaplay=false;
                    delay+=200;
                }
                //判断二级菜单是否显示
                if (MyApplication.islevel2diaplay) {
                    AnimationUtil.rotateOutAnima(rl_level2,delay);
                    MyApplication.islevel2diaplay=false;
                    delay+=200;
                }
                AnimationUtil.rotateOutAnima(rl_level1,delay);
            }else {
                AnimationUtil.rotateInAnima(rl_level1,0);
                AnimationUtil.rotateInAnima(rl_level2,200);
                AnimationUtil.rotateInAnima(rl_level3,400);
                //
                MyApplication.islevel3diaplay=true;
                MyApplication.islevel2diaplay=true;
            }
            MyApplication.islevel1diaplay=!MyApplication.islevel1diaplay;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initUI() {
        ImageButton ib_home = (ImageButton) findViewById(R.id.ib_home);
        ImageButton ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
        //给需要的控件设置点击事件
        ib_home.setOnClickListener(this);
        ib_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (AnimationUtil.runningAnimation > 0) {
            return;
        }
        switch (v.getId()) {
            case R.id.ib_home:
                //二级菜单显示的时候点击转出去
                //二级菜单不显示的时候点击转出来
                long delay=0;
                if (MyApplication.islevel2diaplay) {
                    if (MyApplication.islevel3diaplay){
                        AnimationUtil.rotateOutAnima(rl_level3,0);
                        MyApplication.islevel3diaplay=false;
                        delay+=200;
                    }
                    AnimationUtil.rotateOutAnima(rl_level2,delay);
                    MyApplication.islevel2diaplay=false;
                }else {
                    AnimationUtil.rotateInAnima(rl_level2,0);
                    MyApplication.islevel2diaplay=true;
                }
                break;
            case R.id.ib_menu:
                if (MyApplication.islevel3diaplay) {
                    AnimationUtil.rotateOutAnima(rl_level3,0);
                    MyApplication.islevel3diaplay=false;
                }else {
                    AnimationUtil.rotateInAnima(rl_level3,0);
                    MyApplication.islevel3diaplay=true;
                }
                break;
        }
    }
}

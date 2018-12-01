package com.marliao.youkumenu.Application;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyApplication extends Application {

    public static boolean islevel1diaplay=true;
    public static boolean islevel2diaplay=true;
    public static boolean islevel3diaplay=true;

    public static Context context;
    public static Toast toast;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        toast=Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public static void showToast(String text){
        toast.setText(text);
        toast.show();
    }

    public static Context getContext() {
        return context;
    }

}

package com.joker.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

   public static boolean checkPermissionAndOpen(Activity activity,int requestCode,String permission){
       if(Build.VERSION.SDK_INT>=23){
           if(ContextCompat.checkSelfPermission(activity,permission)!= PackageManager.PERMISSION_GRANTED){
                //未获取到权限
               ActivityCompat.requestPermissions(activity,new String[]{permission},requestCode);
               return false;
           }
       }
       return true;
   }

    public static boolean isOpenPermissionBySdkV(Context context, String permission){
        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public static void openPermissions(Activity activity,int requestCode,String[] permissions){

        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }
}

package com.t3k.fams.scantest.Permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void RequestPermission(Activity activity, String[] permissions, int RequestCode)
    {
        activity.requestPermissions(permissions,RequestCode);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public static List<String> getDeniedPermission(Context context, String[] Permissions )
    {
        List<String> deniedpermissionList=new ArrayList<>();
for(String permissions:Permissions)
{
if(context.checkSelfPermission(permissions) != PackageManager.PERMISSION_GRANTED) {
    deniedpermissionList.add(permissions);
}
}
int size=deniedpermissionList.size();
if(size>0)
{
    return deniedpermissionList;
}
else
{
    return null;
}
    }
}

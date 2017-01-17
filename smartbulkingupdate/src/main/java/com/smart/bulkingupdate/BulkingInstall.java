package com.smart.bulkingupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * Created by fengjh on 16/12/17.
 */

public class BulkingInstall {

    public static String getSourceApkPath(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        return apkPath;
    }

    public static void installApk(Context context, String newApkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(newApkPath)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static boolean isInstalled(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = applicationInfo.packageName;
        return isInstalled(context, packageName);
    }

    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return installed;
    }
}

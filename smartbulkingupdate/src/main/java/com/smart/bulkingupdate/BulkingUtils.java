package com.smart.bulkingupdate;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by fengjh on 16/12/19.
 */

public class BulkingUtils {

    public static void startPatch(Context context, String newApkName, String patchPath, String oldMd5, String newMd5, BulkingCallback callback) {
        String apkFolder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bulkingPatch";
        File temp = new File(apkFolder);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        File newApk = new File(temp, newApkName);
        File patchFile = new File(patchPath);
        if (!patchFile.exists()) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_PATCH_NOT_EXIST);
            }
            return;
        }
        String sourceApkPath = BulkingInstall.getSourceApkPath(context);
        File source = new File(sourceApkPath);
        if (!source.exists()) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_SOURCE_APK_NOT_EXIST);
            }
            return;
        }
        //合成完成,进行md5校验
        //进行原始版本md5校验
        boolean checkOldMd5 = BulkingSign.checkMd5(sourceApkPath, oldMd5);
        if (!checkOldMd5) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_SOURCE_MD5_FAIL);
            }
            return;
        }
        int bulkingPatch = BulkingPatch.mergeBulkingPatch(sourceApkPath, newApk.getAbsolutePath(), patchPath);
        if (bulkingPatch != 0) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_MERGE_PATCH_FAIL);
            }
            return;
        }
        if (!newApk.exists()) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_NEW_APK_NOT_EXIST);
            }
            return;
        }
        //进行新版本md5校验
        boolean checkNewMd5 = BulkingSign.checkMd5(newApk.getAbsolutePath(), newMd5);
        if (!checkNewMd5) {
            if (callback != null) {
                callback.bulkingResult(BulkingCallback.BULKING_NEW_APK_MD5_FAIL);
            }
            return;
        }
        if (callback != null) {
            callback.bulkingResult(BulkingCallback.BULKING_OK);
        }
        //进行安装
        BulkingInstall.installApk(context, newApk.getAbsolutePath());
        //删除增量包
        deletePatch(patchFile);
    }

    public static void deletePatch(String patchPath) {
        if (!TextUtils.isEmpty(patchPath))
            deletePatch(new File(patchPath));
    }

    public static void deletePatch(File patchFile) {
        if (patchFile != null)
            if (patchFile.exists())
                patchFile.delete();
    }

}

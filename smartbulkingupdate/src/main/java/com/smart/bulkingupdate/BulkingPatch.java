package com.smart.bulkingupdate;

/**
 * Created by fengjh on 16/12/17.
 */

public class BulkingPatch {

    static {
        System.loadLibrary("bulkingPatch");
    }

    public static native int generateBulkingPatch(String oldApkPath, String newApkPath, String patchPath);

    public static native int mergeBulkingPatch(String oldApkPath, String newApkPath, String patchPath);

}

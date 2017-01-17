package com.smart.bulkingupdate;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.ObjectStreamException;

/**
 * Created by fengjh on 16/12/18.
 */

public class BulkingPermission {

    public static final int PERMISSION_REQUEST_CODE = 0x123;

    private BulkingPermissionCallback mPermissionCallback;

    private BulkingPermission() {
    }

    public static BulkingPermission getInstance() {
        return BulkingPermissionHolder.mInstance;
    }

    public void checkPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            if (mPermissionCallback != null) {
                mPermissionCallback.bulkingPermission(true);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mPermissionCallback != null) {
                    mPermissionCallback.bulkingPermission(true);
                }
            } else {
                if (mPermissionCallback != null) {
                    mPermissionCallback.bulkingPermission(false);
                }
            }
        }
    }

    public void setPermissionCallback(BulkingPermissionCallback permissionCallback) {
        mPermissionCallback = permissionCallback;
    }

    private static class BulkingPermissionHolder {
        private static final BulkingPermission mInstance = new BulkingPermission();

        private Object readResolve() throws ObjectStreamException {
            return mInstance;
        }
    }
}

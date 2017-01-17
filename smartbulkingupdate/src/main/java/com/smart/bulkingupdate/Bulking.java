package com.smart.bulkingupdate;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.smart.bulkingupdate.BulkingCallback.BULKING_MERGE_PATCH_FAIL;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_NEW_APK_MD5_FAIL;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_NEW_APK_NOT_EXIST;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_OK;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_PATCH_NOT_EXIST;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_SOURCE_APK_NOT_EXIST;
import static com.smart.bulkingupdate.BulkingCallback.BULKING_SOURCE_MD5_FAIL;

/**
 * Created by fengjh on 16/12/19.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({BULKING_PATCH_NOT_EXIST, BULKING_SOURCE_APK_NOT_EXIST, BULKING_SOURCE_MD5_FAIL,
        BULKING_MERGE_PATCH_FAIL, BULKING_NEW_APK_NOT_EXIST, BULKING_NEW_APK_MD5_FAIL, BULKING_OK})
public @interface Bulking {

}

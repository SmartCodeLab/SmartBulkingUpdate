package com.smart.bulkingupdate;

/**
 * Created by fengjh on 16/12/19.
 */
public interface BulkingCallback {

    //增量包不存在，请重新下载增量包
    int BULKING_PATCH_NOT_EXIST = 0x1;
    //无法获取应用的源文件，只能完整更新啦o
    int BULKING_SOURCE_APK_NOT_EXIST = 0x2;
    //本地安装的客户端的md5不正确
    int BULKING_SOURCE_MD5_FAIL = 0x3;
    //新安装包合成失败
    int BULKING_MERGE_PATCH_FAIL = 0x4;
    //新安装包不存在
    int BULKING_NEW_APK_NOT_EXIST = 0x5;
    //新安装包合成完毕，但新安装包的md5不正确
    int BULKING_NEW_APK_MD5_FAIL = 0x6;
    //新安装包合并完成
    int BULKING_OK = 0x7;

    void bulkingResult(@Bulking int result);
}

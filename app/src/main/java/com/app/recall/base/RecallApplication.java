package com.app.recall.base;

import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.app.recall.util.SDCardUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.orhanobut.logger.Logger;

import org.litepal.LitePalApplication;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by KenChan on 16/12/13.
 * //                                           o8888888o
 * //                                           88" . "88
 * //                                           (| -_- |)
 * //                                           0\  =  /0
 * //                                         ___/`___'\___
 * //                                       .' \\|     |// '.
 * //                                      / \\|||  :  |||// \
 * //                                     / _||||| -:- |||||_ \
 * //                                    |   | \\\  _  /// |   |
 * //                                    | \_|  ''\___/''  |_/ |
 * //                                    \  .-\__  '_'  __/-.  /
 * //                                  ___'. .'  /--.--\  '. .'___
 * //                                ."" '<  .___\_<|>_/___. '>' "".
 * //                             | | :  `_ \`.;` \ _ / `;.`/ - ` : | |
 * //                             \ \  `_.   \_ ___\ /___ _/   ._`  / /
 * //                          ====`-.____` .__ \_______/ __. -` ___.`====
 * //                                           `=-----='
 * //
 * //                          ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * //                                   佛祖保佑           永无BUG
 */

public class RecallApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                .detectDiskWrites().detectNetwork().penaltyLog().build());
        MultiDex.install(this);
        SDCardUtils.cdApplicationDirectory(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        ShareSDK.initSDK(this, "1a14ebf640a26");
        Logger.init("recall").methodCount(3).hideThreadInfo();//logger 初始化
    }


}

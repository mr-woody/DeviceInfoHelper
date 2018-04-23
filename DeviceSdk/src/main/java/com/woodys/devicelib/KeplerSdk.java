package com.woodys.devicelib;

import android.content.Context;

import com.woodys.devicelib.callback.DeviceInfoListener;

import org.json.JSONObject;

/**
 * Created by zhengbing on 2018/3/19 16:52
 * phone: 13683103169
 * email: zhengbing@rrx360.com
 */

public class KeplerSdk {
    private static KeplerSdk keplerSdk;

    private KeplerSdk() {
    }

    public static KeplerSdk getInstance() {
        synchronized (KeplerSdk.class) {
            if (keplerSdk == null) {
                keplerSdk = new KeplerSdk();
            }
        }
        return keplerSdk;
    }

    /**
     * 获取上传信息
     *
     * @param context
     * @param listener
     */
    public void initSdk(Context context,DeviceInfoListener listener) {
        if(null!=listener) listener.run(DeviceUtil.getDeviceInfo(context));
    }


    public JSONObject getDeviceInfo(Context context) {
        return DeviceUtil.getDeviceInfo(context);
    }

}

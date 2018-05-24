package com.woodys.devicelib.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by woodys on 2018/3/26.
 */

public class AppInfo implements Serializable {
    public String appName;
    public String packageName;
    public String versionName;
    public int versionCode;
    public Drawable appIcon;
}

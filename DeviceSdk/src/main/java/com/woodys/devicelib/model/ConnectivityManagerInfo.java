package com.woodys.devicelib.model;

import java.io.Serializable;

/**
 * Created by woodys on 2018/3/26.
 */

public class ConnectivityManagerInfo implements Serializable {
    //connectivityManager
    public String ExtraInfo;
    public int Type;
    public String TypeName;
    public boolean isConnected;
}

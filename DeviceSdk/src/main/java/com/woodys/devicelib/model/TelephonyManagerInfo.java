package com.woodys.devicelib.model;

import java.io.Serializable;

/**
 * Created by woodys on 2018/3/26.
 */

public class TelephonyManagerInfo implements Serializable {
    //telephonyManager
    public String DeviceId;
    public String Line1Number;
    public String NetworkOperator;
    public String NetworkOperatorName;
    public int NetworkType;
    public String SimOperatorName;
    public String SimSerialNumber;
    public String SubscriberId;
}

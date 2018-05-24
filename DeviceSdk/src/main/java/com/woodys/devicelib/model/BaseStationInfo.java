package com.woodys.devicelib.model;

import java.io.Serializable;

/**
 * Created by Zhao_xl on 17/5/26.
 */
public class BaseStationInfo implements Serializable {

    public String mcc;
    public String mnc;
    public String lac;
    public String cid;
    public String rssi;
    public String latitude;//电信经度
    public String longitude;//电信纬度
    public boolean flag;//电信区分

}

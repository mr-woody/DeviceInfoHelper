package com.woodys.devicelib.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.woodys.devicelib.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.woodys.devicelib.DeviceUtil.noSameName;

/**
 * Created by lisiwei on 2018/2/12.
 */

public class LocationGetter extends SetterAndGetter implements LocationConstants {
    private  TelephonyManager tm;
    private  WifiManager wm;

    public  LocationGetter(Context context){
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public  JSONObject getCellLocation(Context context) throws JSONException {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        CellLocation cl = tm.getCellLocation();
        JSONObject obj = new JSONObject();
        if (null != cl) {
            if (cl instanceof CdmaCellLocation) {
                CdmaCellLocation ccl = (CdmaCellLocation) cl;
                obj.put(CL_TYPE, CL_CDMACELLLOCATION);
                obj.put(CL_BASESTATIONID, ccl.getBaseStationId());
                obj.put(CL_BASESTATIONLATITUDE, ccl.getBaseStationLatitude());
                obj.put(CL_BASESTATIONLONGTITUDE, ccl.getBaseStationLongitude());
                obj.put(CL_NETWORKID, ccl.getNetworkId());
                obj.put(CL_SYSTEMID, ccl.getSystemId());

            } else if (cl instanceof GsmCellLocation) {
                GsmCellLocation gcl = (GsmCellLocation) cl;
                obj.put(CL_TYPE, CL_GSMCELLLOCATION);
                obj.put(CL_GSMCID, gcl.getCid());
                obj.put(CL_GSMLAC, gcl.getLac());
                obj.put(CL_GSMPSC, gcl.getPsc());
            }
        }
        return obj;
    }

    public  JSONArray getNeighboringCellInfo(Context context) throws JSONException {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<NeighboringCellInfo> nciList = tm.getNeighboringCellInfo();
        JSONArray array = new JSONArray();
        if (null != nciList && nciList.size() > 0) {
            for (NeighboringCellInfo nci : nciList) {
                JSONObject obj = new JSONObject();
                Log.i("RRX", "CID: " + nci.getCid());
                obj.put(NCI_CID, nci.getCid());
                Log.i("RRX", "LAC: " + nci.getLac());
                obj.put(NCI_LAC, nci.getLac());
                Log.i("RRX", "NETWORKTYPE: " + nci.getNetworkType());
                obj.put(NCI_NETWORKTYPE, nci.getNetworkType());
                Log.i("RRX", "PSC: " + nci.getPsc());
                obj.put(NCI_PSC, nci.getPsc());
                Log.i("RRX", "RSSI: " + nci.getRssi());
                obj.put(NCI_RSSI, nci.getRssi());
                array.put(obj);
            }
        }
        return array;
    }

    public   JSONArray getAllCellInfo(Context context) throws JSONException {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<CellInfo> ciList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ciList = tm.getAllCellInfo();
        }
        JSONArray array = new JSONArray();
        Gson gson = new Gson();
        Log.i("RRX", "getAllCellInfo");
        if(null != ciList && ciList.size() > 0) {
            for(CellInfo ci : ciList) {
                if(null != ci){
                    String json = gson.toJson(ci);
                    JSONObject item = new JSONObject(json);
                    array.put(item);
                }
            }
        }
        return array;
    }

    public   JSONObject getWifiConnectionInfo() throws NoSuchFieldException, IllegalAccessException, JSONException {
        WifiInfo ci = wm.getConnectionInfo();
        JSONObject obj = new JSONObject();
        obj.put(CI_BSSID, ci.getBSSID());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            obj.put(CI_Frequency, ci.getFrequency());
        }
        obj.put(CI_HiddenSSID, ci.getHiddenSSID());
        obj.put(CI_IpAddress, ci.getIpAddress());
        obj.put(CI_LinkSpeed, ci.getLinkSpeed());
        obj.put(CI_MacAddress, ci.getMacAddress());
        obj.put(CI_NetworkId, ci.getNetworkId());
        obj.put(CI_Rssi, ci.getRssi());
        obj.put(CI_SSID, ci.getSSID());
        obj.put(CI_SupplicantState, ci.getSupplicantState());
        obj.put(CI_txBad, getFieldValueByNameLong(ci, "txBad"));
        obj.put(CI_txRetries, getFieldValueByNameLong(ci, "txRetries"));
        obj.put(CI_txSuccess, getFieldValueByNameLong(ci, "txSuccess"));
        obj.put(CI_rxSuccess, getFieldValueByNameLong(ci, "rxSuccess"));
        obj.put(CI_txBadRate, getFieldValueByNameDouble(ci, "txBadRate"));
        obj.put(CI_txRetriesRate, getFieldValueByNameDouble(ci, "txRetriesRate"));
        obj.put(CI_txSuccessRate, getFieldValueByNameDouble(ci, "txSuccessRate"));
        obj.put(CI_rxSuccessRate, getFieldValueByNameDouble(ci, "rxSuccessRate"));
        obj.put(CI_badRssiCount, getFieldValueByNameInt(ci, "badRssiCount"));
        obj.put(CI_linkStuckCount, getFieldValueByNameInt(ci, "linkStuckCount"));
        obj.put(CI_lowRssiCount, getFieldValueByNameInt(ci, "lowRssiCount"));
        obj.put(CI_score, getFieldValueByNameInt(ci, "score"));
        return obj;
    }

    public  JSONArray getScanResults() throws NoSuchFieldException, IllegalAccessException, JSONException {
        List<ScanResult> srList = noSameName(wm.getScanResults());
        JSONArray array = new JSONArray();
        if(null != srList && srList.size() > 0) {
            for(ScanResult sr : srList) {
                JSONObject obj = new JSONObject();
                //wifiSsid = source.wifiSsid; parcelable TBD
                obj.put(SR_BSSID, sr.BSSID);

                obj.put(SR_SSID, getFieldValueByNameString(sr, SR_SSID));
                obj.put(SR_hessid, getFieldValueByNameLong(sr, SR_hessid));
                obj.put(SR_anqpDomainId, getFieldValueByNameInt(sr, SR_anqpDomainId));

                //obj.put(SR_informationElements, getFieldValueByNameString(sr, SR_informationElements));
                //obj.put(SR_anqpElements, getFieldValueByNameString(sr, SR_anqpElements));
                obj.put(SR_capabilities, sr.capabilities);
                obj.put(SR_level, sr.level);
                obj.put(SR_frequency, sr.frequency);
                obj.put(SR_channelWidth, getFieldValueByNameInt(sr, SR_channelWidth));
                obj.put(SR_centerFreq0, getFieldValueByNameInt(sr, SR_centerFreq0));
                obj.put(SR_centerFreq1, getFieldValueByNameInt(sr, SR_centerFreq1));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    obj.put(SR_timestamp, sr.timestamp);
                }

                obj.put(SR_distanceCm, getFieldValueByNameInt(sr, SR_distanceCm));
                obj.put(SR_distanceSdCm, getFieldValueByNameInt(sr, SR_distanceSdCm));
                obj.put(SR_seen, getFieldValueByNameLong(sr, SR_seen));
                obj.put(SR_untrusted, getFieldValueByNameBoolean(sr, SR_untrusted));
                obj.put(SR_numConnection, getFieldValueByNameInt(sr, SR_numConnection));
                obj.put(SR_numUsage, getFieldValueByNameInt(sr, SR_numUsage));
                obj.put(SR_numIpConfigFailures, getFieldValueByNameInt(sr, SR_numIpConfigFailures));
                obj.put(SR_isAutoJoinCandidate, getFieldValueByNameInt(sr, SR_isAutoJoinCandidate));
                obj.put(SR_flags, getFieldValueByNameLong(sr, SR_flags));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    obj.put(SR_venueName, sr.venueName.toString());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    obj.put(SR_operatorFriendlyName, sr.operatorFriendlyName.toString());
                }
                array.put(obj);
            }
        }
        return array;
    }
}

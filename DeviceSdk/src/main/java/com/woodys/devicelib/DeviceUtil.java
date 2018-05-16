package com.woodys.devicelib;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;

import com.woodys.devicelib.gson.Gson;
import com.woodys.devicelib.location.LocationGetter;
import com.woodys.devicelib.model.AppInfo;
import com.woodys.devicelib.model.AudioManagerInfo;
import com.woodys.devicelib.model.BaseStationInfo;
import com.woodys.devicelib.model.ConnectivityManagerInfo;
import com.woodys.devicelib.model.DeviceInfo;
import com.woodys.devicelib.model.GPSInfo;
import com.woodys.devicelib.model.LocationInfo;
import com.woodys.devicelib.model.NeighboringStationInfo;
import com.woodys.devicelib.model.OsWifiInfo;
import com.woodys.devicelib.model.PackageManagerInfo;
import com.woodys.devicelib.model.SensorInfo;
import com.woodys.devicelib.model.TelephonyManagerInfo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;


/**
 * Created by renrenxin on 16/12/10.
 */
public class DeviceUtil {

    private static final String ALIPAY_PACKAGENAME = "com.eg.android.AlipayGphone";
    /**
     * BRAND
     *
     * @return
     */
    private static String getBRAND() {
        return Build.BRAND.toString();
    }


    /**
     * @return
     */
    private static String getCPUABI() {
        return Build.CPU_ABI;
    }

    private static String getIncremental() {
        return Build.VERSION.INCREMENTAL;
    }


    /**
     * @return
     */
    private static String getCPUCount() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return "" + files.length;
        } catch (Exception e) {
            e.getMessage();
            return "" + 1;
        }

    }

    /**
     * @return
     */
    private static String getCPUHardware() {
        return Build.HARDWARE;
    }

    /**
     * @return
     */
    private static String getCPUSerial() {
        return Build.SERIAL;
    }

    /**
     * @return
     */
    private static String getCPUSpeed() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.getMessage();
            result = "N/A";
        }
        return result.trim();
    }

    /**
     * @return
     */
    private static String getIMSI(Context context) {

        TelephonyManager tm = getSystemService(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   private void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        if (tm.getSubscriberId() == null) {
            return "";
        } else {

            return tm.getSubscriberId();
        }

    }

    /**
     * @return
     */
    private static String getModel() {

        return Build.MODEL;
    }

    /**
     * @return
     */

    private static int getNetworkType(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return networkInfo.getType();
        }

        return 0;
    }

    private static int getMock_location(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "mock_location", 0);

    }

    private static int getAdbEnable(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0);
        }
        return 0;

    }

    private static int getAirplane_mode_on(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "adb_enabled", 0);

    }

    private static String getBluetooth_address(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");

    }

    /**
     * @return
     */
    private static int getPhoneType(Context context) {
        TelephonyManager tm = getSystemService(context);

        return tm.getPhoneType();
    }

    /**
     * @return
     */
    private static String getResolution(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        if (screenWidth > screenHeight) {
            return "" + screenHeight + "*" + screenWidth;
        } else {
            return "" + screenWidth + "*" + screenHeight;
        }
    }

    /**
     * @return
     */
    private static String getSim(Context context) {
        TelephonyManager tm = getSystemService(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   private void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        if (tm.getSimSerialNumber() == null) {
            return "";
        }
        return tm.getSimSerialNumber();
    }

    /**
     * 获取设备id
     *
     * @param context
     * @return
     */
    private static String getDeviceId(Context context) {
        TelephonyManager tm = getSystemService(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   private void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return tm == null ? "" : tm.getDeviceId();
        //return "1234343";
    }

    private static String getDeviceId2(Context context) {
        String version = "";
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("get", new Class[]{String.class, String.class});
            Object result = method.invoke(object, new Object[]{"gsm.version.baseband", "no message"});
            version = (String) result;
        } catch (Exception e) {
        }
        return version;

    }

    private static TelephonyManager getSystemService(Context context) {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    private static int getTouchScreen(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        return configurationInfo.reqTouchScreen;
    }

    private static String getTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        return tz.getDisplayName(false, TimeZone.SHORT);
    }

    private static String getLanguage() {

        String language = Locale.getDefault().getLanguage();
        return language;
    }


    public static JSONObject getDeviceInfo(Context context) {
        DeviceInfo info = new DeviceInfo();
        info.setBrand(getBRAND());

        info.setCpuABI(getCPUABI());
        info.setCpuCount(getCPUCount());
        info.setCpuHardware(getCPUHardware());
        info.setCpuSerial(getCPUSerial());
        info.setCpuSpeed(getCPUSpeed());

        info.setModel(getModel());
        info.setNetworkType(getNetworkType(context));
        info.setPhoneType(getPhoneType(context));
        info.setResolution(getResolution(context));

        info.setTotalMemory(getTotalRam(context));
        info.setTotalStorage(getRomTotalSize(context));

        info.setTouchScreen(getTouchScreen(context));

        info.setTimeZone(getTimeZone());
        info.setLanguage(getLanguage());
        info.setCountry(getCountry(context));
        info.setOsName(getSystemName());
        //二期添加字段
        info.setCpuArchitecture(getCpuName());
        info.setCpuMaxFreq(getMaxCpuFreq());
        info.setCpuMinFreq(getMinCpuFreq());
        info.setCpuCurFreq(getCurCpuFreq());
        info.setCpuABI2(getCPUABI2());
        info.setBluetoothMac(getBluetoothMAC());
        info.setTotalsys(getSystemTotalSize(context));
        info.setAvaisys(getSystemFreeSpaceSize(context));
        info.setFreeSDStorage(getSDFreeSpaceSize(context));
        info.setTotalSDStorage(getTotalSDStorage(context));
        info.setSimState(getSimState(context));

        info.setBaseBandVersion(getBaseBandVersion());
        info.setBoard(getBoard());
        info.setBootLoader(getBootloader());
        info.setDevice(getBuildDevice());
        info.setManufacturer(getManufacturer());
        info.setDisplay(getDisplay());
        info.setFingerPrint(getFingerprint());
        info.setHost(getBuidHost());
        info.setId(getBuildID());
        info.setUser(getBuildUser());
        info.setProduct(getProduct());
        info.setScreenDensity(getScreenDensity(context));
        info.setBrightness(getSystemBrightness(context));
        info.setRooted(isRoot());
        info.setHasCellular(getHasGSM(context));
        info.setHasCellular1(getHasCDMA(context));
        info.setHasWiFi(getHasFeatureWifi(context));
        info.setHasGps(getHasFeatureLocationGPS(context));
        info.setHasTelephony(getHasTelephony(context));
        info.setHasNFC(getHasFeatureNFC(context));
        info.setHasNFCHost(getHasFeatureNFCHost(context));
        info.setHasBluetooth(getHasFeatureBluetooth(context));
        info.setHasWiFiDirect(getHasFeatureWifiDirect(context));
        info.setHasOTG(getHasUSB(context));
        info.setHasAOA(getHasUSBAccessory(context));
        info.setOsVersion(getVersion());
        info.setOsVersionInt(getVersionInt());
        info.setCodeName(getCodeName());
        info.setSystemUpTime(getStartuptime());
        info.setActivetime(getActivetime());
        info.setTags(getTags());
        info.setType(getType());
        info.setUserAgent(getUserAgent(context));
        info.setAndroidId(getAndroidId(context));
        info.setMemoryUsed(getMemoryUsed());
        BaseStationInfo baseStation = getBaseStation(context);
        if (baseStation != null) {
            info.setSimMcc(baseStation.mcc);
            info.setSimMnc(baseStation.mnc);
            info.setSimLac(baseStation.lac);
            info.setSimCid(baseStation.cid);
            if (baseStation.flag) {

                info.setBaseStationLongitude(baseStation.longitude);
                info.setBaseStationLatitude(baseStation.latitude);
            }

        }

        GPSInfo gps = getGPS(context);
        if (gps != null) {
            info.setLongitude(gps.longitude);
            info.setLatitude(gps.latitude);
        }

        info.setDeviceId(getDeviceId(context));
        info.setImsi(getIMSI(context));
        info.setSim(getSim(context));
        info.setWifiList(getWifiList(context));
        info.setWifiDNS(getDns(context));
        info.setWifiGateway(getWifiGateway(context));
        info.setWifiNetmask(getWifiNetmask(context));
        info.setWifiRssi(getConnectedWifiRssi(context));
        info.setWifiSSID(getConnectedWifiSSID(context));
        info.setWifiBSSID(getConnectedWifiBSSID(context));

        Map<String,String> ipAddress = getIpAddress(context);
        info.setIp(ipAddress.get("local_ip"));
        info.setWifiIP(ipAddress.get("wifi_ip"));

        info.setSimName(getSimName(context));
        info.setSensorList(getSensor(context));
        info.setIncremental(getIncremental());
        //支付宝采集的全局变量
        info.setSupported_abis(getSUPPORTED_ABIS());
        info.setSupported_32_bit_abis(getSUPPORTED_32_BIT_ABIS());
        info.setSupported_64_bit_abis(getSUPPORTED_64_BIT_ABIS());
        info.setMock_location(getMock_location(context));
        info.setAdb_enabled(getAdbEnable(context));
        info.setAirplane_mode_on(getAirplane_mode_on(context));
        info.setBluetooth_address(getBluetooth_address(context));
        info.setDataPath(getDataPath());
        info.setRo_kernel_qemu(getSystemProperties("ro.kernel.qemu"));
        info.setGsm_sim_state(getSystemProperties("gsm.sim.state"));
        info.setGsm_sim_state_2(getSystemProperties("gsm.sim.state.2"));
        info.setWifi_interface(getSystemProperties("wifi.interface"));
        info.setSys_usb_state(getSystemProperties("rsys.usb.state"));
        info.setGsm_version_baseband(getSystemProperties("gsm.version.baseband"));


        PackageManager pm = context.getPackageManager();
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        //audioManager
        AudioManagerInfo audioManager = new AudioManagerInfo();
        audioManager.streamVolume0=getStreamVolume0(am);
        audioManager.streamVolume1=getStreamVolume1(am);
        audioManager.streamVolume2=getStreamVolume2(am);
        audioManager.streamVolume3=getStreamVolume3(am);
        audioManager.streamVolume4=getStreamVolume4(am);
        info.audioManager = audioManager;

        //connectivityManager
        ConnectivityManagerInfo connectivityManager = new ConnectivityManagerInfo();
        connectivityManager.ExtraInfo=getConnExtraInfo(cm);
        connectivityManager.Type=getConnType(cm);
        connectivityManager.TypeName=getConnTypeName(cm);
        connectivityManager.isConnected=isConnected(cm);
        info.connectivityManager = connectivityManager;

        //location
        LocationInfo location= new LocationInfo();
        location.Latitude=getLatitude(lm, context);
        location.Longitude=getLongitude(lm, context);
        info.location = location;

        //packageManager
        PackageManagerInfo packageManager = new PackageManagerInfo();
        try {
            packageManager.versionName=getVersionName(pm, context);
            info.packageManager = packageManager;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //telephonyManager
        TelephonyManagerInfo telephonyManager = new TelephonyManagerInfo();
        telephonyManager.DeviceId=getDeviceId(context);
        telephonyManager.Line1Number=getLine1Number(tm, context);
        telephonyManager.NetworkOperator=getNetworkOperator(tm);
        telephonyManager.NetworkOperatorName=getNetworkOperatorName(tm);
        telephonyManager.NetworkType=getTelNetworkType(tm);
        telephonyManager.SimOperatorName=getSimOperatorName(tm);
        telephonyManager.SimSerialNumber=getSimSerialNumber(tm, context);
        telephonyManager.SubscriberId=getSubscriberId(tm, context);
        info.telephonyManager = telephonyManager;


        info.setAllApps(getPackagesList(context));
        JSONObject jsonObject = null;
        //位置和wifi相关
        try {
            jsonObject = new JSONObject(new Gson().toJson(info));
            LocationGetter locationGetter = new LocationGetter(context);
            jsonObject.put("cellLocation", locationGetter.getCellLocation(context));
            jsonObject.put("neighboringcellinfo", locationGetter.getNeighboringCellInfo(context));
            jsonObject.put("allCellInfo", locationGetter.getAllCellInfo(context));
            jsonObject.put("wifiConnectionInfo", locationGetter.getWifiConnectionInfo());
            jsonObject.put("scanResults", locationGetter.getScanResults());

        } catch (Exception e) {
            e.getMessage();
        }
        return jsonObject;

    }
    private static String getSubscriberId(TelephonyManager tm, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return tm.getSubscriberId();
    }

    private static String getSimSerialNumber(TelephonyManager tm, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return tm.getSimSerialNumber();
    }

    private static String getSimOperatorName(TelephonyManager tm) {
        return tm.getSimOperatorName();
    }

    private static String getNetworkOperatorName(TelephonyManager tm) {
        return tm.getNetworkOperatorName();
    }

    private static String getNetworkOperator(TelephonyManager tm) {
        return tm.getNetworkOperator();
    }

    private static String getLine1Number(TelephonyManager tm, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return tm.getLine1Number();
    }


    private static int getTelNetworkType(TelephonyManager tm) {
        return tm.getNetworkType();
    }

    private static String getVersionName(PackageManager pm, Context context) throws PackageManager.NameNotFoundException {
        String versionName = null;
        PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGENAME, 16);
        if(null != info){
            versionName = info.versionName;
        }
        return versionName;
    }

    private static List<String[]> getPkgInfoList(PackageManager pm) {
        List<String[]> pkgInfoList = new ArrayList<String[]>();
        List<PackageInfo> pkgList =  pm.getInstalledPackages(64);
        if(null != pkgList && pkgList.size() > 0){
            for (PackageInfo info : pkgList){
                Signature[] sigArray = info.signatures;
                if(null != sigArray && sigArray.length > 0 && null != info.packageName) {
                    String[] arr = new String[sigArray.length + 1];
                    arr[0] = info.packageName;
                    for(int i=1; i<=sigArray.length; i++){
                        arr[i] = sigArray[i-1].toCharsString();
                    }
                    pkgInfoList.add(arr);
                }
            }
        }
        return  pkgInfoList;
    }


    private static double getLongitude(LocationManager lm, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return -1;
        }
        Location loc = lm.getLastKnownLocation("network");
        if(null != loc){
            return loc.getLongitude();
        }
        return 0;
    }

    private static double getLatitude(LocationManager lm, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return -1;
        }
        Location loc = lm.getLastKnownLocation("network");
        if(null != loc){
            return loc.getLatitude();
        }
        return 0;
    }


    private static String getConnTypeName(ConnectivityManager cm) {
        return cm.getActiveNetworkInfo().getTypeName();
    }

    private static int getConnType(ConnectivityManager cm) {
        return cm.getActiveNetworkInfo().getType();
    }

    private static String getConnExtraInfo(ConnectivityManager cm) {
        return cm.getActiveNetworkInfo().getExtraInfo();
    }

    private static boolean isConnected(ConnectivityManager cm) {
        return cm.getActiveNetworkInfo().isConnected();
    }


    private static int getStreamVolume0(AudioManager am) {
        return am.getStreamVolume(0);
    }
    private static int getStreamVolume1(AudioManager am) {
        return am.getStreamVolume(1);
    }
    private static int getStreamVolume2(AudioManager am) {
        return am.getStreamVolume(2);
    }
    private static int getStreamVolume3(AudioManager am) {
        return am.getStreamVolume(3);
    }
    private static int getStreamVolume4(AudioManager am) {
        return am.getStreamVolume(4);
    }


    private static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId == null ? "" : androidId;
    }

    private static String getUserAgent(Context context) {
        String useragent = "";
        if (Build.VERSION.SDK_INT >= 17) {
            useragent = WebSettings.getDefaultUserAgent(context);
        }
        return useragent;
    }

    private static String getSystemName() {
        return Build.VERSION.RELEASE;
    }

    private static String getCountry(Context context) {

        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getSimCountryIso();
    }


    /**
     * 获得SD卡总大小
     *
     * @return
     */
    private static String getTotalSDStorage(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    private static String getRomTotalSize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long size = blockSize * totalBlocks / 1024 / 1024 / 1024;
        if (size <= 16) {
            return "16 GB";
        } else if (size <= 32) {
            return "32 GB";
        } else if (size <= 64) {
            return "64 GB";
        } else if (size <= 128) {
            return "128 GB";
        } else if (size <= 256) {
            return "256 GB";
        } else {
            return Formatter.formatFileSize(context, blockSize * totalBlocks);
        }

    }


    /**
     * 获取设备的蓝牙Mac地址
     *
     * @return
     */

    private static String getBluetoothMAC() {
        BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();
        String btMac = null;
        if (bAdapt != null) {
            if (!bAdapt.isEnabled()) {
                btMac = "Bluetooth Device is not opening";
//                Intent enBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enBT, 3);
            } else {
                btMac = bAdapt.getAddress();
            }
        }

        return btMac;
    }

    /**
     * BOARD
     *
     * @return
     */
    private static String getBoard() {
        return Build.BOARD.toString();
    }


    private static String[] getSUPPORTED_ABIS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS;
        }
        return null;

    }

    private static String[] getSUPPORTED_32_BIT_ABIS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_32_BIT_ABIS;
        }
        return null;

    }

    private static String[] getSUPPORTED_64_BIT_ABIS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_64_BIT_ABIS;
        }
        return null;

    }

    /**
     * MANUFACTURER
     *
     * @return
     */
    private static String getManufacturer() {
        return Build.MANUFACTURER.toString();
    }

    /**
     * PRODUCT
     *
     * @return
     */
    private static String getProduct() {
        return Build.PRODUCT.toString();
    }

    /**
     * DISPLAY
     *
     * @return
     */
    private static String getDisplay() {
        return Build.DISPLAY.toString();
    }

    /**
     * Version
     *
     * @return
     */
    private static String getVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * VersionInt
     *
     * @return
     */
    private static String getVersionInt() {
        return "" + Build.VERSION.SDK_INT;
    }

    /**
     * Build.ID
     *
     * @return
     */
    private static String getBuildID() {
        return Build.ID;
    }

    /**
     * Build.USER
     *
     * @return
     */
    private static String getBuildUser() {
        return Build.USER;
    }

    /**
     * Build.USER
     *
     * @return
     */
    private static String getBuildDevice() {
        return Build.DEVICE;
    }

    /**
     * SimState SIM 卡状态
     *
     * @return
     */
    private static String getSimState(Context context) {
        TelephonyManager tm = getSystemService(context);
        return "" + tm.getSimState();
    }

    /**
     * 是否支持2G移动网络
     *
     * @return
     */
    private static boolean getHasGSM(Context context) {
        PackageManager pm = context.getPackageManager();
        // 获取是否支持移动网络
        boolean gsm = pm
                .hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);
        return gsm;
    }

    /**
     * 是否支持3G移动网络
     *
     * @return
     */
    private static boolean getHasCDMA(Context context) {
        PackageManager pm = context.getPackageManager();
        // 获取是否支持移动网络
        boolean cdma = pm
                .hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
        return cdma;
    }

    /**
     * 是否支持电话功能
     *
     * @return
     */
    private static boolean getHasTelephony(Context context) {
        PackageManager pm = context.getPackageManager();
        // 获取是否支持电话
        boolean telephony = pm
                .hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        return telephony;
    }

    /**
     * 是否有 gps 模块
     *
     * @return
     */
    private static boolean getHasFeatureLocationGPS(Context context) {
        PackageManager pm = context.getPackageManager();
        // 是否支持GPS
        boolean gps = pm
                .hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
        return gps;
    }

    /**
     * 是否有 WIFI 模块
     *
     * @return
     */
    private static boolean getHasFeatureWifi(Context context) {
        PackageManager pm = context.getPackageManager();
        // 是否支持WIFI
        boolean wifi = pm
                .hasSystemFeature(PackageManager.FEATURE_WIFI);
        return wifi;
    }


    private static Map<String,String> getIpAddress(Context context) {
        Map<String,String> ipMap = new HashMap<String,String>();
        ConnectivityManager conMann = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mobileNetworkInfo.isConnected()) {
            //本地ip
            ipMap.put("local_ip",getLocalIpAddress());
        }else if(wifiNetworkInfo.isConnected()) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            //wifi_ip地址
            ipMap.put("wifi_ip",intToIp(ipAddress));
        }
        return ipMap;
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    private static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface ni = en.nextElement();
                Enumeration<InetAddress> enIp = ni.getInetAddresses();
                while (enIp.hasMoreElements()) {
                    InetAddress inet = enIp.nextElement();
                    if (!inet.isLoopbackAddress()
                            && (inet instanceof Inet4Address)) {
                        return inet.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return "0";
    }

    /**
     * 获取WI-FI ip地址
     * @param ipInt
     * @return
     */
    private static String intToIp(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 是否支持 NFC
     *
     * @return
     */
    private static boolean getHasFeatureNFC(Context context) {
        PackageManager pm = context.getPackageManager();
        // 是否支持NFC
        boolean nfc = pm
                .hasSystemFeature(PackageManager.FEATURE_NFC);
        return nfc;
    }

    /**
     * 是否支持 NFC 主机
     *
     * @return
     */
    private static boolean getHasFeatureNFCHost(Context context) {
        PackageManager pm = context.getPackageManager();
        // 是否支持NFCHost
        boolean nfc = pm
                .hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
        return nfc;
    }

    /**
     * 是否有 BLUETOOTH 模块
     *
     * @return
     */
    private static boolean getHasFeatureBluetooth(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean bluetooth = pm
                .hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
        return bluetooth;
    }

    /**
     * 是否支持 WIFI 直连接
     *
     * @return
     */
    private static boolean getHasFeatureWifiDirect(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean wifiDirect = pm
                .hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT);
        return wifiDirect;
    }

    /**
     * 是否支持 usb
     *
     * @return
     */
    private static boolean getHasUSB(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean usb = pm
                .hasSystemFeature(PackageManager.FEATURE_USB_HOST);
        return usb;
    }

    /**
     * 是否支持 USB Accessory
     *
     * @return
     */
    private static boolean getHasUSBAccessory(Context context) {
        PackageManager pm = context.getPackageManager();
        boolean usb = pm
                .hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY);
        return usb;
    }

    /**
     * 系统上次开机时间,long
     *
     * @return
     */
    private static long getStartuptime() {
        return SystemClock.elapsedRealtime();
    }

    /**
     * 系统自上次开机的活跃时间
     *
     * @return
     */
    private static long getActivetime() {
        return SystemClock.uptimeMillis();
    }

    /**
     * root标识
     *
     * @return
     */
    private static boolean isRoot() {
        boolean bool = false;

        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
            Log.d("isRoot", "bool = " + bool);
        } catch (Exception e) {

        }
        return bool;
    }

    /**
     * 屏幕亮度
     *
     * @param context
     * @return
     */
    private static String getSystemBrightness(Context context) {
        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.getMessage();
        }
        return "" + systemBrightness;
    }

    /**
     * 获得所有类型的Sensor，并装进一个列表里
     *
     * @param context
     * @return
     */
    private static List<SensorInfo> getSensor(Context context) {
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //获得所有类型的Sensor，并装进一个列表里
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
        List<SensorInfo> list = new ArrayList<SensorInfo>();
        //遍历Sensor列表
        for (Sensor s : allSensors) {
            //显示Sensor信息
            SensorInfo info = new SensorInfo();
            info.name = s.getName();
            info.type = "" + s.getType();
            info.version = "" + s.getVersion();
            info.vendor = s.getVendor();
            list.add(info);
        }
        return list;
    }


    private static List<String> getPackagesList(Context context) {
        List<String> appList=new ArrayList<String>();
        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if ((packageInfo.applicationInfo.flags& ApplicationInfo.FLAG_SYSTEM) == 0) { //非系统应用
                // AppInfo 自定义类，包含应用信息
                AppInfo appInfo = new AppInfo();
                appInfo.appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();//获取应用名称
                appInfo.packageName = packageInfo.packageName; //获取应用包名，可用于卸载和启动应用
                appInfo.versionName = packageInfo.versionName;//获取应用版本名
                appInfo.versionCode = packageInfo.versionCode;//获取应用版本号
                appInfo.appIcon= packageInfo.applicationInfo.loadIcon(context.getPackageManager());//获取应用图标
                appList.add(appInfo.appName);
            } else { // 系统应用

            }
        }
        return appList;
    }


    /**
     * cpu 指令集
     *
     * @return
     */
    private static String getCPUABI2() {
        return Build.CPU_ABI2;
    }

    /**
     * 硬件识别码(设备指纹)
     *
     * @return
     */
    private static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    /**
     * 系统启动程序版本号
     *
     * @return
     */
    private static String getBootloader() {
        return Build.BOOTLOADER;
    }

    /**
     * Build类中的host
     *
     * @return
     */
    private static String getBuidHost() {
        return Build.HOST;
    }

    /**
     * 屏幕密度
     *
     * @return
     */
    private static String getScreenDensity(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return "" + density;
    }

    /**
     * 获得基带版本
     *
     * @return String
     */
    private static String getBaseBandVersion() {
        String version = "";
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("get", new Class[]{String.class, String.class});
            Object result = method.invoke(object, new Object[]{"gsm.version.baseband", "no message"});
            version = (String) result;
        } catch (Exception e) {
        }
        return version;
    }

    private static String getSystemProperties(String propertiesKey) {

        String value = "";
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("get", new Class[]{String.class, String.class});
            Object result = method.invoke(object, new Object[]{propertiesKey, ""});
            value = (String) result;
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * Build 的标签 TAGS
     *
     * @return String
     */
    private static String getTags() {

        return Build.TAGS;
    }

    /**
     * Build 的标签 TYPE
     *
     * @return String
     */
    private static String getType() {

        return Build.TYPE;
    }

    /**
     * Build 的标签 CodeName
     *
     * @return String
     */
    private static String getCodeName() {

        return Build.VERSION.CODENAME;
    }

    /**
     * Build 的标签 CodeName
     *
     * @return String
     */
    private static List<OsWifiInfo> getWifiList(Context context) {

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<OsWifiInfo> list = new ArrayList<>();
        if (wifiManager.isWifiEnabled()) {
            List<ScanResult> results = noSameName(wifiManager.getScanResults());
            if(results!=null && results.size()>0) {
                for (ScanResult result : results) {
                    OsWifiInfo wifiInfo = new OsWifiInfo();
                    wifiInfo.bssid = result.BSSID.toString();
                    wifiInfo.ssid = result.SSID.toString();
                    wifiInfo.capabilities = result.capabilities.toString();
                    wifiInfo.level = String.valueOf(Math.abs(result.level));
                    list.add(wifiInfo);
                }
            }
        }
        return list;
    }


    /**
     * 去除同名WIFI
     *
     * @param oldSr 需要去除同名的列表
     * @return 返回不包含同命的列表
     */
    public static List<ScanResult> noSameName(List<ScanResult> oldSr)
    {
        List<ScanResult> newSr = new ArrayList<ScanResult>();
        for (ScanResult result : oldSr)
        {
            if (!TextUtils.isEmpty(result.SSID) && !containName(newSr, result.SSID))
                newSr.add(result);
        }
        return newSr;
    }
    /**
     * 判断一个扫描结果中，是否包含了某个名称的WIFI
     * @param sr 扫描结果
     * @param name 要查询的名称
     * @return 返回true表示包含了该名称的WIFI，返回false表示不包含
     */
    public static boolean containName(List<ScanResult> sr, String name)
    {
        for (ScanResult result : sr)
        {
            if (!TextUtils.isEmpty(result.SSID) && result.SSID.equals(name))
                return true;
        }
        return false;
    }

    private static GPSInfo getGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Location location = null;
        String locationProvider = null;
        List<String> providers = locationManager.getProviders(true);
        locationProvider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        location = locationManager.getLastKnownLocation(locationProvider);
        GPSInfo gpsBean = new GPSInfo();
        if (location != null) {


            gpsBean.latitude = location.getLatitude() + "";
            gpsBean.longitude = location.getLongitude() + "";

        }

        if (location == null && TextUtils.isEmpty(gpsBean.latitude)) {
            //如果用GPS获取，经纬度没获取到
            //如果是GPS
            locationProvider = LocationManager.NETWORK_PROVIDER;
            location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {


                gpsBean.latitude = location.getLatitude() + "";
                gpsBean.longitude = location.getLongitude() + "";
            }
        }
        if (location == null && TextUtils.isEmpty(gpsBean.latitude)) {
            Criteria criteria = new Criteria();
            criteria.setAltitudeRequired(false);//无海拔要求
            criteria.setBearingRequired(false);//无方位要求
            locationProvider = locationManager.getBestProvider(criteria, true);
            location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {


                gpsBean.latitude = location.getLatitude() + "";
                gpsBean.longitude = location.getLongitude() + "";

            }
        }
        return gpsBean;
    }

    private static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * CPU 最大频率
     *
     * @return
     */
    private static String getMaxCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.getMessage();
            result = "N/A";
        }
        return result.trim();
    }

    /**
     * CPU 最小频率
     *
     * @return
     */
    private static String getMinCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.getMessage();
            result = "N/A";
        }
        return result.trim();
    }

    /**
     * 实时获取CPU当前频率（单位KHZ）
     *
     * @return
     */
    private static String getCurCpuFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader(
                    "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim();
        } catch (IOException e) {
            e.getMessage();
        }
        return result;
    }
    private static int getConnectedWifiRssi(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
       if (info!=null){
           return info.getRssi();
       }

        return 0;

    }

    private  static String  getConnectedWifiBSSID(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info!=null){
            return info.getBSSID();
        }

        return null;
    }

    private  static  String getConnectedWifiSSID(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info!=null){
            return info.getSSID();
        }

        return null;

    }




    private  static  String getSimName(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager==null){
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }
    /**
     * 获取当前的链接的基站信息
     *
     * @return
     */
    private static BaseStationInfo getBaseStation(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return null;
        }
        String operator = telephonyManager.getNetworkOperator();
        if (operator.length() == 0) {
            return null;
        }
        String simMcc = operator.substring(0, 3);
        String simMnc = operator.substring(3, 5);
        String simLac = null;
        String simCid = null;
        String baseStationLatitude;
        String baseStationLongitude;
        BaseStationInfo baseStationInfo = new BaseStationInfo();
        if (getIMSI(context) != null) {

            if ("03".equals(simMnc) || "05".equals(simMnc) || "11".equals(simMnc)) {
                @SuppressLint("MissingPermission") CdmaCellLocation location = (CdmaCellLocation) telephonyManager.getCellLocation();
                /**通过GsmCellLocation获取中国移动和联通 LAC 和cellID */
                if (location != null) {
                    simLac = location.getNetworkId() + "";
                    simCid = location.getBaseStationId() + "";
                    baseStationLatitude = location.getBaseStationLatitude() + "";
                    baseStationLongitude = location.getBaseStationLongitude() + "";
                    baseStationInfo.flag = true;
                    baseStationInfo.latitude = baseStationLatitude;
                    baseStationInfo.longitude = baseStationLongitude;
                }
            } else {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   private void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return null;
                }
                GsmCellLocation location = (GsmCellLocation) telephonyManager.getCellLocation();
                if (location != null) {
                    simLac = location.getLac() + "";
                    simCid = location.getCid() + "";
                }

            }
        }
        baseStationInfo.lac = simLac;
        baseStationInfo.cid = simCid;
        baseStationInfo.mcc = simMcc;
        baseStationInfo.mnc = simMnc;
        return baseStationInfo;
    }

    /**
     * 获取DNS
     * @param context
     * @return
     */
    private static int  getDns(Context context){
        WifiManager my_wifiManager = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE));
        DhcpInfo dhcpInfo = my_wifiManager.getDhcpInfo();
        return  dhcpInfo.dns1;
    }

    private static int getWifiGateway(Context context){
        WifiManager my_wifiManager = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE));
        DhcpInfo dhcpInfo = my_wifiManager.getDhcpInfo();
        return  dhcpInfo.gateway;

    }

    private static int getWifiNetmask(Context context){
        WifiManager my_wifiManager = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE));
        DhcpInfo dhcpInfo = my_wifiManager.getDhcpInfo();
        return  dhcpInfo.netmask;

    }

    /**
     * 获取当前的链接的基站信息
     *
     * @return
     */
    private static List<NeighboringStationInfo> getBaseStationList(Context context) {

        List<NeighboringStationInfo> list = new ArrayList<>();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return list;
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   private void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<NeighboringCellInfo> infos = telephonyManager.getNeighboringCellInfo();
        if(infos != null &&infos.size() > 0){
            for (NeighboringCellInfo info : infos) { // 根据邻区总数进行循环
                NeighboringStationInfo stationInfo = new NeighboringStationInfo();
                stationInfo.cid =   info.getCid()+"";
                stationInfo.lac =   info.getLac()+"";
                stationInfo.rssi =   info.getRssi()+"";//强度
                list.add(stationInfo);
            }
        }
        return list;
    }

    /**
     * 获得SD卡空闲空间
     *
     * @return
     */
    private static String getSDFreeSpaceSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availCount = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, blockSize * availCount);
    }



    /**
     * 获得系统磁盘总容量
     *
     * @return
     */
    private static String getSystemTotalSize(Context context) {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalCount = stat.getBlockCount();
        return Formatter.formatFileSize(context, blockSize * totalCount);
    }

    /**
     * 获得系统磁盘可用空间
     *
     * @return
     */
    private static String getSystemFreeSpaceSize(Context context) {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());


        long blockSize = stat.getBlockSize();
        long availCount = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, blockSize * availCount);
    }

    private static String getTotalRam(Context context) {//GB
        String path = "/proc/meminfo";
        String firstLine = null;
        int totalRam = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader, 8192);

            firstLine = br.readLine().split("\\s+")[1];

            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
        if (firstLine != null) {
            totalRam = (int) Math.ceil((new Float(Float.valueOf(firstLine) / (1024 * 1024)).doubleValue()));
        }

        return totalRam + "GB";//返回1GB/2GB/3GB/4GB
    }

    private static String getMemoryUsed() {
        // 获取android当前可用内存大小
        String path = "/proc/meminfo";
        String firstLine = null;
        String senoudLine = null;
        int totalRam = 0;
        int freeRam = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader, 8192);
            String str = br.readLine();
            String[] strings = str.split("\\s+");
            str = br.readLine();
            firstLine = strings[1];
            strings = str.split("\\s+");
            senoudLine = strings[1];

            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
        if (firstLine != null) {
            totalRam = (int) Math.ceil((new Float(Float.valueOf(firstLine) / 1024).doubleValue()));
        }
        if (senoudLine != null) {
            freeRam = Integer.valueOf(senoudLine).intValue() / 1024;

        }

        return (totalRam - freeRam) + "MB";
    }

    private static String getDataPath(){
        return  Environment.getDataDirectory().getPath();
    }

}

package com.woodys.devicelib.model;

import java.util.List;

/**
 * Created by renrenxin on 16/12/10.
 */
public class DeviceInfo {
    /*
       手机品牌
     */
    private String brand;

    /*
       运营商国家
     */
    private String country;
    /*
       CPU支持的指令集
     */
    private String cpuABI;
    /*
       cpu数量
     */
    private String cpuCount;
    /*
       CPU型号
     */
    private String cpuHardware;
    /*
       CPU序列
     */
    private String cpuSerial;
    /*
       CPU速度
     */
    private String cpuSpeed;
    /*
       设备ID
     */
    private String deviceId;
    /*
       标识了GSM和UMTS
     */
    private String imsi;
    /*
       机型
     */
    private String model;
    /*
       网络类型
     */
    private int networkType;
    /*
       手机类型
     */
    private int phoneType;
    /*
       屏幕分辨率
     */
    private String resolution;

    /*
       系统总内存
     */
    private String totalMemory;
    /*
       机身总空间
     */
    private String totalStorage;
    /*
       sim卡
     */
    private String sim;

    /*
       时区
    */
    private String timeZone;
    /*
       触屏模式
    */
    private int touchScreen;

    /*
           语言
        */
    private String language;

    private String osName;

    //add v2.0 zhaoxiaolong 2017/5/22
    /*
      CPU 型号
    */
    private String androidId;
    /*
      CPU 型号
    */
    private String cpuArchitecture;

    /*
      CPU 最大频率
    */
    private String cpuMaxFreq;

    /*
      CPU 最小频率
    */
    private String cpuMinFreq;

    /*
      CPU 当前频率
    */
    private String cpuCurFreq;

    /*
      cpu 指令集
    */
    private String cpuABI2;
    /*
      当前设备的蓝牙 Mac地址
    */
    private String bluetoothMac;
    /*
      系统磁盘总容量
   */
    private String totalsys;
    /*
      系统磁盘可用空间
    */
    private String avaisys;
    /*
      SD卡可用空间
     */
    private String freeSDStorage;
    /*
      SD卡总空间
     */
    private String totalSDStorage;
    /*
      内存可用空间
     */
    private String memoryUsed;
    /*
      SIM 卡状态
    */
    private String simState;
    /*
      国家码。
    */
    private String simMcc;
    /*
      网络码。
    */
    private String simMnc;
    /*
      位置区域码
     */
    private String simLac;
    /*
       基站编号
        */
    private String simCid;

    private String simName;
    /*
       纬度（只有电信卡才有）
     */
    private String baseStationLongitude;
    /*
        经度（只有电信卡才有）
     */
    private String baseStationLatitude;

    /*
      基带信息
    */
    private String baseBandVersion;
    /*
      主板
    */
    private String board;
    /*
      系统启动程序版本号
    */
    private String bootLoader;
    /*
      设备参数
    */
    private String device;
    /*
      硬件制造商
    */
    private String manufacturer;
    /*
      显示屏参数
     */
    private String display;
    /*
      硬件识别码(设备指纹)
     */
    private String fingerPrint;
    /*
      Build类中提供的信息host
    */
    private String host;
    /*
      Build类中提供的信息host
    */
    private String user;
    /*
      Build类中提供的信息id
    */
    private String id;
    /*
      手机制造商
    */
    private String product;
    /*
      屏幕密度
    */
    private String screenDensity;
    /*
      电池状态以及电量
    */
    private String brightness;
    /*
      root标识
    */
    private boolean rooted;
    /*
      是否支持2G网络
    */
    private boolean hasCellular;
    /*
      是否支持3G网络
    */
    private boolean hasCellular1;
    /*
      是否有 WIFI 模块
    */
    private boolean hasWiFi;
    /*
      是否有 gps 模块
    */
    private boolean hasGps;
    /*
      是否支持电话功能
    */
    private boolean hasTelephony;
    /*
      是否支持 NFC
    */
    private boolean hasNFC;
    /*
      是否支持 NFC 主机
     */
    private boolean hasNFCHost;
    /*
      是否支持蓝牙
     */
    private boolean hasBluetooth;
    /*
      是否支持 WIFI 直连接
     */
    private boolean hasWiFiDirect;

    private int wifiDNS;

    private int wifiGateway;

    private int wifiNetmask;
    /*
      是否支持 usb
     */
    private boolean hasOTG;
    /*
      是否有 USB 配件
     */
    private boolean hasAOA;
    /*
        列表形式保存,模拟器信息. Obejct中的字段类型 全String
    */
    //private List<SensorInfo> sensors;
    /*
     GPS 定位经度
    */
    private String longitude;
    /*
      GPS 定位纬度
    */
    private String latitude;
    /*
      系统版本，如 5.0.2
    */
    private String osVersion;
    /*
      系统版本号码 21
    */
    private String osVersionInt;
    /*
      系统版本号码 21
    */
    private String codeName;
    /*
      系统上次开机时间
     */
    private long systemUpTime;
    /*
      系统自上次开机的活跃时间
     */
    private long activetime;
    /*
      系统上次开机时间
     */
    private String tags;
    /*
      builder 类型
     */
    private String type;
    /*
      保存当前扫描到WiFi信息, 包括SSID, BSSID和CAP即, WiFi名称,其mac地址和类型
    */
    private List<OsWifiInfo> wifiList;

    private List<SensorInfo> sensorList;


    private int wifiRssi;

    private String wifiSSID;

    private String wifiBSSID;
    /**
     * Builder信息
     */
    private String incremental;

    private String[] supported_abis;

    private String[] supported_32_bit_abis;

    private String[] supported_64_bit_abis;

    private String dataPath;


    /*
    电量百分比
    */
    private String batteryLevel;
    /*
       user agent
    */
    private String userAgent;

    private int mock_location;

    private int adb_enabled;

    private int airplane_mode_on;

    private String bluetooth_address;

    private String ro_kernel_qemu;

    private String gsm_sim_state;

    private String gsm_sim_state_2;

    private String wifi_interface;

    private String sys_usb_state;

    private String gsm_version_baseband;

    private String wifiIP;//wifiIP
    private String ip;//内网IP


    /*
     所有的APP
    */
    public List<String> allApps;


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCpuABI(String cpuABI) {
        this.cpuABI = cpuABI;
    }

    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }

    public void setCpuHardware(String cpuHardware) {
        this.cpuHardware = cpuHardware;
    }

    public void setCpuSerial(String cpuSerial) {
        this.cpuSerial = cpuSerial;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNetworkType(int networkType) {
        this.networkType = networkType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public void setTotalStorage(String totalStorage) {
        this.totalStorage = totalStorage;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setTouchScreen(int touchScreen) {
        this.touchScreen = touchScreen;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public void setCpuMaxFreq(String cpuMaxFreq) {
        this.cpuMaxFreq = cpuMaxFreq;
    }

    public void setCpuMinFreq(String cpuMinFreq) {
        this.cpuMinFreq = cpuMinFreq;
    }

    public void setCpuCurFreq(String cpuCurFreq) {
        this.cpuCurFreq = cpuCurFreq;
    }

    public void setCpuABI2(String cpuABI2) {
        this.cpuABI2 = cpuABI2;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac;
    }

    public void setTotalsys(String totalsys) {
        this.totalsys = totalsys;
    }

    public void setAvaisys(String avaisys) {
        this.avaisys = avaisys;
    }

    public void setFreeSDStorage(String freeSDStorage) {
        this.freeSDStorage = freeSDStorage;
    }

    public void setTotalSDStorage(String totalSDStorage) {
        this.totalSDStorage = totalSDStorage;
    }

    public void setMemoryUsed(String memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public void setSimState(String simState) {
        this.simState = simState;
    }

    public void setSimMcc(String simMcc) {
        this.simMcc = simMcc;
    }

    public void setSimMnc(String simMnc) {
        this.simMnc = simMnc;
    }

    public void setSimLac(String simLac) {
        this.simLac = simLac;
    }

    public void setSimCid(String simCid) {
        this.simCid = simCid;
    }

    public void setSimName(String simName) {
        this.simName = simName;
    }

    public void setBaseStationLongitude(String baseStationLongitude) {
        this.baseStationLongitude = baseStationLongitude;
    }

    public void setBaseStationLatitude(String baseStationLatitude) {
        this.baseStationLatitude = baseStationLatitude;
    }


    public void setBaseBandVersion(String baseBandVersion) {
        this.baseBandVersion = baseBandVersion;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setBootLoader(String bootLoader) {
        this.bootLoader = bootLoader;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setScreenDensity(String screenDensity) {
        this.screenDensity = screenDensity;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public void setRooted(boolean rooted) {
        this.rooted = rooted;
    }

    public void setHasCellular(boolean hasCellular) {
        this.hasCellular = hasCellular;
    }

    public void setHasCellular1(boolean hasCellular1) {
        this.hasCellular1 = hasCellular1;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    public void setHasGps(boolean hasGps) {
        this.hasGps = hasGps;
    }

    public void setHasTelephony(boolean hasTelephony) {
        this.hasTelephony = hasTelephony;
    }

    public void setHasNFC(boolean hasNFC) {
        this.hasNFC = hasNFC;
    }

    public void setHasNFCHost(boolean hasNFCHost) {
        this.hasNFCHost = hasNFCHost;
    }

    public void setHasBluetooth(boolean hasBluetooth) {
        this.hasBluetooth = hasBluetooth;
    }

    public void setHasWiFiDirect(boolean hasWiFiDirect) {
        this.hasWiFiDirect = hasWiFiDirect;
    }

    public void setWifiDNS(int wifiDNS) {
        this.wifiDNS = wifiDNS;
    }

    public void setWifiGateway(int wifiGateway) {
        this.wifiGateway = wifiGateway;
    }

    public void setWifiNetmask(int wifiNetmask) {
        this.wifiNetmask = wifiNetmask;
    }

    public void setHasOTG(boolean hasOTG) {
        this.hasOTG = hasOTG;
    }

    public void setHasAOA(boolean hasAOA) {
        this.hasAOA = hasAOA;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsVersionInt(String osVersionInt) {
        this.osVersionInt = osVersionInt;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void setSystemUpTime(long systemUpTime) {
        this.systemUpTime = systemUpTime;
    }

    public void setActivetime(long activetime) {
        this.activetime = activetime;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWifiList(List<OsWifiInfo> wifiList) {
        this.wifiList = wifiList;
    }

    public void setSensorList(List<SensorInfo> sensorList) {
        this.sensorList = sensorList;
    }


    public void setWifiRssi(int wifiRssi) {
        this.wifiRssi = wifiRssi;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public void setWifiBSSID(String wifiBSSID) {
        this.wifiBSSID = wifiBSSID;
    }

    public void setIncremental(String incremental) {
        this.incremental = incremental;
    }

    public void setSupported_abis(String[] supported_abis) {
        this.supported_abis = supported_abis;
    }

    public void setSupported_32_bit_abis(String[] supported_32_bit_abis) {
        this.supported_32_bit_abis = supported_32_bit_abis;
    }

    public void setSupported_64_bit_abis(String[] supported_64_bit_abis) {
        this.supported_64_bit_abis = supported_64_bit_abis;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setMock_location(int mock_location) {
        this.mock_location = mock_location;
    }

    public void setAdb_enabled(int adb_enabled) {
        this.adb_enabled = adb_enabled;
    }

    public void setAirplane_mode_on(int airplane_mode_on) {
        this.airplane_mode_on = airplane_mode_on;
    }

    public void setBluetooth_address(String bluetooth_address) {
        this.bluetooth_address = bluetooth_address;
    }

    public void setRo_kernel_qemu(String ro_kernel_qemu) {
        this.ro_kernel_qemu = ro_kernel_qemu;
    }

    public void setGsm_sim_state(String gsm_sim_state) {
        this.gsm_sim_state = gsm_sim_state;
    }

    public void setGsm_sim_state_2(String gsm_sim_state_2) {
        this.gsm_sim_state_2 = gsm_sim_state_2;
    }

    public void setWifi_interface(String wifi_interface) {
        this.wifi_interface = wifi_interface;
    }

    public void setSys_usb_state(String sys_usb_state) {
        this.sys_usb_state = sys_usb_state;
    }

    public void setGsm_version_baseband(String gsm_version_baseband) {
        this.gsm_version_baseband = gsm_version_baseband;
    }

    public List<String> getAllApps() {
        return allApps;
    }

    public void setAllApps(List<String> allApps) {
        this.allApps = allApps;
    }


    public String getWifiIP() {
        return wifiIP;
    }

    public void setWifiIP(String wifiIP) {
        this.wifiIP = wifiIP;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}

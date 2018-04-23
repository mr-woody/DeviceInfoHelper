package com.woodys.devicelib.location;

/**
 * Created by lisiwei on 2018/2/16.
 */

public interface LocationConstants {
    String IMEI = "IMEI";
    String IMSI = "IMSI";

    String CELLLOCATION = "CellLocation";
    String CL_TYPE = "CLTYPE";
    String CL_CDMACELLLOCATION = "CDMACELLLOCATION";
    String CL_BASESTATIONID = "BASESTATIONID";
    String CL_BASESTATIONLATITUDE = "BASESTATIONLATITUDE";
    String CL_BASESTATIONLONGTITUDE = "BASESTATIONLONGTITUDE";
    String CL_NETWORKID = "NETWORKID";
    String CL_SYSTEMID = "SYSTEMID";
    String CL_GSMCELLLOCATION = "GSMCELLLOCATION";
    String CL_GSMCID = "GSMCID";
    String CL_GSMLAC = "GSMLAC";
    String CL_GSMPSC = "GSMPSC";

    String NEIGHBORINGCELLINFO = "NeighboringCellInfo";
    String NCI_CID = "CID";
    String NCI_LAC = "LAC";
    String NCI_NETWORKTYPE = "NETWORKTYPE";
    String NCI_PSC = "PSC";
    String NCI_RSSI = "RSSI";

    String ALLCELLINFO = "AllCellInfo";
    String ACI_TimeStamp = "TimeStamp";
    String ACI_TimeStampType = "TimeStampType";
    String ACI_isRegistered = "isRegistered";

    String CONNECTIONINFO = "ConnectionInfo";
    String CI_BSSID = "BSSID";
    String CI_Frequency = "Frequency";
    String CI_HiddenSSID = "HiddenSSID";
    String CI_IpAddress = "IpAddress";
    String CI_LinkSpeed = "LinkSpeed";
    String CI_MacAddress = "MacAddress";
    String CI_NetworkId = "NetworkId";
    String CI_Rssi = "Rssi";
    String CI_SSID = "SSID";
    String CI_SupplicantState = "SupplicantState";
    String CI_txBad = "txBad";
    String CI_txRetries = "txRetries";
    String CI_txSuccess = "txSuccess";
    String CI_rxSuccess = "rxSuccess";
    String CI_txBadRate = "txBadRate";
    String CI_txRetriesRate = "txRetriesRate";
    String CI_txSuccessRate = "txSuccessRate";
    String CI_rxSuccessRate = "rxSuccessRate";
    String CI_badRssiCount = "badRssiCount";
    String CI_linkStuckCount = "linkStuckCount";
    String CI_lowRssiCount = "lowRssiCount";
    String CI_score = "score";

    String SCANRESULTS = "ScanResults";
    String SR_SSID = "SSID";
    String SR_BSSID = "BSSID";
    String SR_hessid = "hessid";
    String SR_anqpDomainId = "anqpDomainId";
    String SR_informationElements = "informationElements";
    String SR_anqpElements = "anqpElements";
    String SR_capabilities = "capabilities";
    String SR_level = "level";
    String SR_frequency = "frequency";
    String SR_channelWidth = "channelWidth";
    String SR_centerFreq0 = "centerFreq0";
    String SR_centerFreq1 = "centerFreq1";
    String SR_timestamp = "timestamp";
    String SR_distanceCm = "distanceCm";
    String SR_distanceSdCm = "distanceSdCm";
    String SR_seen = "seen";
    String SR_untrusted = "untrusted";
    String SR_numConnection = "numConnection";
    String SR_numUsage = "numUsage";
    String SR_numIpConfigFailures = "numIpConfigFailures";
    String SR_isAutoJoinCandidate = "isAutoJoinCandidate";
    String SR_venueName = "venueName";
    String SR_operatorFriendlyName = "operatorFriendlyName";
    String SR_flags = "flags";
}

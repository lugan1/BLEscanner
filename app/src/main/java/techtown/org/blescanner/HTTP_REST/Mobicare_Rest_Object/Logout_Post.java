package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

public class Logout_Post {
    long systemTime =0 ;
    String requestDateTime = "";
    String gmtCode = "";
    int deviceKind = 0;

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getGmtCode() {
        return gmtCode;
    }

    public void setGmtCode(String gmtCode) {
        this.gmtCode = gmtCode;
    }

    public int getDeviceKind() {
        return deviceKind;
    }

    public void setDeviceKind(int deviceKind) {
        this.deviceKind = deviceKind;
    }

}

package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

public class Login_Post {
    String id;
    String password;
    int encryption;
    long systemTime;
    String requestDateTime;
    String gmtCode;
    int deviceKind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEncryption() {
        return encryption;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

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

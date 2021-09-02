package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

public class Create_Account_Post {

    public long getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(long requestUserId) {
        this.requestUserId = requestUserId;
    }

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

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEncryption() {
        return encryption;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

    public int getDeactivate() {
        return deactivate;
    }

    public void setDeactivate(int deactivate) {
        this.deactivate = deactivate;
    }

    public int getDeviceKind() {
        return deviceKind;
    }

    public void setDeviceKind(int deviceKind) {
        this.deviceKind = deviceKind;
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






    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    long requestUserId;
    //요청자 userId

    String id;
    //ID

    String password;
    //비밀번호

    int userLevel;
    //0x01 환자, 0x02 리뷰어, 0x03 의사, 0x04 매니저

    long organizationId;
    //기관 ID

    String userCode;
    //회원코드

    String department;
    //부서

    String firstName;
    //이름

    String lastName;
    //성

    int gender;
    //1. 남자 , 2. 여자

    String birthday;
    //생일 ex) 1983-03-13

    String phoneNumber;
    //핸드폰 번호 ex) 010-7366-4532

    String email;
    //이메일

    String address;
    //주소

    int encryption;
    //비밀번호 암호화 AES128, 0.암호화 안함 , 1. 암호화 함

    int deviceKind;
    //1.ios 2.android, 3.web(chrome), 4.web(IE)

    int deactivate;
    //0.활성화 , 1. 비활성화

    int status;
    //0. none , 1.어카운트 락(lock)


    long systemTime;

    String requestDateTime;

    String gmtCode;




}

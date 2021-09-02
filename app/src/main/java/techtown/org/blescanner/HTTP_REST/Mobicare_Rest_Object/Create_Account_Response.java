package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

public class Create_Account_Response {
    public data data = new data();

    public Create_Account_Response() {
    }

    public data getData(){
        return data;
    }

    boolean result;
    String extra;
    long systemTime;
    int error;
    String message;

    public boolean getResult() {
        return result;
    }

    public String getExtra() {
        return extra;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }


    public String string(){
        String body = "";
        body = "\nresult : "+getResult()+
                "\nextra : "+getExtra()+
                "\nsystemTime : "+getSystemTime()+
                "\nerror : "+getError()+
                "\nmessage : "+getMessage();

        if(getData() != null){
            Create_Account_Response.data.userAccount userAccount = getData().getUserAccount();
            Create_Account_Response.data.organization organization = getData().getOrganization();
            body = body +
                    "\ndata : "+
                    "\n  userAccount {"+
                    "\n        userId : "+userAccount.getUserId()+
                    "\n        id : "+userAccount.getId()+"" +
                    "\n        password"+userAccount.getPassword()+
                    "\n        organizationId : "+userAccount.getOrganizationId()+
                    "\n        userCode : "+userAccount.getUserCode()+
                    "\n        department : "+userAccount.getDepartment()+
                    "\n        userLevel : "+userAccount.getUserLevel()+
                    "\n        firstName : "+userAccount.getFirstName()+
                    "\n        lastName : "+userAccount.getLastName()+
                    "\n        gender : "+userAccount.getGender()+
                    "\n        birthday : "+userAccount.getBirthday()+
                    "\n        phoneNumber : "+userAccount.getPhoneNumber()+
                    "\n        email : "+userAccount.getEmail()+
                    "\n        address : "+userAccount.getAddress()+
                    "\n        devicekind : "+userAccount.getDeviceKind()+
                    "\n        status : "+userAccount.getStatus()+
                    "\n        deactivate : "+userAccount.getDeactivate()+
                    "\n        gmtCode : "+userAccount.getGmtCode()+
                    "\n        joinDateTime : "+userAccount.getJoindateTime()+
                    "\n        updateDateTime : "+userAccount.getUpdateDateTime()+
                    "\n        deactivateDateTime : "+userAccount.getDeactivateDateTime()+
                    "\n  },"+
                    "\n  organization { "+
                    "\n        organizationId : "+organization.getOrganizationId()+
                    "\n        organiazationcode : "+organization.getOrganiationCode()+
                    "\n        organizationName : "+organization.getOrganizationName()+
                    "\n        countryCode : "+organization.getCountryCode()+
                    "\n        countryName : "+organization.getCountryName()+
                    "\n        state : "+organization.getState()+
                    "\n        city : "+organization.getCity()+
                    "\n        address : "+organization.getAddress()+
                    "\n        phonenumber : "+organization.getPhonenumber()+
                    "\n        contractLevel : "+organization.getContractLevel()+
                    "\n        deactivate : "+organization.getDeactivate()+
                    "\n        administarator : "+organization.getAdministrator()+
                    "\n        contractStartDate : "+organization.getContractStartDate()+
                    "\n        contractExpirationDate : "+organization.getContractExpirationDate()+
                    "\n        updateDateTime : "+organization.getUpdateDateTime()+
                    "\n        deactivateDateTime : "+organization.getDeactivateDateTime()+
                    "\n        etc : "+organization.getEtc();
        }else if (getData() == null){
            body = body+"\ndata : null";
        }
        return body;
    }

    public class data{

        public userAccount userAccount = new userAccount();
        public organization organization = new organization();

        public Create_Account_Response.data.userAccount getUserAccount() {
            return userAccount;
        }

        public Create_Account_Response.data.organization getOrganization() {
            return organization;
        }

        public class userAccount{


            int userId;
            String id;
            String password;

            int organizationId;
            String userCode;

            String department;
            int userLevel;
            String firstName;
            String lastName;
            int gender;
            String birthday;
            String phoneNumber;
            String email;
            String address;
            int deviceKind;
            int encryption;
            int status;
            int deactivate;
            String gmtCode;
            String joindateTime;
            String updateDateTime;
            String deactivateDateTime;

            public int getUserId() {
                return userId;
            }

            public String getId() {
                return id;
            }

            public String getPassword() {
                return password;
            }

            public int getOrganizationId() {
                return organizationId;
            }

            public String getUserCode() {
                return userCode;
            }

            public String getDepartment() {
                return department;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public int getGender() {
                return gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public String getEmail() {
                return email;
            }

            public String getAddress() {
                return address;
            }

            public int getDeviceKind() {
                return deviceKind;
            }

            public int getEncryption() {
                return encryption;
            }

            public int getStatus() {
                return status;
            }

            public int getDeactivate() {
                return deactivate;
            }

            public String getGmtCode() {
                return gmtCode;
            }

            public String getJoindateTime() {
                return joindateTime;
            }

            public String getUpdateDateTime() {
                return updateDateTime;
            }

            public String getDeactivateDateTime() {
                return deactivateDateTime;
            }
        }

        public class organization{

            String organizationId;
            String organiationCode;
            String organizationName;
            String countryCode;
            String countryName;
            String state;
            String city;
            String address;
            String phonenumber;
            int contractLevel;
            int deactivate;
            int administrator;
            String contractStartDate;
            String contractExpirationDate;
            String updateDateTime;
            String deactivateDateTime;
            String etc;

            public String getOrganizationId() {
                return organizationId;
            }

            public String getOrganiationCode() {
                return organiationCode;
            }

            public String getOrganizationName() {
                return organizationName;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public String getCountryName() {
                return countryName;
            }

            public String getState() {
                return state;
            }

            public String getCity() {
                return city;
            }

            public String getAddress() {
                return address;
            }

            public String getPhonenumber() {
                return phonenumber;
            }

            public int getContractLevel() {
                return contractLevel;
            }

            public int getDeactivate() {
                return deactivate;
            }

            public int getAdministrator() {
                return administrator;
            }

            public String getContractStartDate() {
                return contractStartDate;
            }

            public String getContractExpirationDate() {
                return contractExpirationDate;
            }

            public String getUpdateDateTime() {
                return updateDateTime;
            }

            public String getDeactivateDateTime() {
                return deactivateDateTime;
            }

            public String getEtc() {
                return etc;
            }
        }

    }
}

package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

import java.util.ArrayList;
import java.util.List;

public class Select_Organization_Response {
    boolean result;
    String extra;
    long systemTime;
    int error;

    data data = new data();

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

    public Select_Organization_Response.data getData() {
        return data;
    }

    public class data{

        List<organizationList> organizationList = new ArrayList<>();

        public List<organizationList> getOrganizationLists() {
            return organizationList;
        }


        public class organizationList{
            int organizationId;
            String organizationCode;
            String organizationName;
            String countryCode;
            String countryName;
            String state;
            String city;
            String address;
            String phoneNumber;
            int contractLevel;
            int deactivate;
            int administrator;
            String contractStartDate;
            String contractExpirationDate;
            String updateDateTime;
            String deactivateDateTime;
            String etc;

            public int getOrganizationId() {
                return organizationId;
            }

            public String getOrganizationCode() {
                return organizationCode;
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

            public String getPhoneNumber() {
                return phoneNumber;
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

package techtown.org.blescanner.BLE_Scan;

import android.app.Notification;
import android.location.Location;
import android.util.Log;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;

public class Service_UUID_Constant {
    final String Generic_Access = "1800";
    final String Alert_Notification_Service = "1811";
    final String Automation_IO = "1815";
    final String Battery_Service	= "180F";
    final String Blood_Pressure = "1810";
    final String Body_Composition = "181B";
    final String Bond_Management_Service ="181E";
    final String Continuous_Glucose_Monitoring =	"181F";
    final String Current_Time_Service = 	"1805";
    final String Cycling_Power = "1818";
    final String Cycling_Speed_and_Cadence = 	"1816";
    final String Device_Information	 = "180A";
    final String Environmental_Sensing	 = "181A";
    final String Fitness_Machine = "1826";
    final String Generic_Attribute	 = "1801";
    final String Glucose = "1808";
    final String Health_Thermometer = "1809";
    final String Heart_Rate	 = "180D";
    final String HTTP_Proxy	  = "1823";
    final String Human_Interface_Device =	 "1812";
    final String Immediate_Alert	= "1802";
    final String Indoor_Positioning	 = "1821";
    final String Insulin_Delivery	= "183A";
    final String Internet_Protocol_Support_Service	= "1820";
    final String Link_Loss	= "1803";
    final String Location_and_Navigation = "1819";
    final String Mesh_Provisioning_Service = "1827";
    final String Mesh_Proxy_Service	 = "1828";
    final String Next_DST_Change_Service = "1807";
    final String Object_Transfer_Service = "1825";
    final String Phone_Alert_Status_Service = "180E";
    final String Pulse_Oximeter_Service	 = "1822";
    final String Reconnection_Configuration = "1829";
    final String Reference_Time_Update_Service	 = "1806";
    final String Running_Speed_and_Cadence = 	"1814";
    final String Scan_Parameters =	"1813";
    final String Transport_Discovery = "1824";
    final String Tx_Power = 	"1804";
    final String User_Data =	"181C";
    final String Weight_Scale	= "181D";

    HashMap<String, String> UUID_SERVICE_LIST = new HashMap(){{
        put(Generic_Access,"Generic_Access");
        put(Alert_Notification_Service , "Alert_Notification_Service");
        put(Automation_IO, "Automation_IO");
        put(Battery_Service, "Battery_Service");
        put(Blood_Pressure, "Blood_Pressure");
        put(Body_Composition , "Body_Composition");
        put(Bond_Management_Service, "Bond_Management_Service");
        put(Continuous_Glucose_Monitoring, "Continuous_Glucose_Monitoring");
        put(Current_Time_Service, "Current_Time_Service");
        put(Cycling_Power, "Cycling_Power");
        put(Cycling_Speed_and_Cadence, "Cycling_Speed_and_Cadence");
        put(Device_Information, "Device_Information");
        put(Environmental_Sensing, "Environmental_Sensing");
        put(Fitness_Machine , "Fitness_Machine");
        put(Generic_Attribute, "Generic_Attribute");
        put(Glucose, "Glucose");
        put(Health_Thermometer, "Health_Thermometer");
        put(Heart_Rate, "Heart_Rate");
        put(HTTP_Proxy	,"HTTP_Proxy");
        put(Human_Interface_Device, "Human_Interface_Device");
        put(Immediate_Alert, "Immediate_Alert");
        put(Indoor_Positioning, "Indoor_Positioning");
        put(Insulin_Delivery,  "Insulin_Delivery");
        put(Internet_Protocol_Support_Service,	"Internet_Protocol_Support_Service");
        put(Link_Loss,	"Link_Loss");
        put(Location_and_Navigation, "Location_and_Navigation");
        put(Mesh_Provisioning_Service, "Mesh_Provisioning_Service");
        put(Mesh_Proxy_Service,	 "Mesh_Proxy_Service"	);
        put(Next_DST_Change_Service, "Next_DST_Change_Service");
        put(Object_Transfer_Service, "Object_Transfer_Service");
        put(Phone_Alert_Status_Service, "Phone_Alert_Status_Service");
        put(Pulse_Oximeter_Service,	 "Pulse_Oximeter_Service");
        put(Reconnection_Configuration, "Reconnection_Configuration");
        put(Reference_Time_Update_Service,	 "Reference_Time_Update_Service");
        put(Running_Speed_and_Cadence, "Running_Speed_and_Cadence");
        put(Scan_Parameters, "Scan_Parameters");
        put(Transport_Discovery, "Transport_Discovery");
        put(Tx_Power, "Tx_Power");
        put(User_Data, "User_Data");
        put(Weight_Scale,	"Weight_Scale");
    }};


    public String Find_Gatt_Service(String UUID){
        String service = UUID_SERVICE_LIST.get(UUID);
        if(service == null){
            service ="unknown type";
        }
        return service;
    }
}

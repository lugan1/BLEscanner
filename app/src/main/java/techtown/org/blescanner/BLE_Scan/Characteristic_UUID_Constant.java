package techtown.org.blescanner.BLE_Scan;

import android.app.Notification;
import android.content.Context;
import android.hardware.Sensor;
import android.icu.util.Output;
import android.inputmethodservice.Keyboard;
import android.location.Address;
import android.net.Network;
import android.view.Window;

import java.lang.ref.Reference;
import java.net.URI;
import java.security.Security;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

public class Characteristic_UUID_Constant {

/*    final String Aerobic_Heart_Rate_Lower_Limit	 2A7E
    final String Aerobic_Heart_Rate_Upper_Limit 2A84
    final String Aerobic_Threshold 2A7F
    final String Age	 2A80
    final String Aggregate 2A5A
    final String Alert_Category_ID	2A43
    final String Alert_Category_ID_Bit_Mask		2A42
    final String Alert_Level 2A06
    final String Alert_Notification_Control_Point	2A44
    final String Alert_Status 2A3F
    final String Altitude		2AB3
    final String Anaerobic_Heart_Rate_Lower_Limit		2A81
    final String Anaerobic_Heart_Rate_Upper_Limit		2A82
    final String Anaerobic_Threshold		2A83
    final String Analog	 2A58
    final String Analog_Output		2A59
    final String Apparent_Wind_Direction		2A73
    final String Apparent_Wind_Speed 	2A72
    final String Appearance		2A01
    final String Barometric_Pressure_Trend		2AA3
    final String Battery_Level		2A19
    final String Battery_Level_State		2A1B
    final String Battery_Power_State		2A1A
    final String Blood_Pressure_Feature		2A49
    final String Blood_Pressure_Measurement 	2A35
    final String Body_Composition_Feature		2A9B
    final String Body_Composition_Measurement		2A9C
    final String Body_Sensor_Location		2A38
    final String Bond_Management_Control_Point		2AA4
    final String Bond_Management_Features		2AA5
    final String Boot_Keyboard_Input_Report		2A22
    final String Boot_Keyboard_Output_Report		2A32
    final String Boot_Mouse_Input_Report		2A33
    final String Central_Address_Resolution		2AA6
    final String CGM_Feature		2AA8
    final String CGM_Measurement		2AA7
    final String CGM_Session_Run_Time	2AAB
    final String CGM_Session_Start_Time 	2AAA
    final String CGM_Specific_Ops_Control_Point	2AAC
    final String CGM_Status	2AA9
    final String Cross_Trainer_Data	2ACE
    final String CSC_Feature	2A5C
    final String CSC_Measurement	2A5B
    final String Current_Time	2A2B
    final String Cycling_Power_Control_Point	2A66
    final String Cycling_Power_Feature	2A65
    final String Cycling_Power_Measurement	2A63
    final String Cycling_Power_Vector 2A64
    final String Database_Change_Increment 2A99
    final String Date_of_Birth	2A85
    final String Date_of_Threshold_Assessment	2A86
    final String Date_Time	2A08
    final String Date_UTC	2AED
    final String Day_Date_Time	2A0A
    final String Day_of_Week	2A09
    final String Descriptor_Value_Changed	2A7D
    final String Device_Name	2A00
    final String Dew_Point	2A7B
    final String Digital	2A56
    final String Digital_Output	2A57
    final String DST_Offset	2A0D
    final String Elevation	2A6C
    final String Email_Address	2A87
    final String Exact_Time 100	2A0B
    final String Exact_Time_256	2A0C
    final String Fat_Burn_Heart Rate Lower Limit	2A88
    final String Fat Burn Heart Rate Upper Limit	2A89
    final String Firmware Revision_String	2A26
    final String First Name	2A8A
    final String Fitness_Machine_Control_Point	2AD9
    final String Fitness Machine Feature	2ACC
    final String Fitness Machine Status		2ADA
    final String Five Zone Heart Rate Limits 2A8B
    final String Floor Number	2AB2
    final String Gender		2A8C
    final String Glucose Feature		2A51
    final String Glucose Measurement		2A18
    final String Glucose Measurement Context 	2A34
    final String Gust Factor		2A74
    final String Hardware Revision String		2A27
    final String Heart Rate Control Point		2A39
    final String Heart Rate Max		2A8D
    final String Heart Rate Measurement		2A37
    final String Heat Index		2A7A
    final String Height		2A8E
    final String HID Control Point		2A4C
    final String HID Information		2A4A
    final String Hip Circumference		2A8F
    final String HTTP Control Point		2ABA
    final String HTTP Entity Body		2AB9
    final String HTTP Headers		2AB7
    final String HTTP Status Code		2AB8
    final String HTTPS Security		2ABB
    final String Humidity		2A6F
    final String IDD Annunciation Status		2B22
    final String IDD Command Control Point		2B25
    final String IDD Command Data		2B26
    final String IDD Features		2B23
    final String IDD History Data		2B28
    final String IDD Record Access Control Point		2B27
    final String IDD Status		2B21
    final String IDD Status Changed		2B20
    final String IDD Status Reader Control Point		2B24
    final String IEEE 11073-20601 Regulatory Certification Data List		2A2A
    final String Indoor Bike Data		2AD2
    final String Indoor Positioning Configuration		2AAD
    final String Intermediate Cuff Pressure		2A36
    final String Intermediate Temperature		2A1E
    final String Irradiance		2A77
    final String Language		2AA2
    final String Last Name		2A90
    final String Latitude		2AAE
    final String LN Control Point		2A6B
    final String  LN Feature		2A6A
    final String Local East Coordinate		2AB1
    final String Local North Coordinate		2AB0
    final String Local Time Information 	2A0F
    final String Location and Speed Characteristic	2A67
    final String Location Name		2AB5
    final String Longitude		2AAF
    final String Magnetic Declination		2A2C
    final String Magnetic Flux Density - 2D		2AA0
    final String Magnetic Flux Density - 3D		2AA1
    final String Manufacturer Name String		2A29
    final String Maximum Recommended Heart Rate		2A91
    final String Measurement Interval		2A21
    final String Model Number String		2A24
    final String Navigation		2A68
    final String Network Availability		2A3E
    final String New Alert		2A46
    final String Object Action Control Point		2AC5
    final String Object Changed		2AC8
    final String Object First-Created		2AC1
    final String Object ID		2AC3
    final String Object Last-Modified		2AC2
    final String Object List Control Point		2AC6
    final String Object List Filter	o	2AC7
    final String Object Name		2ABE
    final String Object Properties		2AC4
    final String Object Size		2AC0
    final String Object Type		2ABF
    final String OTS Feature		2ABD
    final String Peripheral Preferred Connection Parameters		2A04
    final String Peripheral Privacy Flag		2A02
    final String PLX Continuous Measurement Characteristic		2A5F
    final String PLX Features		2A60
    final String PLX Spot-Check Measurement		2A5E
    final String PnP ID	2A50
    final String Pollen Concentration		2A75
    final String Position 2D		2A2F
    final String Position 3D		2A30
    final String Position Quality		2A69
    final String Pressure		2A6D
    final String Protocol Mode		2A4E
    final String Pulse Oximetry Control Point		2A62
    final String Rainfall	2A78
    final String RC Feature	2B1D
    final String RC Settings	2B1E
    final String Reconnection Address	2A03
    final String Reconnection Configuration Control Point	2B1F
    final String Record Access Control Point	2A52
    final String Reference Time Information	 2A14
    final String Removable 2A3A
    final String Report	2A4D
    final String Report Map	2A4B
    final String Resolvable Private Address Only 2AC9
    final String Resting Heart Rate	2A92
    final String Ringer Control point 2A40
    final String Ringer Setting	2A41
    final String Rower Data	2AD1
    final String RSC Feature	2A54
    final String RSC Measurement 2A53
    final String SC Control Point	2A55
    final String Scan Interval Window 2A4F
    final String Scan Refresh	2A31
    final String Scientific Temperature Celsius	2A3C
    final String Secondary Time Zone 2A10
    final String Sensor Location	2A5D
    final String Serial Number String	2A25
    final String Service Changed	2A05
    final String Service Required	2A3B
    final String Software Revision String	2A28
    final String Sport Type for Aerobic and Anaerobic Thresholds	2A93
    final String Stair Climber Data	2AD0
    final String Step Climber Data 2ACF
    final String String	2A3D
    final String Supported Heart Rate Range	2AD7
    final String Supported Inclination Range 2AD5
    final String Supported New Alert Category 2A47
    final String Supported Power Range	2AD8
    final String Supported Resistance Level Range 2AD6
    final String Supported Speed Range	2AD4
    final String Supported Unread Alert Category	 2A48
    final String System ID	2A23
    final String TDS Control Point	2ABC
    final String Temperature	2A6E
    final String Temperature Celsius	2A1F
    final String Temperature Fahrenheit 2A20
    final String Temperature Measurement	2A1C
    final String Temperature Type	2A1D
    final String Three Zone Heart Rate Limits	2A94
    final String Time Accuracy	2A12
    final String Time Broadcast 2A15
    final String Time Source 2A13
    final String Time Update Control Point 2A16
    final String Time Update State	2A17
    final String Time with DST	2A11
    final String Time Zone	2A0E
    final String Training Status	2AD3
    final String Treadmill Data	2ACD
    final String True Wind Direction	2A71
    final String True Wind Speed 2A70
    final String Two Zone Heart Rate Limit	2A95
    final String Tx Power Level	2A07
    final String Uncertainty	2AB4
    final String Unread Alert Status	2A45
    final String URI 2AB6
    final String User Control Point	2A9F
    final String User Index	2A9A
    final String UV Index	2A76
    final String VO2 Max	2A96
    final String Waist Circumference	2A97
    final String Weight	2A98
    final String Weight Measurement	2A9D
    final String Weight Scale Feature	2A9E
    final String Wind Chill	2A79	*/
    
}

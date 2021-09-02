package techtown.org.blescanner.BLE_Scan;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import techtown.org.blescanner.R;

public class Scan_listView extends LinearLayout {
    TextView textView1;
    TextView textView2;
    TextView textView3;

    public Scan_listView(Context context) {
        super(context);
        init(context);
    }

    public Scan_listView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ble_scan_list_item,this,true);

        textView1 = (TextView)findViewById(R.id.device_name);
        textView2 = (TextView)findViewById(R.id.device_address);
        textView3 = (TextView)findViewById(R.id.device_rssi);
    }

    public void setName(String name){
        textView1.setText(name);
    }

    public void setAddress(String address){
        textView2.setText(address);
    }

    public void setRssi(String rssi) {
        textView3.setText(rssi);
    }

}

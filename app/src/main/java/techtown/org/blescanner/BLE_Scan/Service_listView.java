package techtown.org.blescanner.BLE_Scan;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import techtown.org.blescanner.R;

public class Service_listView extends LinearLayout {
    TextView textView1, textView2, textView3;

    public Service_listView(Context context) {
        super(context);
        init(context);
    }

    public Service_listView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ble_service_list_item,this,true);

        textView1 = (TextView)findViewById(R.id.txtv_Service_name);
        textView2 = (TextView)findViewById(R.id.txtv_Service_UUID);
        textView3 = (TextView)findViewById(R.id.txtv_Service_type);
    }

    public void setName(String name) {
        this.textView1.setText(name);
    }

    public void setUUID(String UUID) {
        this.textView2.setText(UUID);
    }

    public void setType(String type) {
        this.textView3.setText(type);
    }
}

package techtown.org.blescanner.BLE_Scan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import techtown.org.blescanner.R;


public class Notify_Fragment extends Fragment {

    BluetoothModule bluetoothModule = BluetoothModule.getInstance();

    public TextView getTxtv_ch_value() {
        return txtv_ch_value;
    }

    public void setTxtv_ch_value(TextView txtv_ch_value) {
        this.txtv_ch_value = txtv_ch_value;
    }

    static TextView txtv_ch_value;

    public TextView getTxtv_ch_value_string() {
        return txtv_ch_value_string;
    }

    public void setTxtv_ch_value_string(TextView txtv_ch_value_string) {
        Read_Fragment.txtv_ch_value_string = txtv_ch_value_string;
    }

    static TextView txtv_ch_value_string;

    int service_index, ch_index;

    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ble_notify_fragment,container,false);


        Bundle readBundle = getArguments();

        String Service_UUID = readBundle.getString("Service");
        String Characteristic_UUID = readBundle.getString("Ch");
        service_index = readBundle.getInt("service_index",-1);

        ch_index = readBundle.getInt("ch_index",-1);

        TextView txtv_Service_UUID = (TextView)rootView.findViewById(R.id.txtv_pservice_UUID3);
        TextView txtv_Characteristic_UUID = (TextView)rootView.findViewById(R.id.txtv_pcharacteristic_UUID3);

        txtv_Service_UUID.setText("서비스 UUID : \n"+Service_UUID);
        txtv_Characteristic_UUID.setText("특성 UUID : \n"+Characteristic_UUID);

        txtv_ch_value = (TextView)rootView.findViewById(R.id.txtv_ch_value);
        txtv_ch_value_string = (TextView)rootView.findViewById(R.id.txtv_ch_value_string);

        bluetoothModule.setWitchActivity("Notify");

        Button btn_notify = (Button)rootView.findViewById(R.id.btn_notify);
        btn_notify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bluetoothModule.ReadChar(service_index, ch_index);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        Fragment restFragment = getFragmentManager().findFragmentByTag("rest");

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(restFragment).commit();

    }
}

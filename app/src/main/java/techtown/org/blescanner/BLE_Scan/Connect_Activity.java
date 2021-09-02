package techtown.org.blescanner.BLE_Scan;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import techtown.org.blescanner.MainActivity;
import techtown.org.blescanner.R;

public class Connect_Activity extends AppCompatActivity {
    String DeviceAddr;
    String DeviceName;

    BluetoothDevice device;

    Button btn_connect, btn_disconnect;

    static Button btn_service;

    static TextView textv_state;

    static TextView textv_device_name, textv_device_address;

    BluetoothModule bluetoothModule = BluetoothModule.getInstance();

    static HashMap<String, BluetoothModule> bluetoothModuleList;

    BluetoothGatt gatt;

    FragmentManager fragmentManager;

    MainActivity mainActivity = new MainActivity();

    public Button getBtn_service() {
        return btn_service;
    }

    public void setBtn_service(Button btn_service) {
        this.btn_service = btn_service;
    }

    public TextView getTextv_state() {
        return textv_state;
    }

    public void setTextv_state(TextView textv_state) {
        this.textv_state = textv_state;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ble_connect_state);


        mainActivity.setRecvMsg("");

        btn_connect = (Button)findViewById(R.id.btn_connect);
        btn_disconnect = (Button)findViewById(R.id.btn_disconnect);
        btn_service = (Button)findViewById(R.id.btn_service);

        textv_state = (TextView)findViewById(R.id.Textv_gattstate);
        textv_device_name = (TextView)findViewById(R.id.Text_device_name);
        textv_device_address = (TextView)findViewById(R.id.Text_device_address);


        fragmentManager = getSupportFragmentManager();




        Intent stateIntent = getIntent();

        device = stateIntent.getParcelableExtra("device");

        DeviceName = device.getName();
        DeviceAddr = device.getAddress();

        if(bluetoothModule.GattList.containsKey(DeviceAddr)){
            //Log.v("cnnt","Gatt리스트에 있음");

            //BluetoothManager bm = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);

            BluetoothManager bm = bluetoothModule.getBluetoothManager();

            if(bm.getConnectionState(device, BluetoothProfile.GATT) == BluetoothProfile.STATE_CONNECTED){
                //Log.v("cnnt","connected : "+bm.getConnectionState(device,BluetoothProfile.GATT));

                textv_state.setText("연결 됨.");
                btn_service.setEnabled(true);
                gatt = bluetoothModule.getGatt(DeviceAddr);

                bluetoothModule.gattConnect(DeviceAddr, new BluetoothModule.BluetoothConnectImpl() {

                    @Override
                    public void onSuccessConnect(BluetoothDevice device) {
                        Log.v("BLELOG", "onSuccessConnect: 연결완료");
                        Log.v("BLELOG","Success");
                    }

                    @Override
                    public void onFailed() {
                        Log.v("BLELOG", "onFailed: 연결실패, 다시 연결중....");
                        Log.v("BLELOG","Failed");
                    }
                },getApplicationContext());

            }
            else if(bm.getConnectionState(device, BluetoothProfile.GATT) == BluetoothProfile.STATE_DISCONNECTED){
                textv_state.setText("연결 끊김.");
                //Log.v("cnnt","disconnected");
            }

        };

        if(DeviceName == null){
            DeviceName = "Unknown Device";
        }

        textv_device_name.setText(DeviceName);
        textv_device_address.setText(DeviceAddr);

        btn_connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DeviceAddr = device.getAddress();
                DeviceName = device.getName();

                Log.v("BLELOG","select Device : "+DeviceName+"    Mac : "+DeviceAddr);
                bluetoothModule.gattConnect(DeviceAddr, new BluetoothModule.BluetoothConnectImpl() {

                    @Override
                    public void onSuccessConnect(BluetoothDevice device) {
                        Log.v("BLELOG", "onSuccessConnect: 연결완료");
                        Log.v("BLELOG","Success");
                    }

                    @Override
                    public void onFailed() {
                        Log.v("BLELOG", "onFailed: 연결실패, 다시 연결중....");
                        Log.v("BLELOG","Failed");
                    }
                },getApplicationContext());

            }
        });

        btn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    bluetoothModule.disconnect();
            }
        });


        btn_service.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onServiceActivity();
            }
        });
    }

    public void onServiceActivity(){
        mainActivity.setRecvMsg("onService");
        finish();
    }


}

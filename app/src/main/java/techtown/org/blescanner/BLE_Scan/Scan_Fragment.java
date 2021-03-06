package techtown.org.blescanner.BLE_Scan;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import techtown.org.blescanner.MainActivity;
import techtown.org.blescanner.R;

public class Scan_Fragment extends Fragment {
    TextView textView, textView2;
    Button button;
    ListView listView;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBLEScanner;
    private boolean mScanning;
    private Handler mHandler;
    private static final int REQUEST_ENABLE_BT = 1;


    private static final long SCAN_PERIOD = 30000;
    LeDeviceListAdapter mLeDeviceListAdapter;
    public ArrayList<BluetoothDevice> mLeDevices;
    public ArrayList<Integer> mLeRssi;

    MainActivity mainActivity = new MainActivity();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.v("ccnt","frag onCreateView");

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ble_scan_fragment,container,false);
        /////////////////////////////////??? ?????? ??? ?????? ????????? ??????////////////////////////////////////////////////////////////////////////////////////
        textView = (TextView)rootView.findViewById(R.id.textView);
        textView2 = (TextView)rootView.findViewById(R.id.textView2);

        button = (Button)rootView.findViewById(R.id.button);



        mLeDeviceListAdapter = new LeDeviceListAdapter();
        listView = (ListView)rootView.findViewById(R.id.listView);

        listView.setAdapter(mLeDeviceListAdapter);
        listView.setOnItemClickListener(deviceClickListener);

        mHandler = new Handler();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////BLE ?????? ???????????? ?????? ///////////////////////////////////////////////////////////
        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(getActivity(), "BLE ????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show();
        }

        final BluetoothManager bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(getActivity(), "???????????? ????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////// BLE ???????????? ??????////////////////////////////////////////////////////////////
        mBLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
        // Checks if Bluetooth LE Scanner is available.
        if (mBLEScanner == null) {
            Toast.makeText(getActivity(), "Can not find BLE Scanner", Toast.LENGTH_SHORT).show();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////?????? ?????? ????????? /////////////////////////////////////////////////////////////////////////
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeDeviceListAdapter.clear();
                isBTEnabled();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////?????? ???????????? ??? ??????///////////////////////////////
        pemissionCheck();
        //////////////////////////////////////////////////////////////////////////////////

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Log.v("ccnt","frag onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

       // Log.v("ccnt","frag onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Log.v("ccnt","frag onDestroy()");
        scanLeDevice(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.v("ccnt","frag onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        //Log.v("ccnt","frag onStop");
    }

    @Override
    public  void onResume() {
        super.onResume();
        //Log.v("ccnt","frag onResume");
        // Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
        // fire an intent to display a dialog asking the user to grant permission to enable it.
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Log.v("ccnt","frag onAttach");
    }






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT) {
            scanLeDevice(true);
        }
        else if (resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getActivity(), "???????????? ????????? ???????????? ???  ?????? ??????????????????", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    ////////////////////////////////////////////////???????????? ??????///////////////////////////////////////////////////////////////////////
    public void pemissionCheck() {
        int permissionCOARS = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);

        int permissionFINE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCOARS != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(getActivity(), "COARS ?????? ?????? ?????????", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }

        if (permissionFINE != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(getActivity(), "FINE ?????? ?????? ?????????", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void isBTEnabled(){
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        else if (mBluetoothAdapter.isEnabled()){
            scanLeDevice(true);
        }
    }

    ////////////////////////////////BLE ?????? ????????? ??????/////////////////////////////////////////
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBLEScanner.stopScan(mScanCallback);
                    button.setAlpha(1);
                    button.setClickable(true);
                    textView2.setText("?????????");
                    //Log.v("BLELOG","Post Delayed stopScan\n \n ");
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBLEScanner.startScan(mScanCallback);
            button.setAlpha(.4f);
            button.setClickable(false);
            textView2.setText("?????????");
            //Log.v("BLELOG","startScan");

        }
        else {
            //Log.v("BLELOG","stopScan");
            mScanning = false;
            //Log.v("BLELOG","????????? ?????????");
            mBLEScanner.stopScan(mScanCallback);
            button.setClickable(true);
        }
    }


    // Device scan callback.
    public ScanCallback mScanCallback = new ScanCallback() {
        int i= 30;
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            //Log.v("BLELOG","onScanResult Device Name : "+result.getDevice().getName());
            processResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            //Log.v("BLELOG","ScanCallback");
            for (ScanResult result : results) {
                for(int i=0; i<results.size(); i++){
                    //Log.v("BLELOG","onBatchScanResult, DeviceName : "+results.get(i).getDevice().getName());
                }
                processResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.v("BLELOG","onScanFaild");
        }

        private void processResult(final ScanResult result) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLeDeviceListAdapter.addDevice(result);
                    mLeDeviceListAdapter.notifyDataSetChanged();
                }
            });
        }
    };
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////???????????? ???????????? ????????? ?????? ????????? ??????//////////////////////////////////////////////////////
    private AdapterView.OnItemClickListener deviceClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent stateIntent = new Intent(getActivity(),Connect_Activity.class);

            BluetoothDevice device = mLeDevices.get(position);

            mainActivity.setMdevice_name(device.getName());
            mainActivity.setMdevice_addr(device.getAddress());

            stateIntent.putExtra("device",device);

            startActivity(stateIntent);
        }
    };
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////





    /////////////////////////????????? ??????////////////////////////////////////////////////////////////////////////
    private class LeDeviceListAdapter extends BaseAdapter {

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mLeRssi = new ArrayList<Integer>();
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        public void addDevice(ScanResult result) {
            //???????????? ?????????
            if(!mLeDevices.contains(result.getDevice())) {
                mLeDevices.add(result.getDevice());
                mLeRssi.add(result.getRssi());
            }



        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //ViewHolder viewHolder = new ViewHolder();

            Scan_listView singerItemView = new Scan_listView(getActivity());

            try{
                BluetoothDevice device = mLeDevices.get(i);
                int DeviceRssi = mLeRssi.get(i);

                final String deviceName = device.getName();


                if (deviceName != null && deviceName.length() > 0){
                    singerItemView.setName(deviceName);
                }else{
                    singerItemView.setName("unknown device");
                }

                singerItemView.setAddress(device.getAddress());
                singerItemView.setRssi(String.valueOf(DeviceRssi));
            }catch(IndexOutOfBoundsException e){
                Log.v("cnnt","IndexOutOfBoundsException ");
                mLeDevices.clear();
                mLeDeviceListAdapter.clear();
                mLeDeviceListAdapter.notifyDataSetChanged();
            }

            return singerItemView;
        }
    }


    public interface FragCallback{
        void sendMsg(String msg);
    }

}

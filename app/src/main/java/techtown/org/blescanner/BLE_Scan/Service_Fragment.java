package techtown.org.blescanner.BLE_Scan;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import techtown.org.blescanner.MainActivity;
import techtown.org.blescanner.R;

public class Service_Fragment extends Fragment {

    TextView txtv_name, txtv_addr;
    ListView ServiceList;
    ServiceListAdapter serviceListAdapter;

    BluetoothModule bluetoothModule = BluetoothModule.getInstance();
    Service_UUID_Constant service_uuid_constant = new Service_UUID_Constant();

    BluetoothGatt gatt;

    MainActivity mainActivity = new MainActivity();

    static List<BluetoothGattService> mGattService;

    List<BluetoothGattService> mGattServiceList;

    FragmentTransaction fragmentTransaction;

    Characteristic_Fragment characteristic_fragment;

    public static List<BluetoothGattService> getmGattService() {
        return mGattService;
    }

    public static void setmGattService(List<BluetoothGattService> mGattService) {
        Service_Fragment.mGattService = mGattService;
    }


    String Device_name, Device_addr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ble_service_fragment,container,false);

        Bundle serviceBundle = getArguments();


        Device_name = serviceBundle.getString("device_name");
        Device_addr = serviceBundle.getString("device_addr");

        gatt = bluetoothModule.getGatt(Device_addr);


        mGattServiceList = gatt.getServices();


        txtv_name = (TextView)rootView.findViewById(R.id.txtv_device_name);
        txtv_addr = (TextView)rootView.findViewById(R.id.txtv_device_mac);


        ServiceList = (ListView)rootView.findViewById(R.id.service_listview);


        serviceListAdapter = new ServiceListAdapter();
        serviceListAdapter.notifyDataSetChanged();
        ServiceList.setAdapter(serviceListAdapter);
        ServiceList.setOnItemClickListener(serviceClickListener);

        txtv_name.setText("Device Name : "+Device_name);
        txtv_addr.setText("mac : "+Device_addr);

        return rootView;

    }

    @Override
    public void onPause() {
        super.onPause();

        Fragment restFragment = getFragmentManager().findFragmentByTag("rest");

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(restFragment).commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("cnnt","ServiceDestroy");
    }


    private AdapterView.OnItemClickListener serviceClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Bundle characteristic_bundle = new Bundle();

            characteristic_bundle.putString("mac",Device_addr);
            characteristic_bundle.putInt("service_index",position);

            characteristic_fragment = new Characteristic_Fragment();

            characteristic_fragment.setArguments(characteristic_bundle);



            fragmentTransaction = getFragmentManager().beginTransaction();

            Fragment service_fragment = getFragmentManager().findFragmentByTag("service");

            Fragment rest_fragment = getFragmentManager().findFragmentByTag("rest");


            fragmentTransaction.add(R.id.frag_container,characteristic_fragment,"characteristic");
            fragmentTransaction.addToBackStack("characteristic_stack");
            fragmentTransaction.hide(service_fragment);
            fragmentTransaction.hide(rest_fragment);
            fragmentTransaction.show(characteristic_fragment);
            fragmentTransaction.commit();

        }
    };




    private class ServiceListAdapter extends BaseAdapter {

        public ServiceListAdapter() {
            super();
        }

        public void addService(List<BluetoothGattService> gattService) {
            //중복검사 제외외
            mGattServiceList = gattService;
        }

        public BluetoothGattService getService(int position) {
            return mGattServiceList.get(position);
        }

        public void clear() {
            mGattServiceList.clear();
        }

        @Override
        public int getCount() {
            return mGattServiceList.size();
        }

        @Override
        public Object getItem(int i) {
            return mGattServiceList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //Log.v("cnnt","get view");

            Service_listView singerItemView = new Service_listView(getActivity());

            BluetoothGattService gattService = mGattServiceList.get(i);

            String service_name = "서비스("+i+")";

            final String service_UUID = gattService.getUuid().toString();

            //Log.v("cnnt","service UUID : "+service_UUID);

            String service_type = service_uuid_constant.Find_Gatt_Service(service_UUID.substring(4,8));

            if (service_UUID != null && service_UUID.length() > 0){
                singerItemView.setUUID(service_UUID);
            }else{
                singerItemView.setName("unknown UUID");
            }

            singerItemView.setType("타입( "+service_type+" )");
            singerItemView.setName(service_name);

            return singerItemView;
        }
    }


}

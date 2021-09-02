package techtown.org.blescanner.BLE_Scan;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import techtown.org.blescanner.R;

public class Characteristic_Fragment extends Fragment {
    static List<BluetoothGattCharacteristic> mGattCharacteristics;

    AlertDialog alert;
    ListView characteristic_listview;
    CharacteristicListAdapter characteristicListAdapter;
    Bundle characteristic_bundle;

    List<BluetoothGattCharacteristic> characteristic_List;
    BluetoothGattService GattService;

    BluetoothGatt gatt;

    BluetoothModule bluetoothModule = BluetoothModule.getInstance();

    int service_index;

    String Device_addr;
    String GattService_UUID, GattCharacteristic_UUID;

    FragmentTransaction fragmentTransaction;

    Fragment characteristic_fragment, rest_fragment, service_fragment;


    Read_Fragment read_fragment;
    Indicate_Fragment indicate_fragment;
    Notify_Fragment notify_fragment;


    HashMap<Integer, CharSequence[]> ChoisePropertiesCS;

    public static void setmGattCharacteristics(List<BluetoothGattCharacteristic> mGattCharacteristics) {
        Characteristic_Fragment.mGattCharacteristics = mGattCharacteristics;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ble_characteristic_fragment,container,false);


        characteristic_bundle = getArguments();

        ChoisePropertiesCS = new HashMap<>();

        Device_addr = characteristic_bundle.getString("mac");
        service_index = characteristic_bundle.getInt("service_index",0);


        gatt = bluetoothModule.getGatt(Device_addr);

        GattService = gatt.getServices().get(service_index);

        characteristic_List = GattService.getCharacteristics();



        characteristicListAdapter = new CharacteristicListAdapter();

        characteristic_listview = (ListView)rootView.findViewById(R.id.characteristic_listview);

        characteristic_listview.setAdapter(characteristicListAdapter);
        characteristic_listview.setOnItemClickListener(CharacteristicClickListener);


        Log.v("cnnt","Characteristic createView");

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("cnnt","Characteristic onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("cnnt","Characteristic onResume");
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
        Log.v("cnnt","CharacteristicDestroy");
    }


    private AdapterView.OnItemClickListener CharacteristicClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            GattService_UUID = GattService.getUuid().toString();
            GattCharacteristic_UUID = GattService.getCharacteristics().get(position).getUuid().toString();

            BluetoothGattCharacteristic SelectCharacteristic = characteristic_List.get(position);

            DialogRadio(position, GattService_UUID, GattCharacteristic_UUID, position, service_index);

        }
    };

    private void DialogRadio(int position, final String service_UUID, final String characteristic_UUID, final int ch_index, final int service_index){
        final CharSequence[] ItemList = ChoisePropertiesCS.get(position);
        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());

        alt_bld.setTitle("성질 선택");

        alt_bld.setSingleChoiceItems(ItemList, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Intent openPropIntent;
                switch(ItemList[item].toString()){
                    case "Read":
                        read_fragment = new Read_Fragment();

                        Bundle readBundle = new Bundle();
                        readBundle.putString("Service",service_UUID);
                        readBundle.putString("Ch",characteristic_UUID);
                        readBundle.putInt("service_index",service_index);
                        readBundle.putInt("ch_index",ch_index);

                        read_fragment.setArguments(readBundle);

                        characteristic_fragment = getFragmentManager().findFragmentByTag("characteristic");
                        service_fragment = getFragmentManager().findFragmentByTag("service");
                        rest_fragment = getFragmentManager().findFragmentByTag("rest");


                        fragmentTransaction = getFragmentManager().beginTransaction();

                        fragmentTransaction.add(R.id.frag_container,read_fragment,"read");
                        fragmentTransaction.addToBackStack("read_stack");

                        fragmentTransaction.hide(characteristic_fragment);
                        fragmentTransaction.hide(service_fragment);
                        fragmentTransaction.hide(rest_fragment);
                        fragmentTransaction.show(read_fragment);
                        fragmentTransaction.commit();

                        alert.dismiss();
                        //alert.cancel();
                        //alert.hide();
                        break;
                    case "Indicate":
                        indicate_fragment = new Indicate_Fragment();

                        Bundle IndicateBundle = new Bundle();
                        IndicateBundle.putString("Service",service_UUID);
                        IndicateBundle.putString("Ch",characteristic_UUID);
                        IndicateBundle.putInt("service_index",service_index);
                        IndicateBundle.putInt("ch_index",ch_index);

                        indicate_fragment.setArguments(IndicateBundle);

                        characteristic_fragment = getFragmentManager().findFragmentByTag("characteristic");
                        service_fragment = getFragmentManager().findFragmentByTag("service");
                        rest_fragment = getFragmentManager().findFragmentByTag("rest");


                        fragmentTransaction = getFragmentManager().beginTransaction();

                        fragmentTransaction.add(R.id.frag_container,indicate_fragment,"indicate");
                        fragmentTransaction.addToBackStack("indicate_stack");

                        fragmentTransaction.hide(characteristic_fragment);
                        fragmentTransaction.hide(service_fragment);
                        fragmentTransaction.hide(rest_fragment);
                        fragmentTransaction.show(indicate_fragment);
                        fragmentTransaction.commit();

                        alert.dismiss();
                        //alert.cancel();
                        //alert.hide();
                        break;
                    case "Notify":
                        notify_fragment = new Notify_Fragment();

                        Bundle NotifyBundle = new Bundle();
                        NotifyBundle.putString("Service",service_UUID);
                        NotifyBundle.putString("Ch",characteristic_UUID);
                        NotifyBundle.putInt("service_index",service_index);
                        NotifyBundle.putInt("ch_index",ch_index);

                        notify_fragment.setArguments(NotifyBundle);

                        characteristic_fragment = getFragmentManager().findFragmentByTag("characteristic");
                        service_fragment = getFragmentManager().findFragmentByTag("service");
                        rest_fragment = getFragmentManager().findFragmentByTag("rest");


                        fragmentTransaction = getFragmentManager().beginTransaction();

                        fragmentTransaction.add(R.id.frag_container,notify_fragment,"notify");
                        fragmentTransaction.addToBackStack("notify_stack");

                        fragmentTransaction.hide(characteristic_fragment);
                        fragmentTransaction.hide(service_fragment);
                        fragmentTransaction.hide(rest_fragment);
                        fragmentTransaction.show(notify_fragment);
                        fragmentTransaction.commit();

                        alert.dismiss();
                        //alert.cancel();
                        //alert.hide();
                        break;
                }
                // Toast.makeText(getApplicationContext(), "Phone Model = "+ItemList[item], Toast.LENGTH_SHORT).show();
                // dialog.cancel();
            }
        });
        alert = alt_bld.create();
        alert.show();
    }



    private class CharacteristicListAdapter extends BaseAdapter {

        public CharacteristicListAdapter() {
            super();
        }

        public void addCharacteristic(List<BluetoothGattCharacteristic> gattCharacteristics) {
            //중복검사 제외외
        }

        public BluetoothGattCharacteristic getCharacteristic(int position) {
            return characteristic_List.get(position);
        }

        public void clear() {
            characteristic_List.clear();
        }

        @Override
        public int getCount() {
            return characteristic_List.size();
        }

        @Override
        public Object getItem(int i) {
            return characteristic_List.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            Characteristic_listView singerItemView = new Characteristic_listView(getActivity());

            String characteristic_name = "특성("+i+")";

            final String characteristic_UUID = characteristic_List.get(i).getUuid().toString();

            int characteristic_properties = 0;

            characteristic_properties = characteristic_List.get(i).getProperties();

            StringBuilder property = new StringBuilder();

            ArrayList<String> properListArray = new ArrayList<>();

                if ((characteristic_properties & BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                    property.append("Read");
                    property.append(" , ");
                    properListArray.add("Read");

                }
                if ((characteristic_properties & BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
                    property.append("Write");
                    property.append(" , ");
                    properListArray.add("Write");
                }
                if ((characteristic_properties & BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) > 0) {
                    property.append("Write No Response");
                    property.append(" , ");
                    properListArray.add("Write No Response");
                }
                if ((characteristic_properties & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                    property.append("Notify");
                    property.append(" , ");
                    properListArray.add("Notify");
                }
                if ((characteristic_properties & BluetoothGattCharacteristic.PROPERTY_INDICATE) > 0) {
                    property.append("Indicate");
                    property.append(" , ");
                    properListArray.add("Indicate");
                }

            if (property.length() > 1) {
                property.delete(property.length() - 2, property.length() - 1);
            }
            if (property.length() > 0) {
                singerItemView.setproperties(String.valueOf("성질" + "( " + property.toString() + ")"));
            }

            if (characteristic_UUID != null && characteristic_UUID.length() > 0){
                singerItemView.setUUID(characteristic_List.get(i).getUuid().toString());
            }else{
                singerItemView.setUUID("unknown UUID");
            }


            CharSequence[] properList = properListArray.toArray(new CharSequence[properListArray.size()]);
            ChoisePropertiesCS.put(i,properList);

            singerItemView.setname(characteristic_name);

            return singerItemView;
        }
    }

}

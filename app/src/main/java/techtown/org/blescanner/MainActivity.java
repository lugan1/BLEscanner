package techtown.org.blescanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import techtown.org.blescanner.BLE_Scan.Characteristic_Fragment;
import techtown.org.blescanner.BLE_Scan.Scan_Fragment;
import techtown.org.blescanner.BLE_Scan.Service_Fragment;
import techtown.org.blescanner.HTTP_REST.Login_Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Scan_Fragment getScan_fragment() {
        return scan_fragment;
    }

    public void setScan_fragment(Scan_Fragment scan_fragment) {
        this.scan_fragment = scan_fragment;
    }


Scan_Fragment scan_fragment;
Login_Fragment login_fragment;
Service_Fragment service_fragment;
Characteristic_Fragment characteristic_fragment;

Fragment characteristicFragment;
Fragment serviceFragment;
Fragment readFragment;
Fragment notifyFragment;
Fragment indicateFragment;
Fragment createaccFragment;
Fragment succesFragment;


FragmentTransaction fragmentTransaction;
FragmentManager fragmentManager;

static String mdevice_name;
static String mdevice_addr;

    public String getMdevice_name() {
        return mdevice_name;
    }

    public void setMdevice_name(String mdevice_name) {
        this.mdevice_name = mdevice_name;
    }

    public String getMdevice_addr() {
        return mdevice_addr;
    }

    public void setMdevice_addr(String mdevice_addr) {
        this.mdevice_addr = mdevice_addr;
    }

    ImageButton btn_scan_frag;


    public String getRecvMsg() {
        return recvMsg;
    }

    public void setRecvMsg(String recvMsg) {
        this.recvMsg = recvMsg;
    }

    static String recvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        scan_fragment = new Scan_Fragment();
        login_fragment = new Login_Fragment();

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frag_container2, login_fragment,"rest");
        fragmentTransaction.add(R.id.frag_container,scan_fragment,"scan");
        fragmentTransaction.hide(login_fragment);
        fragmentTransaction.hide(scan_fragment);
        fragmentTransaction.commit();


        btn_scan_frag = (ImageButton)findViewById(R.id.btn_scanfrag);
        ImageButton btn_rest_frag = (ImageButton)findViewById(R.id.btn_restfrag);

        btn_scan_frag.setOnClickListener(this);
        btn_rest_frag.setOnClickListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();

        //Log.v("ccnt","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        //Log.v("ccnt","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        scan_fragment.mLeDevices.clear();
        scan_fragment.mLeRssi.clear();
        //Log.v("cnnt","MainActivity : onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Log.v("ccnt","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recvMsg == "onService"){
            //Log.v("ccnt","recvMsg == onService");

            service_fragment = new Service_Fragment();
            Bundle serviceBundle = new Bundle();

            //Log.v("cnnt","mdevice name : "+mdevice_name);

            //Log.v("cnnt","mdevice addr : "+mdevice_addr);

            serviceBundle.putString("device_name",mdevice_name);
            serviceBundle.putString("device_addr",mdevice_addr);

            service_fragment.setArguments(serviceBundle);

            createaccFragment = getSupportFragmentManager().findFragmentByTag("creacc");
            succesFragment = getSupportFragmentManager().findFragmentByTag("success");

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(scan_fragment);

            if(createaccFragment != null){
                fragmentTransaction.hide(createaccFragment);
            }

            if(succesFragment != null){
                fragmentTransaction.hide(succesFragment);
            }

            fragmentTransaction.hide(login_fragment);
            fragmentTransaction.add(R.id.frag_container,service_fragment,"service");
            fragmentTransaction.addToBackStack("service_stack");
            fragmentTransaction.commit();
        }
        //Log.v("cnnt","MainActivity recvMsg: "+recvMsg);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scanfrag:
                //Log.v("cnnt","btn_scanfrag");

                serviceFragment = getSupportFragmentManager().findFragmentByTag("service");
                characteristicFragment = getSupportFragmentManager().findFragmentByTag("characteristic");

                readFragment = getSupportFragmentManager().findFragmentByTag("read");
                indicateFragment = getSupportFragmentManager().findFragmentByTag("indicate");
                notifyFragment = getSupportFragmentManager().findFragmentByTag("notify");

                createaccFragment = getSupportFragmentManager().findFragmentByTag("creacc");
                succesFragment = getSupportFragmentManager().findFragmentByTag("success");

                fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if(createaccFragment != null){
                    fragmentTransaction.hide(createaccFragment);
                }

                if(succesFragment != null){
                    fragmentTransaction.hide(succesFragment);
                }

                fragmentTransaction.hide(login_fragment);


                if(serviceFragment != null && characteristicFragment == null && readFragment == null && indicateFragment == null && notifyFragment == null){
                    //Log.v("cnnt","service frag != null && characteristicFragment == null");
                    fragmentTransaction.hide(scan_fragment);
                    fragmentTransaction.show(serviceFragment).commit();
                }else if(serviceFragment == null){
                    //Log.v("cnnt","serviceFragment == null");
                }


                if(characteristicFragment != null && readFragment == null && indicateFragment == null && notifyFragment == null){
                    //Log.v("cnnt","service frag != null && characteristicFragment == null");
                    fragmentTransaction.hide(scan_fragment);
                    fragmentTransaction.hide(service_fragment);
                    fragmentTransaction.show(characteristicFragment).commit();
                }else if(characteristicFragment == null){
                    //Log.v("cnnt","characteristicFragment == null");
                }


                if(readFragment != null){
                    fragmentTransaction.hide(scan_fragment);
                    fragmentTransaction.hide(service_fragment);
                    fragmentTransaction.hide(characteristicFragment);
                    fragmentTransaction.show(readFragment).commit();
                }
                if(indicateFragment != null){
                    fragmentTransaction.hide(scan_fragment);
                    fragmentTransaction.hide(service_fragment);
                    fragmentTransaction.hide(characteristicFragment);
                    fragmentTransaction.show(indicateFragment).commit();
                }
                if(notifyFragment != null){
                    fragmentTransaction.hide(scan_fragment);
                    fragmentTransaction.hide(service_fragment);
                    fragmentTransaction.hide(characteristicFragment);
                    fragmentTransaction.show(notifyFragment).commit();
                }


                if(serviceFragment == null && characteristicFragment == null && readFragment == null && indicateFragment == null && notifyFragment == null){
                    fragmentTransaction.show(scan_fragment).commit();
                }

                break;


            case R.id.btn_restfrag:
                //Log.v("cnnt","btn_restfrag");

                fragmentTransaction = getSupportFragmentManager().beginTransaction();

                serviceFragment = getSupportFragmentManager().findFragmentByTag("service");
                characteristicFragment = getSupportFragmentManager().findFragmentByTag("characteristic");
                readFragment = getSupportFragmentManager().findFragmentByTag("read");
                indicateFragment = getSupportFragmentManager().findFragmentByTag("indicate");
                notifyFragment = getSupportFragmentManager().findFragmentByTag("notify");
                createaccFragment = getSupportFragmentManager().findFragmentByTag("creacc");
                succesFragment = getSupportFragmentManager().findFragmentByTag("success");

                if(readFragment != null){
                    fragmentTransaction.hide(readFragment);
                }
                if(indicateFragment != null ){
                    fragmentTransaction.hide(indicateFragment);
                }
                if (notifyFragment != null){
                    fragmentTransaction.hide(notifyFragment);
                }


                if(characteristicFragment != null){
                    fragmentTransaction.hide(characteristicFragment);
                }
                if(serviceFragment != null){
                    fragmentTransaction.hide(serviceFragment);
                }


                if(createaccFragment !=null){
                    fragmentTransaction.hide(login_fragment);
                    fragmentTransaction.show(createaccFragment);
                }
                else if(createaccFragment == null){
                    fragmentTransaction.show(login_fragment);
                }

                if(succesFragment != null){
                    fragmentTransaction.hide(login_fragment);
                    fragmentTransaction.show(succesFragment);
                } else if(succesFragment == null && createaccFragment == null){
                    fragmentTransaction.show(login_fragment);
                }

                fragmentTransaction.hide(scan_fragment).commit();


                break;
        }
    }
}

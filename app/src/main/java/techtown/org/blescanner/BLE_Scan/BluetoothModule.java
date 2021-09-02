package techtown.org.blescanner.BLE_Scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class BluetoothModule extends AppCompatActivity {
    //BluetoothGatt 객체로 Connect 해주고, writeCharacter 해주는 클래스
    public static final String TAG = "BluetoothModule";

    private BluetoothGatt bluetoothGatt;

    public BluetoothManager getBluetoothManager() {
        return bluetoothManager;
    }

    BluetoothManager bluetoothManager;

    TextView textv_state;
    Button btn_service;

    public String getWitchActivity() {
        return witchActivity;
    }

    public void setWitchActivity(String witchActivity) {
        this.witchActivity = witchActivity;
    }

    String witchActivity;

    private BluetoothConnectImpl btConnectCallback;
    private BluetoothWriteImpl btWriteCallback;
    private Context context;
    Connect_Activity connect_activity = new Connect_Activity();


    Characteristic_Fragment characteristic_fragment = new Characteristic_Fragment();
    Read_Fragment read_fragment = new Read_Fragment();

    Indicate_Fragment indicate_fragment = new Indicate_Fragment();

    Notify_Fragment notify_fragment = new Notify_Fragment();

    HashMap<String, BluetoothGatt> GattList = new HashMap<String, BluetoothGatt>();

    String value;

    public void ReadChar(int serviceIndex, int characteristicIndex){
        BluetoothGattCharacteristic characteristic = getGatt().getServices().get(serviceIndex).getCharacteristics().get(characteristicIndex);
        getGatt().readCharacteristic(characteristic);
    }

    BluetoothModule() {
        //Log.v("BLELOG","BluetoothModule()");
    }
    //생성자

    private static class BluetoothModuleHolder {
        private static final BluetoothModule instance = new BluetoothModule();
    }
    //블루투스 모듈 객체(변수) 생성자


    public static BluetoothModule getInstance() {
        //Log.v("BLELOG","BluetootgetInstance");
        return BluetoothModuleHolder.instance;
    }
    //블루투스 모듈 객체(변수) 반환

    public boolean isConnected() {
        //Log.v("BLELOG","isConnected()");
        return (bluetoothGatt != null && bluetoothGatt.connect());
    }
    //연결됏는지 확인
    //위 조건이 트루인지 펄스인지

    public BluetoothGatt getGatt() {
        return bluetoothGatt;
    }
    //블루투스 가트객체 얻어옴

    public void setGatt(BluetoothGatt bluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt;
    }



    public void disconnect() {
        if (isConnected()) {
            bluetoothGatt.disconnect();
        }
    }
    //연결 끊음
    //만약 연결됐으면 BT가트 객체.disconnect()

    public BluetoothGatt getGatt(String mac){
       BluetoothGatt gatt = GattList.get(mac);
        return gatt;
    }


    /**
     * 맥주소를 받아서 connect
     */
    public void gattConnect(String macAddress, BluetoothConnectImpl btConnectCallback, Context context) {
        Log.v("BLELOG","call by BluetoothModule . gettConnect() : gatt연결 메소드\n ");
        this.btConnectCallback = btConnectCallback;

        this.context = context;


        textv_state = connect_activity.getTextv_state();
        btn_service = connect_activity.getBtn_service();


        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        //Log.v("BLELOG","getRemoteDevice(MAC주소) : 지정된 Bluetooth 하드웨어 주소에 대한 BluetoothDevice 개체를 가져옴");
        final BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);

        textv_state.setText("연결 중... ");
/*        TimerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(bluetoothGatt != null){
                }
                else if(bluetoothGatt == null){
                    Log.v("cnnt","bluetoothGatt == null");
                }
            }
        },20000);*/
        try{
            //Log.v("BLELOG","device.connectGatt(context, true, gattCallback) : 호스팅하는 GATT 서버에 연결, BluetoothGatt 인스턴스를 반환 ");
            bluetoothGatt = device.connectGatt(context, true, gattCallback);
        }catch (IllegalArgumentException e){
            textv_state.setText("connect failed..");
        }

        GattList.put(device.getAddress(),bluetoothGatt);

    }

    //gatt연결 메소드
    //macAdress , btConnectCallback 필요

    //btManager 객체 생성 (getSystemService BLUETOOTH_SEVICE)
    //bt아답터 객체 생성
    //블루투스 디바이스 객체 = 블루투스 아답터.getRemoteDevice(MAC주소);
    //bt가트 = device.connectGatt(컨텍스트, true, gatt콜백);


    //getRemoteDevice(MAC주소)
        //지정된 Bluetooth 하드웨어 주소에 대한 BluetoothDevice 개체를 가져옴

    //connectGatt()
        //이 장치에서 호스팅하는 GATT 서버에 연결합니다.
        //
        //호출자는 GATT 클라이언트 역할을 합니다. 콜백은 연결 상태 및 추가 GATT 클라이언트 작업과 같은 호출자에게 결과를 제공하는 데 사용됩니다.
        //
        //메서드는 BluetoothGatt 인스턴스를 반환합니다.
        //
        //BluetoothGatt를 사용하여 GATT 클라이언트 작업을 수행할 수 있습니다.


    /**
     * 프로토콜 보내기 write 를 하고 ble장치로부터 값을 받으면 onCharacteristicChanged() 메소드가 호출 된다
     */
    public void sendProtocol(String protocol, BluetoothWriteImpl btWriteCallback) {
        if (isConnected()) {
            Log.v("BLELOG","sendProtocol() : 프로토콜을 보내는 메소드");
            this.btWriteCallback = btWriteCallback;
            protocol = "<" + protocol.toUpperCase() + ">";
            //writeGattCharacteristic.setValue(protocol);
            //bluetoothGatt.writeCharacteristic(writeGattCharacteristic);
        }
    }
    //프로토콜 보내는 메소드
    //만약 연결되었으면
    //전송 콜백에 파라메터 전송 콜백값 넣음
    //프로토콜 = "< 프로토콜대문자 >"
    //전송하면 writeCharicteristic 호출


    public BluetoothGattCallback getGattCallback() {
        return gattCallback;
    }

    /**
     * 블루투스 콜백
     */
    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        final Handler handler = new Handler(Looper.getMainLooper());

        /**
         * 연결상태가 변화 할때 마다 (연결, 끊김) 호출
         */
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.v("BLELOG","gattCallback => onConnectionStateChange() : 클라이언트가 원격에 연결/연결해제된 시기를 나타내는 콜백");

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.v("BLELOG","newState == BluetoothProfile.STATE_CONNECTED 새로운 연결이 발견됨. discoverService()호출 : 원격 장치에서 제공하는 서비스뿐만 아니라 해당 특성 및 설명자도 검색");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textv_state.setText("연결 됨.\n서비스 탐색 중...");
                    }
                });

                bluetoothGatt.discoverServices(); // onServicesDiscovered() 호출 (서비스 연결 위해 꼭 필요)

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textv_state.setText("연결 끊김.");
                        btn_service.setEnabled(false);
                    }
                });
            }
        }
        /* GATT 클라이언트가 원격에 연결/연결 해제된 시기를 나타내는 콜백
         * GATT 서버.
         *gatt : GATT 클라이언트
         *status : 연결 또는 연결 해제 작업의 상태
         *newStatus : 새 연결상태 */



        //원격 장치에서 제공하는 서비스뿐만 아니라 해당 특성 및 설명자도 검색합니다.
        //이것은 비동기식 작업입니다.
        // 서비스 검색이 완료되면 BluetoothGattCallback.onServicesDiscovered callback이 트리거됩니다.
        // 검색에 성공하면 getServices 기능을 사용하여 원격 서비스를 검색할 수 있습니다.
        //반환:
        //true, 원격 서비스 검색이 시작된 경우


        /**
         * 서비스 연결 후 ( Notification 설정 ) cf) setCharacteristicNotification 까지만 해도  Notification이 되지만 이 메소드의 콜백을 받지 못한다
         * (setCharacteristicNotification이 비동기로 완료되기 전에 통신을 한다면 에러가 난다) -> writeDescriptor가 완료 된 순간부터 통신이 가능하다
         */
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textv_state.setText("연결 됨.\n서비스 탐색완료. \nGatt 작업 중..");
                    btn_service.setEnabled(true);
                }
            });

            if (status == BluetoothGatt.GATT_SUCCESS) {
                setGatt(gatt);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textv_state.setText("연결 됨.\n서비스 탐색완료. \nGatt 작업 완료.");
                        btn_service.setEnabled(true);
                    }
                });
                }

                //BluetoothGattCharacteristic ch = gatt.getService(UUID_SERVICE).getCharacteristic(UUID_SERVICE);
                //Log.v("BLELOG", "ch : "+ch.toString());
                    //getService : 요청된 UUID가 원격 장치에서 지원되는 경우 BluetoothGattService를 반환합니다.
                    //이 기능을 사용하려면 지정된 디바이스에 대해 서비스 검색이 완료되어야 합니다.
                    //UUID로 식별된 동일한 서비스의 인스턴스가 여러 개 있는 경우 서비스의 첫 번째 인스턴스가 반환됩니다.
                    //Android가 필요합니다.매니페스트.permission.BLUETOOTH 사용 권한.

                //getCharicteristic : 이 서비스에서 제공하는 특성 목록에서 지정된 UUID의 특성을 반환합니다.
                        //이 기능은 getcharics에서 수동으로 반환한 목록을 열거하지 않고 특정 특성에 액세스할 수 있는 편의 기능입니다.
                        //원격 서비스가 동일한 UUID의 여러 특성을 제공하는 경우 지정된 UUID를 사용하는 특성의 첫 번째 인스턴스가 반환됩니다.

                //gatt.setCharacteristicNotification(ch, true);
                    //지정된 특성에 대한 알림/표시 사용 또는 사용 안 함
                    //
                    //특정 특성에 대해 알림이 활성화되면 원격 장치에서 지정된 특성이 변경되었음을 나타내는 경우 "BluetoothGattCallback.oncharicChanged" 콜백이 트리거됩니다.
                    //
                    //Android가 필요합니다.매니페스트.permission.BLUETOOTH 사용 권한.

                //BluetoothGattDescriptor descriptor = ch.getDescriptor(UUID_SERVICE);
                    //이 특성에 대한 설명자 목록에서 지정된 UUID를 가진 설명자를 반환합니다.
                    //
                    //반환:
                    //지정된 UUID를 가진 설명자를 찾을 수 없는 경우 GATT 설명자 개체 또는 null입니다.

                //descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    //이 설명자의 로컬 저장 값을 업데이트합니다.
                    //
                    //이 기능은 이 설명자의 로컬로 저장된 캐시된 값을 수정합니다. 값을 원격 장치로 보내려면 'BluetoothGatt.writeDescriptor'에 문의하여 값을 원격 장치로 전송합니다.
                    //
                    //매개 변수:
                    //value ? 이 설명자에 대한 새 값
                    //반환:
                    //true 로컬 저장 값이 설정된 경우 true이고 요청한 값을 로컬로 저장할 수 없는 경우 false입니다.



                //bluetoothGatt.writeDescriptor(descriptor);
                    //지정된 설명자의 값을 연결된 원격 장치에 기록합니다.
                    //
                    //'BluetoothGattCallback.onDescriptorWrite' 콜백이 트리거되어 쓰기 작업 결과를 보고합니다.
                    //
                    //매개 변수:
                    //descriptor ? 관련 원격 장치에 쓸 설명자
                    //반환:
                    //true, 쓰기 작업이 성공적으로 시작된 경우

                //BluetoothGattService service = bluetoothGatt.getService(UUID_SERVICE);
                    //요청된 UUID가 원격 장치에서 지원되는 경우 BluetoothGattService를 반환합니다.
                    //
                    //이 기능을 사용하려면 지정된 디바이스에 대해 서비스 검색이 완료되어야 합니다.
                    //
                    //UUID로 식별된 동일한 서비스의 인스턴스가 여러 개 있는 경우 서비스의 첫 번째 인스턴스가 반환됩니다.
                    /*매개 변수:
                    UUID – 요청된 서비스의 UUID
                    반환:
                    BluetoothGattService(지원되는 경우) 또는 요청된 서비스가 원격 장치에서 제공되지 않는 경우 null입니다.*/



                //writeGattCharacteristic = service.getCharacteristic(UUID_SERVICE);
                    //이 서비스에서 제공하는 특성 목록에서 지정된 UUID의 특성을 반환합니다.
                    //
                    //이 기능은 getcharics에서 수동으로 반환한 목록을 열거하지 않고 특정 특성에 액세스할 수 있는 편의 기능입니다.
                    //
                    //원격 서비스가 동일한 UUID의 여러 특성을 제공하는 경우 지정된 UUID를 사용하는 특성의 첫 번째 인스턴스가 반환됩니다.
                    //반환:
                    //GATT 특성 개체 또는 지정된 UUID의 특성을 찾을 수 없는 경우 null입니다.

                //SharedPreferences pref = Properties.getSharedPreferences(context);
                //pref.edit().putString(Properties.CONNECTED_BT_ADDRESS, gatt.getDevice().getAddress()).apply();
                //pref.edit().putString(Properties.CONNECTED_BT_NAME, gatt.getDevice().getName()).apply();

        }
        /* 원격 서비스, 특성 및 설명자 목록이 표시될 때 콜백 호출
         * 원격 장치의 경우 업데이트되었으며, 새로운 서비스가 검색되었습니다.
         * 여기서 BLE장치가 가진 특성 취득
         *gatt : GATT클라이언트 호출
         *status : 새 장치가 탐색된 경우 */


        @Override
        public void onCharacteristicRead(final BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            //Log.v("cnnt","onCharacteristicRead()");

            //Log.v("cnnt","bm Activity ch value : "+characteristic.getValue());

            value = HexUtil.formatHexString(characteristic.getValue(),true);

            final String ch_value_string = new String(characteristic.getValue());


            //Log.v("cnnt","BM Activity ch to string : "+ch_value_string);

            switch(witchActivity){
                case "Read":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.v("cnnt","Read");
                            TextView txtv_ch_value = read_fragment.getTxtv_ch_value();
                            txtv_ch_value.setText(value);
                            TextView txtv_ch_value_string = read_fragment.getTxtv_ch_value_string();
                            txtv_ch_value_string.setText(ch_value_string);
                        }
                    });
                    break;

                case "Indicate":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.v("cnnt","Indicate");
                            TextView txtv_ch_value = indicate_fragment.getTxtv_ch_value();
                            txtv_ch_value.setText(value);
                            TextView txtv_ch_value_string = indicate_fragment.getTxtv_ch_value_string();
                            txtv_ch_value_string.setText(ch_value_string);
                        }
                    });
                    break;

                case "Notify":
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.v("cnnt","Notify");
                            TextView txtv_ch_value = notify_fragment.getTxtv_ch_value();
                            txtv_ch_value.setText(value);
                            TextView txtv_ch_value_string = notify_fragment.getTxtv_ch_value_string();
                            txtv_ch_value_string.setText(ch_value_string);
                        }
                    });
                    break;

            }

        }
        /* 특성 읽기 작업의 결과를 보고하는 콜백
         *gatt : GATT클라이언트 호출
         *charicteristic : 연결된 원격장치에서 읽은 특성
         *status : 읽기 작업이 완료된 경우 */

        @Override
        public void onDescriptorWrite(final BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.v("BLELOG","onDescriptorWrite() : 설명자 쓰기 작업의 결과를 나타내는 콜백");
                    btConnectCallback.onSuccessConnect(gatt.getDevice()); // 통신 준비 완료
                }
            });
        }
        /* 설명자 전송 작업의 결과를 나타내는 콜백
         *gatt : GATT클라이언트 호출
         *descriptor : 연결된 원격 장치에 쓰였던 설명자.
         *status : 다음과 같은경우(BluetoothGatt.GATT_SUCESS)의 쓰기작업 결과 */


        /**
         * 가장 중요한 메소드, ble 기기의 값을 받아온다
         */
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.v("BLELOG","onCharicteristicchanged() : 원격 특성 알림의 결과로 콜백이 트리거 됨, BLE 기기의 값을 받아올때 호출");


                    byte[] messageBytes = characteristic.getValue();
                    String messageString = null;


                    try {
                        messageString = new String(messageBytes, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "Unable to convert message bytes to string");
                    }
                    Log.d("RESA","Received message : " + messageString);


                    String value = characteristic.getStringValue(1);
                    Log.v("RESA","characteristic.getStringValue(1) : "+value);


                    value = value.replaceAll(" ", "");
                    value = value.substring(0, value.length() - 1);
                    try {
                        Log.d(TAG, "run: " + value);
                        btWriteCallback.onSuccessWrite(0, value);
                    } catch (IOException e) {
                        e.printStackTrace();
                        btWriteCallback.onFailed(e);
                    }
                }
            });
        }
        /*원격 특성 알림의 결과로 콜백이 트리거됨
         *gatt : 특성이 연관된 GATT 클라이언트
         *characteristic : 원격지의 결과로 업데이트된 특성 */
    };






    public interface BluetoothConnectImpl {
        void onSuccessConnect(BluetoothDevice device);

        void onFailed();
    }

    public interface BluetoothWriteImpl {
        void onSuccessWrite(int status, String data) throws IOException;

        void onFailed(Exception e);
    }

}
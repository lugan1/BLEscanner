package techtown.org.blescanner.HTTP_REST;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Login_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Login_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Response;
import techtown.org.blescanner.R;
import techtown.org.blescanner.HTTP_REST.Util.Utility;


public class Login_Fragment extends Fragment {

    Retrofit retrofit;
    ApiService apiService;

    Login_Post login_post;
    Login_Response login_response;
    Session_Renew_Post session_renew_post;

    private TextView textView;
    private ViewGroup rootview;
    private Button btn_create, btn_login;

    EditText editText_lgid;
    EditText editText_lgpass;

    FragmentTransaction fragmentTransaction;

    Create_Account_Fragment create_account_fragment;
    Success_Fragment success_fragment;

    private long mLastClickTime = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootview = (ViewGroup)inflater.inflate(R.layout.http_login_fragment,container,false);

        btn_create = (Button)rootview.findViewById(R.id.http_btn_create);
        btn_login = (Button)rootview.findViewById(R.id.http_btn_login);

        editText_lgid = (EditText)rootview.findViewById(R.id.http_editText_lgid);
        editText_lgpass = (EditText)rootview.findViewById(R.id.http_editText_lgpass);

        login_post = new Login_Post();

        Utility utility = new Utility();

        btn_login.setOnClickListener(new Utility.OnSingleClickListener(){
            @Override
            public void onSingleClick(View v) {

                getEditText();
                if(editText_lgid.getText().toString().equals("")  || editText_lgpass.getText().toString().equals("")){
                    Alert_Dialog_show();
                }
                else{
                    setDefault_value();
                    Utility utility = new Utility();
                    Utility.Time time = utility.getTime();

                    session_renew_post = new Session_Renew_Post();
                    session_renew_post.setGmtCode(time.getGmtCode());
                    session_renew_post.setRequestDateTime(time.getRequestDateTime());
                    session_renew_post.setSystemTime(time.getSystemTime());

                    login_post.setSystemTime(time.getSystemTime());
                    login_post.setGmtCode(time.getGmtCode());
                    login_post.setRequestDateTime(time.getRequestDateTime());

                    final Mobicare_API_Method mobicare_api_method = new Mobicare_API_Method();

                    mobicare_api_method.Login(login_post, new Callback<Login_Response>() {

                        @Override
                        public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                            try {
                                if(response.body() != null){
                                    //HTTP 통신 테스트

                                    String result = mobicare_api_method.API_errorTest(response);
                                    //API 에러 테스트
                                    if(result == "success"){
                                        session_RENEW(mobicare_api_method);
                                        login_response = response.body();
                                        Log.v("SHOWSTRING","로그인 성공");
                                        Log.v("SHOWSTRING",response.body().string());
                                        onSuccessFragment();
                                        //Toast.makeText(getActivity(),"로그인 성공",Toast.LENGTH_SHORT).show();
                                    }
                                    else if(result != "success"){
                                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }

                                else if(response.body() == null){
                                    //HTTP 통신 실패
                                    Toast.makeText(getActivity(),"http 통신오류 : "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                                    Log.v("cnnt","result == null");
                                    Log.v("cnnt",response.errorBody().string());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<Login_Response> call, Throwable t) {
                            Log.v("cnnt","onFailure");
                        }
                    });
                }

            }
        });

        btn_create.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                onCreateFragment();
            }
        });

        return rootview;
    }

    void Alert_Dialog_show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림창");
        builder.setMessage("입력 필드가 비어있습니다.!!!");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    public void getEditText(){
        login_post.setId(editText_lgid.getText().toString());
        login_post.setPassword(editText_lgpass.getText().toString());
    }

    public void setDefault_value(){
        login_post.setDeviceKind(2);
        login_post.setEncryption(0);
    }

    public void onSuccessFragment(){
        success_fragment = new Success_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",login_response.getData().getUserAccount().getId());
        success_fragment.setArguments(bundle);

        fragmentTransaction = getFragmentManager().beginTransaction();

        Fragment Login_Fragment = getFragmentManager().findFragmentByTag("rest");
        fragmentTransaction.add(R.id.frag_container2,success_fragment,"success");
        fragmentTransaction.addToBackStack("success_stack");
        fragmentTransaction.hide(Login_Fragment);
        fragmentTransaction.show(success_fragment);
        fragmentTransaction.commit();
    }


    public void onCreateFragment(){
        create_account_fragment = new Create_Account_Fragment();

        fragmentTransaction = getFragmentManager().beginTransaction();

        Fragment Login_Fragment = getFragmentManager().findFragmentByTag("rest");
        fragmentTransaction.add(R.id.frag_container2,create_account_fragment,"creacc");
        fragmentTransaction.addToBackStack("creacc_stack");
        fragmentTransaction.hide(Login_Fragment);
        fragmentTransaction.show(create_account_fragment);
        fragmentTransaction.commit();
    }

    public void session_RENEW(Mobicare_API_Method mobicare_api_method){
        mobicare_api_method.Session_Renew(session_renew_post, new Callback<Session_Renew_Response>() {
            @Override
            public void onResponse(Call<Session_Renew_Response> call, Response<Session_Renew_Response> response) {
                try {
                    if(response.body() != null){
                        //HTTP 통신 테스트
                        //API 에러 테스트
                        Session_Renew_Response session_renew_response = response.body();
                        String errorMessage = "";
                        if(session_renew_response.getResult() == false){
                            Log.v("cnnt","로그인 실패");
                            Log.v("cnnt","error Code : "+ session_renew_response.getError());
                            Log.v("cnnt","message : "+ session_renew_response.getMessage());


                            switch (session_renew_response.getError()){
                                case 3 :
                                    errorMessage = "아이디가 틀렸습니다.";
                                    break;
                                case 257 :
                                    errorMessage = "비밀번호가 틀렸습니다.";
                                    break;
                                case 6 :
                                    errorMessage = "비밀번호 틀림 횟수 초과. 계정이 잠겨졌습니다.";
                                    break;
                                default :
                                    errorMessage = "알수없는 오류. error code : "+ session_renew_response.getError()+" message : "+ session_renew_response.getMessage();
                                    break;
                            }
                            Toast.makeText(getActivity(),""+errorMessage,Toast.LENGTH_LONG).show();
                        }
                        else if(session_renew_response.getResult() == true){
                            Log.v("SHOWSTRING","세션 갱신 성공");
                            Log.v("SHOWSTRING",session_renew_response.string());
                        }

                    }
                    else if(response.body() == null){
                        //HTTP 통신 실패
                        Toast.makeText(getActivity(),"http 통신오류 : "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                        Log.v("cnnt","result == null");
                        Log.v("cnnt",response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Session_Renew_Response> call, Throwable t) {

            }
        });
    }

}


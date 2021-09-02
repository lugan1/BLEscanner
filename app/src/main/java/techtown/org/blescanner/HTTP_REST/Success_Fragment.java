package techtown.org.blescanner.HTTP_REST;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Logout_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Logout_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Response;
import techtown.org.blescanner.HTTP_REST.Util.Utility;
import techtown.org.blescanner.R;

public class Success_Fragment extends Fragment {

    Spinner spinner;

    Button btn_logout;

    Logout_Post logout_post;
    Session_Renew_Post session_renew_post;

    TextView txtvid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.http_success_fragment, container, false);

        Bundle bundle = getArguments();

        spinner = (Spinner)rootview.findViewById(R.id.http_spiner);
        btn_logout = (Button)rootview.findViewById(R.id.http_btn_logout);
        txtvid = (TextView)rootview.findViewById(R.id.http_txtv_ID);

        txtvid.setText(bundle.getString("id"));

        //input array data
        final ArrayList<String> list = new ArrayList<>();
        list.add("환자 측정 정보 조회");
        list.add("ECG 데이터 조회");
        list.add("IMU 데이터 조회");
        list.add("HeartRate 데이터 조회");

        //using ArrayAdapter
        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility utility = new Utility();

                Utility.Time time = utility.getTime();

                logout_post = new Logout_Post();
                logout_post.setGmtCode(time.getGmtCode());
                logout_post.setSystemTime(time.getSystemTime());
                logout_post.setRequestDateTime(time.getRequestDateTime());

                session_renew_post = new Session_Renew_Post();
                session_renew_post.setGmtCode(time.getGmtCode());
                session_renew_post.setRequestDateTime(time.getRequestDateTime());
                session_renew_post.setSystemTime(time.getSystemTime());


                final Mobicare_API_Method mobicare_api_method = new Mobicare_API_Method();
                mobicare_api_method.Logout(logout_post, new retrofit2.Callback<Logout_Response>() {
                    @Override
                    public void onResponse(Call<Logout_Response> call, Response<Logout_Response> response) {
                        String errorMessage = "";
                        if(response.body() != null){
                            Logout_Response logout_response = response.body();
                            if(logout_response.getResult() == false){
                                Log.v("cnnt","로그아웃 실패");
                                Log.v("cnnt","error Code : "+logout_response.getError());
                                switch (logout_response.getError()){
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
                                        errorMessage = "오류. error code : "+logout_response.getError()+" message : "+logout_response.getMessage();
                                        break;
                                }
                                Toast.makeText(getActivity(),""+errorMessage,Toast.LENGTH_SHORT).show();
                            }
                            else if(logout_response.getResult() == true){
                                Log.v("cnnt","로그아웃 성공");
                                Log.v("cnnt",logout_response.string());
                                getFragmentManager().popBackStack();
                            }
                        } else if (response.body() == null){
                            //HTTP 통신 실패
                            try {
                                Toast.makeText(getActivity(),"http 통신오류 : "+response.errorBody().string(),Toast.LENGTH_SHORT).show();
                                Log.v("cnnt","result == null");
                                Log.v("cnnt",response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Logout_Response> call, Throwable t) {
                        Toast.makeText(getActivity(),"onFailur",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return rootview;
    }

    public void session_RENEW(Mobicare_API_Method mobicare_api_method){
        mobicare_api_method.Session_Renew(session_renew_post, new retrofit2.Callback<Session_Renew_Response>() {
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

                            Log.v("cnnt",errorMessage);
                        }
                        else if(session_renew_response.getResult() == true){
                            errorMessage = "success";
                            Log.v("SHOWSTRING","세션 갱신 성공");
                            Log.v("SHOWSTRING",session_renew_response.string());
                        }

                    }
                    else if(response.body() == null){
                        //HTTP 통신 실패
                        Toast.makeText(getActivity(),"http 통신오류 : "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                        //Log.v("cnnt","result == null");
                        //Log.v("cnnt",response.errorBody().string());
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

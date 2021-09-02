package techtown.org.blescanner.HTTP_REST;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Create_Account_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Create_Account_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Login_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Login_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Logout_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Logout_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Response;

public class Mobicare_API_Method {
    Retrofit retrofit;
    ApiService apiService;
    JSONObject jsonObject;


    public Mobicare_API_Method() {

    }

    public void init(){
        retrofit = new Retrofit.Builder().baseUrl(ApiService.mobiTest_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        //request를 전달할 url을 넣어서 생성

        apiService = retrofit.create(ApiService.class);
        //get, post와 같은 서비스를 이용하기 위해서 인터페이스 생성

        jsonObject = new JSONObject();
    }

    public void CreateAccount(Create_Account_Post create_account_post, Callback<Create_Account_Response> callback) {

        //Log.v("cnnt","Create_Account_Response");

        init();

        try {
            jsonObject.put("requestUserId", create_account_post.getRequestUserId());
            jsonObject.put("id", create_account_post.getId());
            jsonObject.put("password", create_account_post.getPassword());
            jsonObject.put("userLevel", create_account_post.getUserLevel());
            jsonObject.put("organizationId", create_account_post.getOrganizationId());
            jsonObject.put("userCode", create_account_post.getUserCode());
            jsonObject.put("department", create_account_post.getDepartment());
            jsonObject.put("firstName", create_account_post.getFirstName());
            jsonObject.put("lastName", create_account_post.getLastName());
            jsonObject.put("gender", create_account_post.getGender());
            jsonObject.put("birthday", create_account_post.getBirthday());
            jsonObject.put("phoneNumber", create_account_post.getPhoneNumber());
            jsonObject.put("email", create_account_post.getEmail());
            jsonObject.put("address", create_account_post.getAddress());
            jsonObject.put("encryption", create_account_post.getEncryption());
            jsonObject.put("systemTime", create_account_post.getSystemTime());
            jsonObject.put("gmtCode", create_account_post.getGmtCode());
            jsonObject.put("requestDateTime", create_account_post.getRequestDateTime());
            jsonObject.put("deviceKind", create_account_post.getDeviceKind());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<Create_Account_Response> BodyObj = apiService.CreateAccount(jsonObject.toString());

        BodyObj.enqueue(callback);
    }

    public void Login(Login_Post login_post, Callback<Login_Response> callback){

        init();

        try {
            jsonObject.put("id", login_post.getId());
            jsonObject.put("password", login_post.getPassword());
            jsonObject.put("encryption", 0);
            jsonObject.put("systemTime", login_post.getSystemTime());
            jsonObject.put("requestDateTime", login_post.getRequestDateTime());
            jsonObject.put("gmtCode", login_post.getGmtCode());
            jsonObject.put("deviceKind", 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.v("cnnt",jsonObject.toString());

        Call<Login_Response> Bodyobj = apiService.Login(jsonObject.toString());

        Bodyobj.enqueue(callback);
    }

    public String API_errorTest(Response<Login_Response> response){
        String errorMessage = "";
        if(response.body() != null){
            Login_Response login_response = response.body();
            if(login_response.getResult() == false){
                Log.v("cnnt","로그인 실패");
                Log.v("cnnt","error Code : "+login_response.getError());
                Log.v("cnnt","message : "+login_response.getMessage());

                switch (login_response.getError()){
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
                        errorMessage = "알수없는 오류. error code : "+login_response.getError()+" message : "+login_response.getMessage();
                        break;
                }
            }
            else if(login_response.getResult() == true){
                errorMessage = "success";
            }
        }
        return  errorMessage;
    }

    public void Logout(Logout_Post logout_post, Callback<Logout_Response> callback){

        init();

        try {
            jsonObject.put("systemTime", logout_post.getSystemTime());
            jsonObject.put("requestDateTime", logout_post.getRequestDateTime());
            jsonObject.put("gmtCode", logout_post.getGmtCode());
            jsonObject.put("deviceKind", 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.v("cnnt",jsonObject.toString());

        Call<Logout_Response> Bodyobj = apiService.Logout(jsonObject.toString());

        Bodyobj.enqueue(callback);
    }

    public void Session_Renew(Session_Renew_Post session_renew_post, Callback<Session_Renew_Response> callback){

        init();

        try {
            jsonObject.put("systemTime", session_renew_post.getSystemTime());
            jsonObject.put("requestDateTime", session_renew_post.getRequestDateTime());
            jsonObject.put("gmtCode", session_renew_post.getGmtCode());
            jsonObject.put("deviceKind", 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.v("cnnt",jsonObject.toString());

        Call<Session_Renew_Response> Bodyobj = apiService.Session_Renew(jsonObject.toString());

        Bodyobj.enqueue(callback);
    }



}

package techtown.org.blescanner.HTTP_REST;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Create_Account_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Login_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Logout_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Select_Organization_Response;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Session_Renew_Response;

public interface ApiService {
    // API Gateway Stage URL
    public static final String API_URL = "http://jsonplaceholder.typicode.com/";

    public static final String mobiTest_URL = "http://13.125.78.75:8080/mobiCARE/cardio/";

    @FormUrlEncoded
    @POST("comments")
    Call<ResponseBody>getPostCommentsStr(@Field("postId") String postId);
    //post로 String 보낼때는 Query 가 아닌 Field로 바꿔야함. , 그리고 @FromUrlEncoded 추가해야됨.

    @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Organization/SelectOrganization")
    Call<Select_Organization_Response>SelectOrganization(@Body String body);


    @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Organization/SelectOrganizationByUser")
    Call<ResponseBody>SelectOrganizationByUser(@Body String body);


    @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Manager/CreateAccount")
    Call<Create_Account_Response>CreateAccount(@Body String body);


   @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Account/Login")
    Call<Login_Response>Login(@Body String body);


    @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Account/Logout")
    Call<Logout_Response>Logout(@Body String body);


    @Headers( "Content-Type: application/json; charset=utf8"  )
    @POST("Account/SessionRenew")
    Call<Session_Renew_Response>Session_Renew(@Body String body);


}


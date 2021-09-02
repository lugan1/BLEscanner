package techtown.org.blescanner.HTTP_REST;

public class Test_mobi {

    /* public void CreateAccount(){
        retrofit = new Retrofit.Builder().baseUrl(ApiService.mobiTest_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        //request를 전달할 url을 넣어서 생성

        apiService = retrofit.create(ApiService.class);
        //get, post와 같은 서비스를 이용하기 위해서 인터페이스 생성

        try{
            JSONObject paramObject = new JSONObject();
            paramObject.put("requestUserId", 1);
            paramObject.put("id", "lugan");
            paramObject.put("password","asdf1234");
            paramObject.put("userLevel",4);
            paramObject.put("organizationId",1);
            paramObject.put("userCode","");
            paramObject.put("department","seers");
            paramObject.put("firstName","lee");
            paramObject.put("lastName","hoogy");
            paramObject.put("gender",1);
            paramObject.put("birthday","1992-09-29");
            paramObject.put("phoneNumber","01029883940");
            paramObject.put("email","lugan@naver.com");
            paramObject.put("address","경기도 성남시 분당구 정자동");
            paramObject.put("encryption",0);
            paramObject.put("systemTime", create_account_post.getSystemTime());
            paramObject.put("gmtCode", create_account_post.getGmtCode());
            paramObject.put("requestDateTime", create_account_post.getRequestDateTime());
            paramObject.put("deviceKind",2);

            Call<ResponseBody> comment = apiService.CreateAccount(paramObject.toString());

            comment.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body() != null){
                        try {
                            Log.v("cnnt","response.body().string : "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ResponseBody responseBody = response.body();
                    }
                    else if(response.body() == null){
                        if (response.code() == 400 ) {
                            Log.v("cnnt","response.body() == null");
                            Log.d("cnnt", "Response - code : " + response.code());
                            try {
                                if (response.errorBody() != null)
                                    Log.v("cnnt","response errorBody() : "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        try {
                            Log.v("cnnt","response.body() == null");
                            Log.d("cnnt", "Response - code : " + response.code());
                            Log.v("cnnt","response errorBody() : "+response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/


    /* public void SelectOrganization(){
        retrofit = new Retrofit.Builder().baseUrl(ApiService.mobiTest_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        //request를 전달할 url을 넣어서 생성

        apiService = retrofit.create(ApiService.class);
        //get, post와 같은 서비스를 이용하기 위해서 인터페이스 생성

        try{
            JSONObject paramObject = new JSONObject();
            paramObject.put("countryCode", null);
            paramObject.put("countryName", null);
            paramObject.put("systemTime",87182321);
            paramObject.put("gmtCode","GMT+0900");
            paramObject.put("requestDateTime","2018-08-24 16:04:24");
            paramObject.put("deviceKind",3);

            Call<Select_Organization_Response> comment = apiService.SelectOrganization(paramObject.toString());

            comment.enqueue(new Callback<Select_Organization_Response>() {
                @Override
                public void onResponse(Call<Select_Organization_Response> call, Response<Select_Organization_Response> response) {
                    if(response.body() != null){

                        Log.v("cnnt","response.body() != null ");

                        Select_Organization_Response responseBody = response.body();


                        Log.v("cnnt","result : "+responseBody.getResult());
                        Log.v("cnnt","extra : "+responseBody.getExtra());
                        Log.v("cnnt","systemTime : "+responseBody.getSystemTime());
                        Log.v("cnnt","error : "+responseBody.getError());

                        List<Select_Organization_Response.data.organizationList> organizationLists = responseBody.getData().getOrganizationLists();

                        Log.v("cnnt","data : ");

                        for(int i=0; i<responseBody.getData().getOrganizationLists().size(); i++){
                            Log.v("cnnt","oraganization["+i+"] { ");
                            Log.v("cnnt","organizationId : "+organizationLists.get(i).getOrganizationId());

                            Log.v("cnnt","organizationCode : "+organizationLists.get(i).getOrganizationCode());
                            Log.v("cnnt","organizationName : "+organizationLists.get(i).getOrganizationName());
                            Log.v("cnnt","countryCode : "+organizationLists.get(i).getCountryCode());
                            Log.v("cnnt","countryName : "+organizationLists.get(i).getCountryName());
                            Log.v("cnnt","state : "+organizationLists.get(i).getState());
                            Log.v("cnnt","city : "+organizationLists.get(i).getCity());
                            Log.v("cnnt","address : "+organizationLists.get(i).getAddress());

                            Log.v("cnnt","phoneNumber : "+organizationLists.get(i).getPhoneNumber());
                            Log.v("cnnt","countractLevel : "+organizationLists.get(i).getContractLevel());
                            Log.v("cnnt","Deactivate : "+organizationLists.get(i).getDeactivate());
                            Log.v("cnnt","Administator : "+organizationLists.get(i).getAdministrator());
                            Log.v("cnnt","ConstarctStartDate : "+organizationLists.get(i).getContractStartDate());
                            Log.v("cnnt","ConteactExpirationDate : "+organizationLists.get(i).getContractExpirationDate());

                            Log.v("cnnt","UpdateDateTime : "+organizationLists.get(i).getUpdateDateTime());
                            Log.v("cnnt","DeactivateDateTime : "+organizationLists.get(i).getDeactivateDateTime());
                            Log.v("cnnt","Etc : "+organizationLists.get(i).getEtc());
                            Log.v("cnnt","}");

                        }


                    }
                    else if(response.body() == null){
                        if (response.code() == 400 ) {
                            Log.v("cnnt","response.body() == null");
                            Log.d("cnnt", "Response - code : " + response.code());
                            try {
                                if (response.errorBody() != null)
                                    Log.v("cnnt","response errorBody() : "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Select_Organization_Response> call, Throwable t) {

                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/


    /*Log.v("cnnt","create_account_response != null");

                            Log.v("cnnt","result : "+create_account_response.getResult());
                            Log.v("cnnt","extra : "+create_account_response.getExtra());
                            Log.v("cnnt","systemTime : "+create_account_response.getSystemTime());
                            Log.v("cnnt","error : "+create_account_response.getError());
                            Log.v("cnnt","message : "+create_account_response.getMessage());
                            Log.v("cnnt","data :{ ");

                            Create_Account_Response.data.userAccount userAccount = create_account_response.getdata().getUserAccount();

                            Log.v("cnnt","userAccount :{ ");
                            Log.v("cnnt","userId : "+userAccount.getUserId());
                            Log.v("cnnt","id : "+userAccount.getId());
                            Log.v("cnnt","password : "+userAccount.getPassword());
                            Log.v("cnnt","organizationId : "+userAccount.getOrganizationId());
                            Log.v("cnnt","userCode : "+userAccount.getUserCode());
                            Log.v("cnnt","department : "+userAccount.getDepartment());
                            Log.v("cnnt","userLevel : "+userAccount.getUserLevel());
                            Log.v("cnnt","firstName : "+userAccount.getFirstName());
                            Log.v("cnnt","lastName : "+userAccount.getLastName());
                            Log.v("cnnt","gender : "+userAccount.getGender());
                            Log.v("cnnt","birthday : "+userAccount.getBirthday());
                            Log.v("cnnt","phoneNumber : "+userAccount.getPhoneNumber());
                            Log.v("cnnt","email : "+userAccount.getEmail());
                            Log.v("cnnt","address : "+userAccount.getAddress());
                            Log.v("cnnt","deviceKind : "+userAccount.getDeviceKind());
                            Log.v("cnnt","encryption : "+userAccount.getEncryption());
                            Log.v("cnnt","status : "+userAccount.getStatus());
                            Log.v("cnnt","deactivate : "+userAccount.getDeactivate());
                            Log.v("cnnt","gmtCode : "+userAccount.getGmtCode());
                            Log.v("cnnt","joinDateTime : "+userAccount.getJoindateTime());
                            Log.v("cnnt","updatedateTime : "+userAccount.getUpdateDateTime());
                            Log.v("cnnt","deactivateDateTime : "+userAccount.getDeactivateDateTime());
                            Log.v("cnnt","}.");


                            Create_Account_Response.data.organization organization = create_account_response.getdata().getOrganization();

                            Log.v("cnnt","organization :{");
                            Log.v("cnnt","organizationId : "+organization.getOrganizationId());
                            Log.v("cnnt","organizationCode : "+organization.getOrganiationCode());
                            Log.v("cnnt","organizationName : "+organization.getOrganizationName());
                            Log.v("cnnt","countryCode : "+organization.getCountryCode());
                            Log.v("cnnt","countryName : "+organization.getCountryName());
                            Log.v("cnnt","state : "+organization.getState());
                            Log.v("cnnt","city : "+organization.getCity());
                            Log.v("cnnt","address : "+organization.getAddress());
                            Log.v("cnnt","phoneNumber : "+organization.getPhonenumber());
                            Log.v("cnnt","contractLevel : "+organization.getContractLevel());
                            Log.v("cnnt","deactivate : "+organization.getDeactivate());
                            Log.v("cnnt","administrator : "+organization.getAdministrator());
                            Log.v("cnnt","contractStartdate : "+organization.getContractStartDate());
                            Log.v("cnnt","contractExpirationDate : "+organization.getContractExpirationDate());
                            Log.v("cnnt","updateDateTime : "+organization.getUpdateDateTime());
                            Log.v("cnnt","deactivateDateTime : "+organization.getDeactivateDateTime());
                            Log.v("cnnt","etc : "+organization.getEtc());*/













    /*
    public void SelectOrganization(){
        retrofit = new Retrofit.Builder().baseUrl(ApiService.mobiTest_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        //request를 전달할 url을 넣어서 생성

        apiService = retrofit.create(ApiService.class);
        //get, post와 같은 서비스를 이용하기 위해서 인터페이스 생성

        try{
            JSONObject paramObject = new JSONObject();
            paramObject.put("countryCode", null);
            paramObject.put("countryName", null);
            paramObject.put("systemTime",87182321);
            paramObject.put("gmtCode","GMT+0900");
            paramObject.put("requestDateTime","2018-08-24 16:04:24");
            paramObject.put("deviceKind",3);

            Call<ResponseBody> comment = apiService.SelectOrganization(paramObject.toString());

            comment.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body() != null){
                        try {
                            Log.v("cnnt","response.body().string : "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ResponseBody responseBody = response.body();
                    }
                    else if(response.body() == null){
                        if (response.code() == 400 ) {
                            Log.v("cnnt","response.body() == null");
                            Log.d("cnnt", "Response - code : " + response.code());
                            try {
                                if (response.errorBody() != null)
                                    Log.v("cnnt","response errorBody() : "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
*/


/*
    public void SelectOrganizationByUser(){
        retrofit = new Retrofit.Builder().baseUrl(ApiService.mobiTest_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        //request를 전달할 url을 넣어서 생성

        apiService = retrofit.create(ApiService.class);
        //get, post와 같은 서비스를 이용하기 위해서 인터페이스 생성

        try{
            JSONObject paramObject = new JSONObject();
            paramObject.put("userId", 1);
            paramObject.put("systemTime", 87182321);
            paramObject.put("gmtCode","GMT+0900");
            paramObject.put("requestDateTime","2018-08-24 16:04:24");
            paramObject.put("deviceKind",3);

            Call<ResponseBody> comment = apiService.SelectOrganizationByUser(paramObject.toString());

            comment.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body() != null){
                        try {
                            Log.v("cnnt","response.body().string : "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ResponseBody responseBody = response.body();
                    }
                    else if(response.body() == null){
                        if (response.code() == 400 ) {
                            Log.v("cnnt","response.body() == null");
                            Log.d("cnnt", "Response - code : " + response.code());
                            try {
                                if (response.errorBody() != null)
                                    Log.v("cnnt","response errorBody() : "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
*/


}

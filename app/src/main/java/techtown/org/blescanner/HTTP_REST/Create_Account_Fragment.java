package techtown.org.blescanner.HTTP_REST;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Create_Account_Post;
import techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object.Create_Account_Response;
import techtown.org.blescanner.R;
import techtown.org.blescanner.HTTP_REST.Util.Utility;

public class Create_Account_Fragment extends Fragment {
    Create_Account_Post create_account_post;

    EditText edit_id, edit_pass, edit_department, edit_firstname, edit_lastname, edit_birthday, edit_phonenumber, edit_email, edit_address;

    RadioGroup rdioGroup_userlevel, rdioGroup_gender;

    Button btn_accept, btn_cancel;

    String  gmtCode, requestDateTime;
    long systemTime;

    String id, password, userCode, department, firstName, lastName, birthday, phoneNumber, email, address;


    int requestUserId, organizationId, encryption, devicekind, userlevel, gender;
    //userCode는 null로 함

    Retrofit retrofit;
    ApiService apiService;

    Mobicare_API_Method mobicare_api_method;

    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.http_createacc_fragment, container, false);

        create_account_post = new Create_Account_Post();

        edit_id = (EditText)rootview.findViewById(R.id.http_edit_ID);
        edit_pass = (EditText)rootview.findViewById(R.id.http_edit_password);
        edit_department = (EditText)rootview.findViewById(R.id.http_edit_deaprtment);
        edit_firstname = (EditText)rootview.findViewById(R.id.http_edit_firstname);
        edit_lastname = (EditText)rootview.findViewById(R.id.http_edit_lastname);
        edit_birthday = (EditText)rootview.findViewById(R.id.http_edit_birthday);
        edit_phonenumber = (EditText)rootview.findViewById(R.id.http_edit_phonenumber);
        edit_email = (EditText)rootview.findViewById(R.id.http_edit_email);
        edit_address = (EditText)rootview.findViewById(R.id.http_edit_address);

        rdioGroup_userlevel = (RadioGroup)rootview.findViewById(R.id.radioGroup_userlevel);
        rdioGroup_gender = (RadioGroup)rootview.findViewById(R.id.radioGroup_gender);


        btn_accept = (Button)rootview.findViewById(R.id.http_btn_accept);
        btn_cancel = (Button)rootview.findViewById(R.id.http_btn_cancel);

        mobicare_api_method = new Mobicare_API_Method();

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_POST_DIALOG();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return rootview;
    }

    public void Create_POST_DIALOG()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("전송 확인");
        builder.setMessage("전송(POST) 하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Utility utility = new Utility();
                        Utility.Time time = utility.getTime();
                        witchRadioButton();
                        getTexteditString();
                        Variable_defaultSetting();

                        create_account_post.setSystemTime(time.getSystemTime());
                        create_account_post.setGmtCode(time.getGmtCode());
                        create_account_post.setRequestDateTime(time.getRequestDateTime());

                        Mobicare_API_Method mobicare_api_method = new Mobicare_API_Method();
                        mobicare_api_method.CreateAccount(create_account_post, new Callback<Create_Account_Response>() {
                            @Override
                            public void onResponse(Call<Create_Account_Response> call, Response<Create_Account_Response> response) {

                                Log.v("cnnt","onResponse");

                                Create_Account_Response create_account_response = response.body();

                                if(response.body() == null){
                                    Log.v("cnnt","response.body == null");
                                    Log.v("cnnt",""+response.errorBody());
                                }

                                if(create_account_response != null){
                                    Log.v("SHOWSTRING","회원가입 성공");
                                    Log.v("SHOWSTRING",create_account_response.string());
                                    Create_Succes_Dialog_show();
                                    getFragmentManager().popBackStack();
                                }

                            }
                            @Override
                            public void onFailure(Call<Create_Account_Response> call, Throwable t) {
                            }
                        });
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    void Create_Succes_Dialog_show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("가입 성공");
        builder.setMessage("회원가입에 성공했습니다.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }


    public void witchRadioButton(){
        int radio_userlevel_id = rdioGroup_userlevel.getCheckedRadioButtonId();
        switch (radio_userlevel_id){
            case R.id.radioButton_pationt :
                userlevel = 1;
                create_account_post.setUserLevel(1);
                break;

            case R.id.radioButton_reviewer :
                userlevel = 2;
                create_account_post.setUserLevel(2);
                break;

            case R.id.radioButton_doctor :
                userlevel = 3;
                create_account_post.setUserLevel(3);
                break;

            case R.id.radioButton_manager :
                userlevel = 4;
                create_account_post.setUserLevel(4);
                break;
        }

        int radio_gender_id = rdioGroup_gender.getCheckedRadioButtonId();
        switch (radio_gender_id){
            case R.id.radioButton_man :
                gender = 1;
                create_account_post.setGender(1);
                break;

            case R.id.radiobutton_women :
                gender = 2;
                create_account_post.setGender(2);
                break;
        }
    }

    public void getTexteditString(){
        id = edit_id.getText().toString();
        create_account_post.setId(id);

        password = edit_pass.getText().toString();
        create_account_post.setPassword(password);

        department = edit_department.getText().toString();
        create_account_post.setDepartment(department);

        firstName = edit_firstname.getText().toString();
        create_account_post.setFirstName(firstName);

        lastName = edit_lastname.getText().toString();
        create_account_post.setLastName(lastName);

        birthday = edit_birthday.getText().toString();
        create_account_post.setBirthday(birthday);

        phoneNumber = edit_phonenumber.getText().toString();
        create_account_post.setPhoneNumber(phoneNumber);

        email = edit_email.getText().toString();
        create_account_post.setEmail(email);

        address = edit_address.getText().toString();
        create_account_post.setAddress(edit_address.getText().toString());
    }

    public void Variable_defaultSetting(){
        create_account_post.setDeviceKind(2);
        create_account_post.setRequestUserId(1);
        create_account_post.setOrganizationId(1);
        create_account_post.setEncryption(0);
    }

}

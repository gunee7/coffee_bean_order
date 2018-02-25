package com.example.st1drawermenu.LoginPackage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018-01-26.
 */

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://givetest.cafe24.com/usermain/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userMajor, String userEmail, Response.Listener<String> Listener) {
        super(Method.POST,URL,Listener,null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userMajor", userMajor);
        parameters.put("userEmail", userEmail);

    }



    public Map<String, String> getParams() {
        return parameters;
    }

}

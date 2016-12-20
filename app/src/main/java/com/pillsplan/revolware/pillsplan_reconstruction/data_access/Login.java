package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class Login {

    //TODO: implement this method
    public static void login(String username, String password){

    }

    //TODO: implement this method
    public static void logout() {

    }

    //TODO: implement this method
    public static String getToken() {
        return null;
    }

    //TODO: handle wrong token input
    public static String getUserId () {
        String jwt = getToken();
        try {
            JSONObject payload = new JSONObject(jwt.split(".")[1]);
            return payload.getString("loggedInAs");
        } catch (JSONException e) {
            return null;
        }
    }
}

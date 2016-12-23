package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import android.content.Context;
import android.util.Base64;

import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.AttemptLimitExceededException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.InvalidCredentialsException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.ServerException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongEmailException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongPasswordException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class Login {

    private final String FILE_NAME = "jwt";
    private Context context;

    public Login (Context context){
        this.context = context;
    }

    //TODO: implement this method
    public void login(String email, String password) throws ServerException{
        HashMap<String, String> headers = new HashMap<>();
        String auth = Base64.encodeToString(email.getBytes(), Base64.URL_SAFE) + ":" + Base64.encodeToString(password.getBytes(), Base64.URL_SAFE);
        headers.put("Authorization", auth);

        String token = makeRequest(WebAPIRefs.LOGIN.getURI(), headers);

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(token.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }

    private static String makeRequest(URI uri, HashMap<String, String> headers) throws ServerException{
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            connection = (HttpURLConnection) uri.getURL().openConnection();
            connection.setRequestProperty("Accept", "application/json");
            for(String key : headers.keySet())
                connection.setRequestProperty(key, headers.get(key));
            connection.setRequestMethod("GET");

            connection.getInputStream();
            return connection.getHeaderField("jwt");
        } catch (IOException e) {
            try {
                stream = new BufferedInputStream(connection.getErrorStream());
                byte[] data = new byte[stream.available()];
                stream.read(data);

                JSONObject jsonObject = new JSONObject(new String(data));
                switch(jsonObject.getString("message")){
                    case "Wrong password.":
                        throw new WrongPasswordException();
                    case "Account with this email does not exist.":
                        throw new WrongEmailException();
                    case "You exceeded maximal number of attempts. You will be able to try it again in 30 minutes.":
                        throw new AttemptLimitExceededException();
                    case "Invalid credentials.":
                        throw new InvalidCredentialsException();
                }

            } catch (IOException e1) {} catch (JSONException e1) {}
        } finally {
            if(connection != null)
                connection.disconnect();
            if(stream != null)
                try { stream.close(); } catch (IOException e) {}
        }
        return null;
    }

    //TODO: implement this method
    public void logout() {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write("".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }

    //TODO: implement this method
    public String getToken() {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(FILE_NAME);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {}
        }
        return null;
    }

    //TODO: handle wrong token input
    public String getUserId () {
        String jwt = getToken();
        if(jwt == null)
            return null;
        try {
            JSONObject payload = new JSONObject(jwt.split(".")[1]);
            return payload.getString("loggedInAs");
        } catch (JSONException e) {
            return null;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

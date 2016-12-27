package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.AttemptLimitExceededException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.InvalidCredentialsException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.ServerException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongEmailException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongPasswordException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.user.LoginListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class Login {

    private final String FILE_NAME = "jwt";
    private Context context;
    private LoginListener listener;

    public Login (Context context){
        this.context = context;
    }

    public void login(String email, String password) {

        final HashMap < String, String > headers = new HashMap<>();
        final String auth = Base64.encodeToString(email.getBytes(), Base64.URL_SAFE) + ":" + Base64.encodeToString(password.getBytes(), Base64.URL_SAFE);
        headers.put("Authorization", auth);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String token = null;
                try {
                    token = makeRequest(WebAPIRefs.LOGIN.getURI(), headers);
                } catch (ServerException e) {
                    if(listener != null)
                        listener.onError(e);
                }

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
                if(listener != null && token != null)
                    listener.onSuccess(token);
            }
        }).start();
    }

    private static String makeRequest(URI uri, HashMap<String, String> headers) throws ServerException{
        HttpURLConnection connection = null;
        BufferedReader stream = null;
        try {
            connection = (HttpURLConnection) new URL("http://hrabovec.hopto.org:7070/api/login").openConnection();
            connection.setRequestProperty("Accept", "application/json");
            for(String key : headers.keySet())
                connection.setRequestProperty(key, headers.get(key));
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            if (connection.getResponseCode() < 400) {
                return connection.getHeaderField("jwt");
            }else{
                stream = new BufferedReader( new InputStreamReader( connection.getErrorStream() ) );
                String data = new String(); String line;
                while( (line = stream.readLine()) != null )
                    data += line;
                JSONObject jsonObject = new JSONObject(data);
                switch(jsonObject.getInt("status")){
                    case ServerException.INVALID_PASSWORD_CODE:
                        throw new WrongPasswordException();
                    case ServerException.INVALID_ACCOUNT_CODE:
                        throw new WrongEmailException();
                    case ServerException.ATTEMPT_LIMIT_EXCEEDED_CODE:
                        throw new AttemptLimitExceededException();
                    case ServerException.INVALID_CREDENTIALS_CODE:
                        throw new InvalidCredentialsException();
                    default:
                        throw new ServerException();
                }
            }
        } catch (IOException | JSONException e) {
        } finally {
            if(stream != null)
                try { stream.close(); } catch (IOException e) {}
            if(connection != null)
                connection.disconnect();
        }
        return null;
    }

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

    public void setLoginListener(LoginListener listener) {
        this.listener = listener;
    }
}

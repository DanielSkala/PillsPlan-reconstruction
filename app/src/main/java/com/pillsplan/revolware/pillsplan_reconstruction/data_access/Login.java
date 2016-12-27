package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.AttemptLimitExceededException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.InternalErrorException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.InvalidCredentialsException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.ServerException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongEmailException;
import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.WrongPasswordException;

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
 * 27.12.2016
 */

public class Login {

    private final String FILE_NAME = "jwt";
    private Context context;
    private RequestListener listener;

    public Login (Context context){
        this.context = context;
    }

    /**
     * This method stores token gathered by HTTP request into the file
     * @param email User's email
     * @param password User's password
     */
    public void login(String email, String password) {
        final HashMap < String, String > headers = new HashMap<>();
        final String auth = (Base64.encodeToString(email.getBytes(), Base64.NO_PADDING) + ":" + Base64.encodeToString(password.getBytes(), Base64.NO_PADDING)).replaceAll("\n", "");
        headers.put("Authorization", auth);

        //HTTP Request have to run on separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                String token = null;
                try {
                    //Call HTTP Request
                    token = makeRequest(WebAPIRefs.LOGIN.getURI(), headers);
                } catch (ServerException e) {
                    //If error occurs, listener will be called
                    if(listener != null)
                        listener.onError(e);
                }

                //Store token in file
                if(token != null) {
                    FileOutputStream fos = null;
                    try {
                        fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                        fos.write(token.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fos.close();
                        } catch (IOException e) {
                        }
                    }
                    if (listener != null)
                        listener.onSuccess(token);
                }
            }
        }).start();
    }

    /**
     * This method provides HTTP connection to the server
     * @param uri URI of the resource
     * @param headers custom headers
     * @return either JWT token or null
     * @throws ServerException
     */
    private String makeRequest(URI uri, HashMap<String, String> headers) throws ServerException{
        HttpURLConnection connection = null;
        BufferedReader stream = null;
        try {
            //Set up connection
            connection = (HttpURLConnection) uri.getURL().openConnection();
            connection.setRequestProperty("Accept", "application/json");
            for(String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);

            //If login is successful server will return token stored in header
            //If not this method will throw error based on error code provided in response
            if (connection.getResponseCode() < 400) {
                return connection.getHeaderField("jwt");
            }else{
                //Get response body
                stream = new BufferedReader( new InputStreamReader( connection.getErrorStream() ) );
                String data = new String(); String line;
                while( (line = stream.readLine()) != null )
                    data += line;
                //Make JSON out of the response and throw exception
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
                        throw new InternalErrorException();
                }
            }
        } catch (IOException | JSONException e) {
        } finally {
            if(stream != null)
                try { stream.close(); } catch (IOException e) {}
            if(connection != null)
                connection.disconnect();
        }
        //If something bad happens null will be returned
        return null;
    }

    /**
     * This method will simply clear the file in which the token is stored
     */
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

    /**
     * This method will return JWT Token stored in file
     * @return JWT Token
     */
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

    /**
     * This method will read the token stored in file, decode it and return id of user whom the token is
     * @return Id of user whom the stored token is
     */
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

    public void setRequestListener(RequestListener listener) {
        this.listener = listener;
    }
}

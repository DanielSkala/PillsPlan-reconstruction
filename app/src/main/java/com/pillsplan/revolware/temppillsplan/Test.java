package com.pillsplan.revolware.temppillsplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pillsplan.revolware.temppillsplan.data_access.Login;
import com.pillsplan.revolware.temppillsplan.data_access.RequestListener;
import com.pillsplan.revolware.temppillsplan.data_access.exception.ServerException;

/**
 * @author Peter Grajcar
 * 27.12.2016
 */

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final Button login_button = (Button) findViewById(R.id.login_button);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final TextView status = (TextView) findViewById(R.id.status);

        final Login login = new Login(getApplicationContext());

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.login(email.getText().toString(), password.getText().toString());
            }
        });
        login.setRequestListener(new RequestListener() {
            @Override
            public void onError(ServerException e) {
                final String str = e.getMessage();
                status.post(new Runnable() {
                    @Override
                    public void run() {
                        status.setText(status.getText().toString() + "\nERR:" + str);
                    }
                });
            }

            @Override
            public void onSuccess(String token) {
                final String str = token;
                status.post(new Runnable() {
                    @Override
                    public void run() {
                        status.setText(status.getText().toString() + "\nOK:" + str);
                    }
                });
            }
        });

    }
}

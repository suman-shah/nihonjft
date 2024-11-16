package com.japan.nihonjft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private Button login_button;
    private TextView forget_password,signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.login_button);
        forget_password = findViewById(R.id.forget_password);
        signup_button = findViewById(R.id.signup_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateData()){
                    login();
                }

            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });




    }
    private boolean validateData()
    {

        if(email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
            return false;
        }
        if(password.getText().toString().isEmpty())
        {
            password.setError("Enter Password");
            return false;
        }
        return true;

    }
    private void login()
    {

    }

}
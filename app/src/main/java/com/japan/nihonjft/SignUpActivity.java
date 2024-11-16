package com.japan.nihonjft;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {
    private EditText username,emailID,password,conform_password;
    private Button signup_button;
    private TextView textView4,loginBtnfromSignup;
    private ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.username);
        emailID = findViewById(R.id.emailID);
        password = findViewById(R.id.password);
        conform_password = findViewById(R.id.conform_password);
        signup_button = findViewById(R.id.signup_button);
        textView4 = findViewById(R.id.textView4);
        loginBtnfromSignup = findViewById(R.id.loginBtnfromSignup);
        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });





    }
}
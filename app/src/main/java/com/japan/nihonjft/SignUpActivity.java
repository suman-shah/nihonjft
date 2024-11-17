package com.japan.nihonjft;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText username,emailID,password,conform_password;
    private Button signup_button;
    private TextView textView4,loginBtnfromSignup;
    private ImageView back_button;
    private FirebaseAuth mAuth;
    private String emailStr,passwordStr,usernameStr,conformPasswordStr;

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
        mAuth = FirebaseAuth.getInstance();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {

                signupNewUser();
                }


            }
        });
        loginBtnfromSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }
private boolean validateData()
{
    emailStr = emailID.getText().toString().trim();
    passwordStr = password.getText().toString().trim();
    usernameStr = username.getText().toString().trim();
    conformPasswordStr = conform_password.getText().toString().trim();
    if(emailStr.isEmpty())
    {
        emailID.setError("Enter Email");
        return false;
    }
    if(passwordStr.isEmpty())
    {
        password.setError("Enter Password");
        return false;

    }
    if(usernameStr.isEmpty())
    {
        username.setError("Enter Username");
        return false;
    }
    if(conformPasswordStr.isEmpty())
    {
        conform_password.setError("Enter Conform Password");
        return false;
    }
    if(!passwordStr.equals(conformPasswordStr))
    {
        conform_password.setError("Password Not Match");
        return false;
    }
    return true;
}


    private void signupNewUser() {
        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Sign Up:success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign Up:failure", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
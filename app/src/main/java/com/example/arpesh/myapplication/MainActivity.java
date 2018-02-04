package com.example.arpesh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText InputEmail;
    EditText InputPassword;
    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputEmail = findViewById(R.id.Login_emailtext);
        InputPassword = findViewById(R.id.Login_emailtext);
        Login = findViewById(R.id.Login_button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    void Login(){
        String Email = InputEmail.getText().toString().trim();
        String PassWord = InputPassword.getText().toString().trim();
        String type="Login";
        BackGroundWorker backGroundWorker = new BackGroundWorker(this);
        backGroundWorker.execute(type,Email,PassWord);

    }


}

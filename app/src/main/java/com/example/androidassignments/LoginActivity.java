package com.example.androidassignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";

    //Variables
    EditText email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setting up function
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //Variables
        email = (EditText) findViewById(R.id.emailfield);
        button = (Button) findViewById(R.id.button);

        //More variables, but also checking preferences to set them
        SharedPreferences sp = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s = sp.getString("Default Email", "email@domain.com");
        email.setText(s);

        button.setOnClickListener(new View.OnClickListener() {

            //Button for logging in
            @Override
            public void onClick(View v) {

                //Setting/checking the string

                String login = email.getText().toString();
                SharedPreferences sp = getSharedPreferences("MySharedPref",  Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sp.edit();
                myEdit.putString("Default Email", login);
                myEdit.commit();

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

            }

        });

    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
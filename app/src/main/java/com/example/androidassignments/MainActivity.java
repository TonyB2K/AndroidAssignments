package com.example.androidassignments;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";

    //Variables
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setting up function
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //Buttons
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(i, 10);

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {

        //Setting up function
        super.onActivityResult(requestCode,responseCode,data);

        //Showing messages on activity result

        //Code 10
        if(requestCode  == 10){

            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");

        }

        //Code OK
        if(responseCode == Activity.RESULT_OK) {

            String messagePassed = data.getStringExtra("Response");

            CharSequence t = ListItemsActivity.ACTIVITY_NAME+" passed: "+messagePassed;

            Toast swapper = Toast.makeText(MainActivity.this, t, Toast.LENGTH_LONG);
            swapper.show();

        }

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
}
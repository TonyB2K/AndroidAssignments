package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.widget.Switch;
import android.util.Log;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.CheckBox;
import java.io.File;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //Variables
    ImageButton button;
    Switch swtch;
    CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setting up function
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //Variables
        button = (ImageButton) findViewById(R.id.imagebutton);
        swtch = (Switch) findViewById(R.id.swtch);
        box = (CheckBox) findViewById(R.id.checkbox);

        //Button listener for imagebutton
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(i.resolveActivity(getPackageManager()) != null) {

                    startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

                }

            }

        });

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                //Switch status checker

                CharSequence t = "Switch is Off";
                int d = Toast.LENGTH_LONG;

                if(b) {

                    t = "Switch is On";
                    d = Toast.LENGTH_SHORT;

                }

                //Display message
                Toast toast = Toast.makeText(ListItemsActivity.this, t, d);
                toast.show();

            }

        });

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                //Bob the builder
                AlertDialog.Builder bob = new AlertDialog.Builder(ListItemsActivity.this);

                //Setting strings
                bob.setMessage(R.string.dialog_message);
                bob.setTitle(R.string.dialog_title);

                bob.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Process of leaving
                        Log.i(ACTIVITY_NAME, "Finished");
                        Intent r = new Intent(ListItemsActivity.this, MainActivity.class);
                        r.putExtra("Response", "My information to share");
                        setResult(Activity.RESULT_OK, r);
                        finish();

                    }

                });

                bob.setNegativeButton(R.string.cancel,  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Cancelled activity
                        Log.i(ACTIVITY_NAME, "Canceled");
                        box.setChecked(false);

                    }

                });

                //Showing bob the builder to the world
                bob.show();

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //Image capture

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle e = data.getExtras();

            //Changing the image button image (Makes sense right?)
            if (e != null) {

                Bitmap imgBM = (Bitmap) e.get("data");
                button.setImageBitmap(imgBM);

            }

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
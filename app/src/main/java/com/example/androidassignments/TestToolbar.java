package com.example.androidassignments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;

public class TestToolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;

    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi){
        boolean set = false;
        switch(mi.getItemId()) {
            default:
                Log.d("Toolbar","No action selected");
                break;
            case R.id.action_one:
                Log.d("Toolbar", "Action 1 selected");
                Snackbar.make(findViewById(R.id.action_one), "You selected item 1", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                set=true;
                break;
            case R.id.action_two:
                Log.d("Toolbar", "Action 2 selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(TestToolbar.this, MainActivity.class);
                        startActivityForResult(i, 10);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                set=true;
                break;
            case R.id.action_three:
                Log.d("Toolbar", "Action 3 selected");
                set=true;
                break;
            case R.id.about:
                Log.d("Toolbar","About selected");
                CharSequence t = "Version 1.0, by Tony Bodulovic";
                Toast toast = Toast.makeText(TestToolbar.this,t,Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        return set;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You clicked my snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
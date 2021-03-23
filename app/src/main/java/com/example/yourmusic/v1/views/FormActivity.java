package com.example.yourmusic.v1.views;

import android.os.Bundle;

import com.example.yourmusic.v1.interfaces.FormInterface;
import com.example.yourmusic.v1.presenters.FormPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.yourmusic.R;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity implements FormInterface.View {

    String TAG = "YourMusic/FormActivity";
    private FormInterface.Presenter presenter;
    Button buttonSave;
    private Spinner spinner = null;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> genero = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        presenter = new FormPresenter(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Clicking up Button");
                onBackPressed();
            }
        });

        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Clicking Save Button");
                presenter.onClickSaveAlbum();
            }
        });

        //Create Spinner
        genero = new ArrayList<String>();
        genero.add("Pop");
        genero.add("Rock");
        genero.add("Rap");
        genero.add("Trap");

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genero);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

    }

    @Override
    public void SaveAlbum() {
        Log.d(TAG,"Saving album");
        finish();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }


}
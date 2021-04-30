package com.example.yourmusic.v1.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.example.yourmusic.v1.interfaces.SearchInterface;
import com.example.yourmusic.v1.presenters.SearchPresenter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.yourmusic.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements SearchInterface.View {
    String TAG = "YourMusic/SearchActivity";
    private SearchInterface.Presenter presenter;
    private Context context;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> genero = null;
    ImageView imageViewSpinner;
    EditText artistName;
    EditText editTextCalendar;
    ImageView imageViewCalendar;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new SearchPresenter(this);
        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        artistName = findViewById(R.id.artistName);

        //Boton guardar
        Button SearchButton = findViewById(R.id.searchButton);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSearchButton();
            }
        });

        //Calendario
        editTextCalendar = findViewById(R.id.dateText);
        imageViewCalendar = findViewById(R.id.imageDate);
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar();
            }
        });

        //Spinner
        genero = presenter.getGenres();

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genero);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void SearchAlbum() {
        Intent searchAlbum = getIntent();
        searchAlbum.putExtra("name", artistName.getText().toString());
        searchAlbum.putExtra("date", editTextCalendar.getText().toString());
        searchAlbum.putExtra("spinner", spinner.getSelectedItemId());
        setResult(RESULT_OK, searchAlbum);
        finish();
    }


    public void showCalendar(){
        // Fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        // Se define el calendario y se pone la fecha por defecto
        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            // Clicking Ok
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Le asignamos la fecha al editText
                editTextCalendar.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
            }
        },Year, Month, Day);
        // Mostramos el calendario
        datePickerDialog.show();
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
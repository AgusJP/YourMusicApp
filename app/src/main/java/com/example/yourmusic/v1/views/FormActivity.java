package com.example.yourmusic.v1.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.yourmusic.v1.interfaces.FormInterface;
import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.v1.presenters.FormPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements FormInterface.View {

    String TAG = "YourMusic/FormActivity";
    private FormInterface.Presenter presenter;
    private Context context;
    Button buttonSave;
    private Spinner spinner = null;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> genero = null;

    EditText editTextCalendar;
    ImageView imageViewCalendar;
    ImageView imageViewSpinner;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    AlbumEntity album;

    EditText artistText;
    TextInputLayout artistInputLayout;

    EditText albumText;
    TextInputLayout albumInputLayout;

    EditText companyText;
    TextInputLayout companyInputLayout;

    EditText appreciationText;
    TextInputLayout appreciationInputLayout;

    EditText dateText;
    TextInputLayout dateInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        presenter = new FormPresenter(this);
        context = this;

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

        Button DeleteButton = findViewById(R.id.delete);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog();
            }
        });

        //Create Spinner
        genero = new ArrayList<String>();
        genero.add(getString(R.string.pop));
        genero.add(getString(R.string.rock));
        genero.add(getString(R.string.rap));
        genero.add(getString(R.string.trap));

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genero);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        imageViewSpinner = findViewById(R.id.spinnerAddForm);
        imageViewSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.AddSpinnerElement();
            }
        });



        album = new AlbumEntity();

        artistText = findViewById(R.id.artistText);
        artistInputLayout = findViewById(R.id.artistInputLayout);
        artistText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (!album.setArtistName(artistText.getText().toString())) {
                        artistInputLayout.setError(getString((presenter.showError(1))));
                    } else {
                        artistInputLayout.setError("");
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        albumText = findViewById(R.id.albumText);
        albumInputLayout = findViewById(R.id.albumInputLayout);
        albumText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (!album.setAlbumName(albumText.getText().toString())) {
                        albumInputLayout.setError(getString((presenter.showError(2))));
                    } else {
                        albumInputLayout.setError("");
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        companyText = findViewById(R.id.companyText);
        companyInputLayout = findViewById(R.id.companyInputLayout);
        companyText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (!album.setCompany(companyText.getText().toString())) {
                        companyInputLayout.setError(getString((presenter.showError(3))));
                    } else {
                        companyInputLayout.setError("");
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        appreciationText = findViewById(R.id.appreciationText);
        appreciationInputLayout = findViewById(R.id.appreciationInputLayout);
        appreciationText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (appreciationText.getText().toString().equals("") || !album.setAppreciation(appreciationText.getText().toString())) {
                        appreciationInputLayout.setError(getString((presenter.showError(4))));
                    } else {
                        appreciationInputLayout.setError("");
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        dateText = findViewById(R.id.dateText);
        dateInputLayout = findViewById(R.id.dateInputLayout);
        dateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (!album.setDate(dateText.getText().toString())) {
                        dateInputLayout.setError(getString((presenter.showError(5))));
                    } else {
                        dateInputLayout.setError("");
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        //Calendario
        editTextCalendar = findViewById(R.id.dateText);
        imageViewCalendar = findViewById(R.id.imageDateForm);
        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar();
            }
        });


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
    public void AddSpinnerElement() {
        String p = "";
        LayoutInflater layoutActivity = LayoutInflater.from(context);
        View viewAlertDialog = layoutActivity.inflate(R.layout.dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(viewAlertDialog);
        final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);
        alertDialog.setCancelable(false)
                // Add button
                .setPositiveButton(getResources().getString(R.string.add),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                if ((dialogInput.getText().toString().equals(p.toString()))) {
                                    dialogBox.cancel();
                                    spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                } else {
                                    adapter.add(dialogInput.getText().toString());
                                    spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                }
                            }
                        })
                // Cancel Button
                .setNegativeButton(getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        })
                .create()
                .show();

    }

    public void deleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.deleteTitleDialog);
        builder.setMessage(R.string.deleteMessageDialog);

        //Accept Button
        builder.setPositiveButton(R.string.acceptDeleteButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickDeleteButton();
            }
        });

        //Cancel Button
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
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
    public void SaveAlbum() {
        Log.d(TAG,"Saving album");
        finish();
    }

    @Override
    public void DeleteAlbum() {
        Log.d(TAG,"Deleting album");
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
package com.example.yourmusic.v1.views;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.yourmusic.v1.interfaces.FormInterface;
import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.v1.presenters.FormPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.util.Base64;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.yourmusic.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements FormInterface.View {

    String TAG = "YourMusic/FormActivity";
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private static final int REQUEST_SELECT_IMAGE = 200;
    private ConstraintLayout constraintLayoutFormActivity;
    private FormInterface.Presenter presenter;
    private Context context;
    Button buttonSave;
    Button deleteAlbum;
    private Spinner spinner = null;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> genero = null;
    private String id;
    private Switch fav;

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

    ImageView imageAlbum;
    Button cleanImage;

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

        deleteAlbum = findViewById(R.id.delete);

        constraintLayoutFormActivity = findViewById(R.id.constraintForm);
        fav = findViewById(R.id.switch2);

        //Damos click en la imagen del album o del background
        imageAlbum = findViewById(R.id.imageView);
        imageAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickAlbumImage();
            }
        });

        //Quitar la imagen actual
        cleanImage = findViewById(R.id.button);
        cleanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickCleanAlbum();
            }
        });

        //Boton guardar nos lleva al listado
        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Clicking Save Button");
                if(album.setArtistName(artistText.getText().toString()) &&
                        album.setAlbumName(albumText.getText().toString()) &&
                        album.setCompany(companyText.getText().toString()) &&
                        album.setAppreciation(appreciationText.getText().toString()) &&
                        album.setDate(dateText.getText().toString()) &&
                        spinner.getSelectedItemPosition()!=0){

                    album.setFav(fav.isChecked());
                    album.setGenre(spinner.getSelectedItem().toString());

                    if(imageAlbum.getDrawable() == null || ((BitmapDrawable)imageAlbum.getDrawable()).getBitmap() == null){
                        album.setImage("");
                    }else{
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ((BitmapDrawable)imageAlbum.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        String imageInBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        album.setImage(imageInBase64);
                    }

                    if(id!=null) {
                        album.setId(id);
                    }else {
                        album.setId("");
                    }
                    presenter.onClickSaveAlbum(album);

                }else{
                    showErrorWithToast(getString(R.string.filledFields));
                }
            }
        });

        //Boton eliminar nos abre en alertDialog. Si presionamos cancel no hace nada y si presionamos aceptar nos lleva a listado.
        Button DeleteButton = findViewById(R.id.delete);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog();
            }
        });

        /* //Recuperar el Id del Album del LISTADO
        id = getIntent().getStringExtra("id");
        if(id != null){
            Log.d(TAG,"Recuperando el Id");
            artistText = findViewById(R.id.artistText);
            artistText.setText(id);
        }else{
            Log.d(TAG,"Deshabilitar el boton de eliminar");
        }*/


        //Creación del Spinner
        /*genero = new ArrayList<String>();
        genero.add(getString(R.string.Choose));
        genero.add(getString(R.string.pop));
        genero.add(getString(R.string.rock));
        genero.add(getString(R.string.rap));
        genero.add(getString(R.string.trap));*/

        spinner = (Spinner) findViewById(R.id.spinner);
        genero = presenter.getSpinnerElements();
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genero);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        imageViewSpinner = findViewById(R.id.spinnerAddForm);
        imageViewSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.AddSpinnerElement();
            }
        });


        //Toda la parte de manejo de errores de los campos del formulario
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

        //Recuperamos el Id del Album del LISTADO
        id = getIntent().getStringExtra("id");

        if(id != null){
            //Metemos en todos los campos los valores del album seleccionado en el listado
            AlbumEntity album = presenter.getAlbumById(id);
            albumText.setText(album.getAlbumName());
            artistText.setText(album.getArtistName());
            companyText.setText(album.getCompany());
            appreciationText.setText(album.getAppreciation()+"");
            fav.setChecked(album.isFav());
            SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
            dateText.setText(date.format(album.getDate())+"");

            if(!album.getImage().equals("")){
                imageAlbum.setBackground(null);
                byte[] decodedString = Base64.decode(album.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageAlbum.setImageBitmap(decodedByte);
            }else{
                //Si no posee imagen le ponemos la imagen por defecto y quitamos el background
                imageAlbum.setImageDrawable(MyApp.getContext().getDrawable(R.drawable.ic_notas_musicales));
                imageAlbum.setBackground(null);
            }
            spinner.setSelection(getGenre(spinner, album.getGenre()));
        }else{
            // Deshabilitamos y ocultamos el boton eliminar
            deleteAlbum.setEnabled(false);
            deleteAlbum.setVisibility(View.GONE);

        }
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

    @Override
    public void showErrorWithToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public void deleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.deleteTitleDialog);
        builder.setMessage(R.string.deleteMessageDialog);

        //Accept Button
        builder.setPositiveButton(R.string.acceptDeleteButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickDeleteButton(id);
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

    private int getGenre(Spinner sp, String string){

        int i = 0;

        for (int index=0;index<sp.getCount();index++){
            if (sp.getItemAtPosition(index).equals(string)){
                i = index;
            }
        }
        return i;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);

                        // Quitamos del ImagenView el background
                        imageAlbum.setBackground(null);

                        // Se carga el Bitmap en el ImageView
                        imageAlbum.setImageBitmap(bmp);
                    }
                }
                break;
        }
    }

    @Override
    public void showRequestPermission() {
        // Permiso denegado
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
            // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
        }else{
            Snackbar.make(constraintLayoutFormActivity, getResources().getString(R.string.denied), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            //Selecciona la imagen de la galería
            presenter.permissionGranted();
        } else {
            //Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            //Muestra el error del permiso denegado
            presenter.permissionDenied();
        }
    }

    @Override
    public void selectImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    public void cleanAlbum() {
        imageAlbum.setImageBitmap(null);
        imageAlbum.setBackground(getDrawable(R.drawable.ic_notas_musicales));
    }

    @Override
    public void showPermissionDenied() {
        Snackbar.make(constraintLayoutFormActivity, getResources().getString(R.string.write_permission_denied), Snackbar.LENGTH_LONG).show();
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
        Toast.makeText(FormActivity.this, getString(R.string.deleteAlbumSuccess), Toast.LENGTH_LONG)
                .show();
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
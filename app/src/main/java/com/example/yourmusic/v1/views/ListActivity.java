package com.example.yourmusic.v1.views;

import android.content.Intent;
import android.os.Bundle;
import com.example.yourmusic.R;
import com.example.yourmusic.v1.interfaces.ListInterface;
import com.example.yourmusic.v1.presenters.ListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity implements ListInterface.View {

    String TAG = "YourMusic/ListActivity";
    private ListInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ListPresenter(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Clicking floating button");
               presenter.onClickAddAlbum();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            presenter.onClickSearchIcon();
        }
        if (id == R.id.action_sort) {
            return true;
        }
        if (id == R.id.action_configuration) {
            return true;
        }
        if (id == R.id.action_help) {
            return true;
        }
        if (id == R.id.action_about) {
            presenter.onClickAboutAppCRUD();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startFormActivity() {
        Log.d(TAG,"Starting FormActivity");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSearchActivity() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
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
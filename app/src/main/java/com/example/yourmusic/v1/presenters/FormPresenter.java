package com.example.yourmusic.v1.presenters;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.yourmusic.R;
import com.example.yourmusic.v1.interfaces.FormInterface;
import com.example.yourmusic.v1.views.MyApp;


public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;

    public FormPresenter(FormInterface.View view) {
        this.view = view;
    }

    @Override
    public void onClickSaveAlbum() {
        view.SaveAlbum();
    }

    @Override
    public int showError(int typeError) {
        int error;


        switch (typeError){

            case 1:
                error = R.string.artistNameError;
                break;
            case 2:
                error = R.string.albumNameError;
                break;
            case 3:
                error = R.string.companyNameError;
                break;
            case 4:
                error = R.string.appreciationError;
                break;
            case 5:
                error = R.string.dateError;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeError);
        }
        return error;
    }

    @Override
    public void AddSpinnerElement()
    {
        view.AddSpinnerElement();
    }

    @Override
    public void onClickDeleteButton() {
        view.DeleteAlbum();
    }

    @Override
    public void onClickAlbumImage() {
        int CameraPermission= ContextCompat.checkSelfPermission(MyApp.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("FormPresenter", "Camera Permission: " + CameraPermission);
        if(CameraPermission != PackageManager.PERMISSION_GRANTED){
            //Permiso denegado.
            view.showRequestPermission();
        }else{
            //Permiso aceptado.
            view.selectImageFromGallery();
        }
    }

    @Override
    public void onClickCleanAlbum() {
        view.cleanAlbum();
    }

    @Override
    public void permissionGranted() {
        view.selectImageFromGallery();
    }

    @Override
    public void permissionDenied() {
        view.showPermissionDenied();
    }


}

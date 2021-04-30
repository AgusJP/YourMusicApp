package com.example.yourmusic.v1.presenters;


import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.yourmusic.R;
import com.example.yourmusic.v1.interfaces.FormInterface;
import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.v1.models.AlbumModel;
import com.example.yourmusic.v1.views.MyApp;

import java.util.ArrayList;


public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;
    private AlbumModel albumModel;

    public FormPresenter(FormInterface.View view) {
        this.view = view;
        albumModel = new AlbumModel();
    }

    @Override
    public void onClickSaveAlbum(AlbumEntity album) {
        if(album.getId()!="") {
            albumModel.updateAlbum(album);
            view.SaveAlbum();
        }else if(albumModel.insertAlbum(album)) {
            view.SaveAlbum();
        } else {
            //Album no se ha podido crear por algún motivo
            view.showErrorWithToast(MyApp.getContext().getResources().getString(R.string.albumNotSave));
        }
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
    public void onClickDeleteButton(String id) {
        albumModel.deleteAlbum(id);
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

    @Override
    public AlbumEntity getAlbumById(String id) {
        AlbumEntity result = new AlbumEntity();
        result = albumModel.getAlbumById(id);
        return result;
    }

    @Override
    public ArrayList<String> getSpinnerElements() {
        return albumModel.getGenreAlbum();
    }
}

package com.example.yourmusic.v1.interfaces;

import com.example.yourmusic.v1.models.AlbumEntity;

import java.util.ArrayList;

public interface FormInterface {

    public interface View{
        void SaveAlbum();
        void AddSpinnerElement();
        void DeleteAlbum();
        void showRequestPermission();
        void selectImageFromGallery();
        void cleanAlbum();
        void showPermissionDenied();
        void showErrorWithToast(String text);

    }

    public interface Presenter{
        void onClickSaveAlbum(AlbumEntity album);
        int showError(int typeError);
        void AddSpinnerElement();
        void onClickDeleteButton(String id);
        void onClickAlbumImage();
        void onClickCleanAlbum();
        void permissionGranted();
        void permissionDenied();
        AlbumEntity getAlbumById(String id);
        ArrayList<String> getSpinnerElements();
    }

}

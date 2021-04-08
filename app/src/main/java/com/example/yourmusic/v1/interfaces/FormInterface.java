package com.example.yourmusic.v1.interfaces;

public interface FormInterface {

    public interface View{
        void SaveAlbum();
        void AddSpinnerElement();
        void DeleteAlbum();
        void showRequestPermission();
        void selectImageFromGallery();
        void cleanAlbum();
        void showPermissionDenied();

    }

    public interface Presenter{
        void onClickSaveAlbum();
        int showError(int typeError);
        void AddSpinnerElement();
        void onClickDeleteButton();
        void onClickAlbumImage();
        void onClickCleanAlbum();
        void permissionGranted();
        void permissionDenied();
    }

}

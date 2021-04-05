package com.example.yourmusic.v1.interfaces;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startSearchActivity();
        void startAboutActivity();
    }

    public interface Presenter{
        void onClickAddAlbum();
        void onClickSearchIcon();
        void onClickAboutAppCRUD();

    }
}

package com.example.yourmusic.v1.interfaces;

public interface FormInterface {

    public interface View{
        void SaveAlbum();
    }

    public interface Presenter{
        void onClickSaveAlbum();
    }

}

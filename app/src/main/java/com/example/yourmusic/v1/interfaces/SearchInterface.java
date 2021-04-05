package com.example.yourmusic.v1.interfaces;

public interface SearchInterface {

    public interface View{
        void SearchAlbum();
    }

    public interface Presenter{
        void onClickSearchButton();
    }
}

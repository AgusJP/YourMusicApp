package com.example.yourmusic.v1.interfaces;

import java.util.ArrayList;

public interface SearchInterface {

    public interface View{
        void SearchAlbum();
    }

    public interface Presenter{
        void onClickSearchButton();
        ArrayList<String> getGenres();
    }
}

package com.example.yourmusic.v1.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yourmusic.v1.models.AlbumEntity;

import java.util.ArrayList;
import java.util.Date;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startSearchActivity();
        void startAboutActivity();
        void startFormActivity(String id);
        void onSwipedRemove(RecyclerView.ViewHolder target);
    }

    public interface Presenter{
        void onClickAddAlbum();
        void onClickSearchIcon();
        void onClickAboutAppCRUD();
        void onSwipedAlbum(RecyclerView.ViewHolder target, String id);
        void onClickRecyclerViewAlbum(String id);
        ArrayList<AlbumEntity> getAllAlbumsSummarize();
        ArrayList<AlbumEntity> getAlbumsFilter(String name, Date date, String genre);
        ArrayList<String> getGenres();
    }
}

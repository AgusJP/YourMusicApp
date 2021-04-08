package com.example.yourmusic.v1.interfaces;

import androidx.recyclerview.widget.RecyclerView;

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
        void onSwipedAlbum(RecyclerView.ViewHolder target);
        void onClickRecyclerViewAlbum(String id);
    }
}

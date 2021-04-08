package com.example.yourmusic.v1.presenters;


import androidx.recyclerview.widget.RecyclerView;

import com.example.yourmusic.v1.interfaces.ListInterface;

public class ListPresenter implements ListInterface.Presenter {

    private ListInterface.View view;

    public ListPresenter(ListInterface.View view) {
        this.view = view;
    }

    @Override
    public void onClickAddAlbum() {
        view.startFormActivity();
    }

    @Override
    public void onClickSearchIcon() {
        view.startSearchActivity();
    }

    @Override
    public void onClickAboutAppCRUD() {
        view.startAboutActivity();
    }

    @Override
    public void onSwipedAlbum(RecyclerView.ViewHolder target) {
        view.onSwipedRemove(target);
    }

    @Override
    public void onClickRecyclerViewAlbum(String id){
        view.startFormActivity(id);
    }

}

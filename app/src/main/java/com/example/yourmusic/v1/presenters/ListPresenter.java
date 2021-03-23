package com.example.yourmusic.v1.presenters;


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
}

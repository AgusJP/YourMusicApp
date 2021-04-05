package com.example.yourmusic.v1.presenters;

import com.example.yourmusic.v1.interfaces.SearchInterface;

public class SearchPresenter implements SearchInterface.Presenter {

    private SearchInterface.View view;

    public SearchPresenter(SearchInterface.View view) {
        this.view = view;
    }

    @Override
    public void onClickSearchButton() {
        view.SearchAlbum();
    }



}
